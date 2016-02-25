/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author JJacobo
 */
public class FLVConverter {

    private String ffmpegApp;

    public FLVConverter(String ffmpegApp) {
        this.ffmpegApp = ffmpegApp;
    }

    public void convert(String filenameIn, String filenameOut, int width, int height) throws IOException, InterruptedException {
        convert(filenameIn, filenameOut, width, height, -1);
    }

    public void convert(String filenameIn, String filenameOut, int width, int height, int quality)
            throws IOException, InterruptedException {
        ProcessBuilder processBuilder;
        Process process = null;
        InputStream stderr;
        InputStreamReader isr;
        BufferedReader br;
        String line;
        try {
            if (quality > -1) {
                processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
                        "-s", width + "*" + height, "-qscale", quality + "", filenameOut);
            } else {
                processBuilder = new ProcessBuilder(ffmpegApp, "-i", filenameIn, "-ar", "44100",
                        "-s", width + "*" + height, filenameOut);
            }
            process = processBuilder.start();
//            stderr = process.getErrorStream();
//            isr = new InputStreamReader(stderr);
//            br = new BufferedReader(isr);
//            while ((line = br.readLine()) != null);
//            {                
//            }
//            process.waitFor();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        
    }

    /*
     * How to use...
     */
    /*
     public static void main(String[] args) throws Exception {
     FLVConverter FLVConverter = new FLVConverter("C:\\Users\\merdok\\IdeaProjects\\dev\\tools\\ffmpeg\\ffmpeg.exe");
     FLVConverter.convert("C:\\filein.mp4", "C:\\fileout.flv", 320, 200, 5);
     }
     */
}
