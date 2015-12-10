/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import pe.gob.mef.gescon.common.Parameters;

/**
 *
 * @author JJacobo
 */
public class GcmFileUtils {
    
    public static void writeStringToFileServer(String path, String filename, String content) {
        String filepath;
        String user;
        String password;
        String url;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            filepath = bundle.getString("filepath");
            user = bundle.getString("user");
            password = bundle.getString("password");
            url = filepath + path;
            
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, password);
            SmbFile dir = new SmbFile(url, auth);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            SmbFile sf = new SmbFile(url.concat(filename), auth);
            SmbFileOutputStream out = new SmbFileOutputStream( sf, false );
            out.write(content.getBytes());
            out.flush();
            out.close();
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public static String readStringFromFileServer(String path, String filename) {
        String filepath;
        String user;
        String password;
        String url;
        String lineReader;
        String string = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
            filepath = bundle.getString("filepath");
            user = bundle.getString("user");
            password = bundle.getString("password");
            url = filepath + path;
            
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, password);
            SmbFile sf = new SmbFile(url.concat(filename), auth);
            
            if(sf.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new SmbFileInputStream(sf)));
                StringBuilder builder = new StringBuilder();
                while ((lineReader = reader.readLine()) != null) {
                    builder.append(lineReader).append("\n");
                }
                reader.close();
                string = builder.toString();
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return string;
    }
}
