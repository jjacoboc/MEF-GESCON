/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.es.SpanishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Seccion;

/**
 *
 * @author JJacobo
 */
public class Indexador {

    //private static final String INDEX_DIRECTORY = "\\\\192.168.1.11\\lucene\\";
    private static final String INDEX_DIRECTORY = "\\\\10.2.20.58\\lucene\\";
    private static final String FIELD_PATH = "path";
    private static final String FIELD_CONTENTS = "contents";
    private static final String FIELD_FILENAME = "filename";
    private static final String FIELD_CODE = "code";
    private static final String FILE_NAME = "plain.txt";

    public static void main(String args[]) {
        try {
//            JSFUtils.getSession().invalidate();
            indexDirectory();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void indexDirectory() {
        String filepath;
        String user;
        String password;
        String url;
        NtlmPasswordAuthentication auth;
        SmbFile dir;
        File file;
        Document doc;
        try {
            Path path = Paths.get(INDEX_DIRECTORY);
            Directory directory = FSDirectory.open(path);
            IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());
            IndexWriter indexWriter = new IndexWriter(directory, config);
            indexWriter.deleteAll();

            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            filepath = bundle.getString("filepath");
            user = bundle.getString("user");
            password = bundle.getString("password");

            ConocimientoService conocimientoService = (ConocimientoService) ServiceFinder.findBean("ConocimientoService");
            SeccionService seccionService = (SeccionService) ServiceFinder.findBean("SeccionService");
            List<Conocimiento> listaC = conocimientoService.getConocimientosActivedPublic();
            if (!CollectionUtils.isEmpty(listaC)) {
                for (Conocimiento c : listaC) {
                    url = filepath + c.getVruta();
                    auth = new NtlmPasswordAuthentication(null, user, password);
                    dir = new SmbFile(url, auth);
                    file = new File(dir.getUncPath(), FILE_NAME);

                    if (file.exists()) {
                        doc = new Document();
                        doc.add(new TextField(FIELD_PATH, dir.getUncPath(), Store.YES));
                        doc.add(new TextField(FIELD_FILENAME, FILE_NAME, Store.YES));
                        doc.add(new TextField(FIELD_CODE, c.getNconocimientoid().toString(), Store.YES));

                        FileInputStream is = new FileInputStream(file);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                        StringBuilder stringBuffer = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            stringBuffer.append(line).append("\n");
                        }
                        reader.close();
                        doc.add(new TextField(FIELD_CONTENTS, stringBuffer.toString(), Store.YES));
                        indexWriter.addDocument(doc);
                    }

                    List<Seccion> listaS = seccionService.getSeccionesByConocimiento(c.getNconocimientoid());
                    if (!CollectionUtils.isEmpty(listaS)) {
                        for (Seccion s : listaS) {
                            url = filepath + s.getVruta();
                            auth = new NtlmPasswordAuthentication(null, user, password);
                            dir = new SmbFile(url, auth);
                            file = new File(dir.getUncPath(), FILE_NAME);

                            if (file.exists()) {
                                doc = new Document();
                                doc.add(new TextField(FIELD_PATH, dir.getUncPath(), Store.YES));
                                doc.add(new TextField(FIELD_FILENAME, FILE_NAME, Store.YES));
                                doc.add(new TextField(FIELD_CODE, c.getNconocimientoid().toString(), Store.YES));

                                FileInputStream fis = new FileInputStream(file);
                                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                                StringBuilder sb = new StringBuilder();
                                String lines = null;
                                while ((lines = br.readLine()) != null) {
                                    sb.append(lines).append("\n");
                                }
                                br.close();
                                doc.add(new TextField(FIELD_CONTENTS, sb.toString(), Store.YES));
                                indexWriter.addDocument(doc);
                            }
                        }
                    }
                }
            }
            indexWriter.commit();
            indexWriter.close();
            directory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String search(String text) {
        String codes = null;
        StopWords stopWords;
        try {
            stopWords = new StopWords();
            Path path = Paths.get(INDEX_DIRECTORY);
            Directory directory = FSDirectory.open(path);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//            SpanishAnalyzer analyzer = new SpanishAnalyzer(Version.LUCENE_5_4_0, stopWords.getS_stopWords());
            Set words = new HashSet(Arrays.asList(stopWords.getS_stopWords()));
            QueryParser queryParser = new QueryParser(FIELD_CONTENTS, new StandardAnalyzer(CharArraySet.copy(words)));
            Query query = queryParser.parse(text);
            TopDocs topDocs = indexSearcher.search(query, 10);
            StringBuilder sb = new StringBuilder();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                sb.append(",").append(document.get(FIELD_CODE));
            }
            codes = sb.toString();
            if (StringUtils.isNotBlank(codes)) {
                codes = codes.substring(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return codes;
    }
}
