/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
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
import pe.gob.mef.gescon.common.Constante;
import pe.gob.mef.gescon.common.Parameters;
import pe.gob.mef.gescon.service.AsignacionService;
import pe.gob.mef.gescon.service.BaseLegalService;
import pe.gob.mef.gescon.service.ConocimientoService;
import pe.gob.mef.gescon.service.PreguntaService;
import pe.gob.mef.gescon.service.SeccionService;
import pe.gob.mef.gescon.service.UserService;
import pe.gob.mef.gescon.util.MailUtils;
import pe.gob.mef.gescon.util.ServiceFinder;
import pe.gob.mef.gescon.web.bean.BaseLegal;
import pe.gob.mef.gescon.web.bean.Conocimiento;
import pe.gob.mef.gescon.web.bean.Consulta;
import pe.gob.mef.gescon.web.bean.Pregunta;
import pe.gob.mef.gescon.web.bean.Seccion;
import pe.gob.mef.gescon.web.bean.User;

/**
 *
 * @author JJacobo
 */
public class Indexador {

    private static final String FIELD_PATH = "path";
    private static final String FIELD_CONTENTS = "contents";
    private static final String FIELD_FILENAME = "filename";
    private static final String FIELD_CODE = "code";
    private static final String FIELD_TYPE = "type";
    private static final String FILE_NAME = "plain.txt";

    public static void main(String args[]) {
        try {
            indexDirectory();
            notificar();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public static void notificar() {
        try {
            UserService usuarioService = (UserService) ServiceFinder.findBean("UserService");
            AsignacionService asignacionService = (AsignacionService) ServiceFinder.findBean("AsignacionService");
            List<User> moderadores = usuarioService.getUsersActivedByPerfil(BigDecimal.valueOf(Constante.MODERADOR));
            if (CollectionUtils.isNotEmpty(moderadores)) {                
                for (User user : moderadores) {
                    List<Consulta> lista = asignacionService.getNotificationsAlertPanelByMtuser(user);
                    if (CollectionUtils.isNotEmpty(lista)) {
                        String body = getBody(user, lista);
                        MailUtils.sendMail(user.getVcorreo(), "GESCON - Notificaciones", body);
                    }
                }
            }
            List<User> especialistas = usuarioService.getUsersActivedByPerfil(BigDecimal.valueOf(Constante.ESPECIALISTA));
            if (CollectionUtils.isNotEmpty(especialistas)) {
                for (User user : especialistas) {
                    List<Consulta> lista = asignacionService.getNotificationsAlertPanelByMtuser(user);
                    if (CollectionUtils.isNotEmpty(lista)) {
                        String body = getBody(user, lista);
                        MailUtils.sendMail(user.getVcorreo(), "GESCON - Notificaciones", body);
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static String getBody(User user, List<Consulta> lista) {
        StringBuilder body = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            body.append("<table>");
            body.append("<tr>");
            body.append("<td>");
                body.append("<table>");
                body.append("<tr>");
                body.append("<td>");
                body.append("<img src='cid:banner'>");
                body.append("</td>");
                body.append("</tr>");
                body.append("<tr style='font-size: 30px; font-weight: bold; height: 50px;'>");
                body.append("<td colspan='6' style='text-align: center;'>");
                body.append("Actividades Pendientes");
                body.append("</td>");
                body.append("</tr>");
                body.append("<tr style='font-size: 12px; height: 40px;'>");
                body.append("<td colspan='6' style='text-align: center;'>");
                body.append("Con el fin de ayudarte a planificar y gestionar tus operaciones dentro de la plataforma, GESCON realiza un seguimiento del estado de tus tareas:");
                body.append("</td>");
                body.append("</tr>");
                body.append("</table>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr>");
            body.append("<td>");
                body.append("<table style='border: 1px black solid; width: 100%;'>");
                body.append("<tr style='font-weight: bold; font-size: 12px; border: 1px black solid; background: #CCCCCC;'>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("NOMBRE");
                body.append("</td>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("TIPO CONOCIMIENTO");
                body.append("</td>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("CATEGORÍA");
                body.append("</td>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("FECHA PUBLICACIÓN");
                body.append("</td>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("ESTADO");
                body.append("</td>");
                body.append("<td style='text-align: center; border: 1px black solid;'>");
                body.append("DÍAS RETRASO");
                body.append("</td>");
                body.append("</tr>");
                if(CollectionUtils.isNotEmpty(lista)){
                    for (int i = 0; i < lista.size(); i++) {
                        Consulta c = (Consulta) lista.get(i);
                        if((i+1) % 2 == 0) {
                            body.append("<tr style='font-size: 10px; background: #CCCCCC;'>");
                        } else {
                            body.append("<tr style='font-size: 10px;'>");
                        }
                        body.append("<td>");
                        if (StringUtils.isNotBlank(c.getCodigo())) {
                            body.append(c.getCodigo());
                        } else {
                            body.append(c.getNombre());
                        }
                        body.append("</td>");
                        body.append("<td>");
                        body.append(c.getTipoConocimiento());
                        body.append("</td>");
                        body.append("<td>");
                        body.append(c.getCategoria());
                        body.append("</td>");
                        body.append("<td style='text-align: center;'>");
                        body.append(sdf.format(c.getFechaPublicacion()));
                        body.append("</td>");
                        body.append("<td style='text-align: center;'>");
                        body.append(c.getEstado());
                        body.append("</td>");
                        body.append("<td style='text-align: center;'>");
                        body.append(c.getSemaforo());
                        body.append("</td>");
                        body.append("</tr>");
                    }
                }
                body.append("</table>");
            body.append("</td>");
            body.append("</tr>");
            body.append("<tr>");
            body.append("<td>");
                body.append("<table>");
                body.append("<tr style='font-size: 12px; height: 40px;'>");
                body.append("<td colspan='6' style='text-align: center;'>");
                body.append("Haz clic en el link de abajo para poder ingresar a la plataforma, y administrar tus actividades. Recuerda no acumules tareas pendientes, el conocimiento comienza ahora.");
                body.append("</td>");
                body.append("</tr>");
                body.append("<tr>");
                body.append("<td style='text-align:center;vertical-align:top;'>");
                body.append("<a href='http://192.168.1.11:8180/gescon/'><img src='cid:boton'></a>");
                body.append("</td>");
                body.append("</tr>");
                body.append("</table>");
            body.append("</td>");
            body.append("</tr>");
            body.append("</table>");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return body.toString();
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
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String indexDirectory = bundle.getString("indexDirectory");
            filepath = bundle.getString("filepath");
            user = bundle.getString("user");
            password = bundle.getString("password");
            
            Path path = Paths.get(indexDirectory);
            Directory directory = FSDirectory.open(path);
            IndexWriterConfig config = new IndexWriterConfig(new SimpleAnalyzer());
            IndexWriter indexWriter = new IndexWriter(directory, config);
            indexWriter.deleteAll();
            
            PreguntaService preguntaService = (PreguntaService) ServiceFinder.findBean("PreguntaService");
            List<Pregunta> listaP = preguntaService.getPreguntasActivedPosted();
            if (!CollectionUtils.isEmpty(listaP)) {
                String prefix = bundle.getString("prprefix");
                for (Pregunta p : listaP) {
                    url = filepath + prefix + p.getNpreguntaid().toString() + "/" + BigDecimal.ZERO.toString() + "/";
                    auth = new NtlmPasswordAuthentication(null, user, password);
                    dir = new SmbFile(url, auth);
                    file = new File(dir.getUncPath(), FILE_NAME);
                    
                    if (file.exists()) {
                        doc = new Document();
                        doc.add(new TextField(FIELD_PATH, dir.getUncPath(), Store.YES));
                        doc.add(new TextField(FIELD_FILENAME, FILE_NAME, Store.YES));
                        doc.add(new TextField(FIELD_CODE, p.getNpreguntaid().toString(), Store.YES));
                        doc.add(new TextField(FIELD_TYPE, Constante.PREGUNTAS.toString(), Store.YES));

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
                }
            }
            
            BaseLegalService baseLegalService = (BaseLegalService) ServiceFinder.findBean("BaseLegalService");
            List<BaseLegal> listaB = baseLegalService.getBaselegalesActivedPosted();
            if (!CollectionUtils.isEmpty(listaB)) {
                String prefix = bundle.getString("blprefix");
                for (BaseLegal b : listaB) {
                    url = filepath + prefix + b.getNbaselegalid().toString() + "/" + BigDecimal.ZERO.toString() + "/";
                    auth = new NtlmPasswordAuthentication(null, user, password);
                    dir = new SmbFile(url, auth);
                    file = new File(dir.getUncPath(), FILE_NAME);
                    
                    if (file.exists()) {
                        doc = new Document();
                        doc.add(new TextField(FIELD_PATH, dir.getUncPath(), Store.YES));
                        doc.add(new TextField(FIELD_FILENAME, FILE_NAME, Store.YES));
                        doc.add(new TextField(FIELD_CODE, b.getNbaselegalid().toString(), Store.YES));
                        doc.add(new TextField(FIELD_TYPE, Constante.BASELEGAL.toString(), Store.YES));

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
                }
            }

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
                        doc.add(new TextField(FIELD_TYPE, c.getNtipoconocimientoid().toString(), Store.YES));

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
                                doc.add(new TextField(FIELD_TYPE, c.getNtipoconocimientoid().toString(), Store.YES));

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

    public static HashMap search(String text) {
        String codesBL = null;
        String codesPR = null;
        String codesC = null;
        HashMap map = new HashMap();
        StopWords stopWords;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            String indexDirectory = bundle.getString("indexDirectory");
            stopWords = new StopWords();
            Path path = Paths.get(indexDirectory);
            Directory directory = FSDirectory.open(path);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//            SpanishAnalyzer analyzer = new SpanishAnalyzer(Version.LUCENE_5_4_0, stopWords.getS_stopWords());
            Set words = new HashSet(Arrays.asList(stopWords.getS_stopWords()));
            QueryParser queryParser = new QueryParser(FIELD_CONTENTS, new StandardAnalyzer(CharArraySet.copy(words)));
            Query query = queryParser.parse(text.trim());
            TopDocs topDocs = indexSearcher.search(query, 10);
            StringBuilder sbBL = new StringBuilder();
            StringBuilder sbPR = new StringBuilder();
            StringBuilder sbC = new StringBuilder();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document document = indexSearcher.doc(scoreDoc.doc);
                String type = document.get(FIELD_TYPE);
                if(type.equals(Constante.BASELEGAL.toString())) {
                    sbBL.append(",").append(document.get(FIELD_CODE));
                }else if(type.equals(Constante.PREGUNTAS.toString())) {
                    sbPR.append(",").append(document.get(FIELD_CODE));
                } else {
                    sbC.append(",").append(document.get(FIELD_CODE));
                }
            }
            codesBL = sbBL.toString();
            codesPR = sbPR.toString();
            codesC = sbC.toString();
            if (StringUtils.isNotBlank(codesBL)) {
                codesBL = codesBL.substring(1);
            } else {
                codesBL = null;
            }
            if (StringUtils.isNotBlank(codesPR)) {
                codesPR = codesPR.substring(1);
            } else {
                codesPR = null;
            }
            if (StringUtils.isNotBlank(codesC)) {
                codesC = codesC.substring(1);
            } else {
                codesC = null;
            }
            map.put("codesBL", codesBL);
            map.put("codesPR", codesPR);
            map.put("codesC", codesC);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }
}
