/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.mef.gescon.web.ui;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.util.IOUtils;

/**
 *
 * @author JJacobo
 */
@MultipartConfig
public class UploadImage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String temppath;
        try {
            Part file = request.getPart("file");
            String mb = request.getParameter("mb");
            String sec = request.getParameter("sec");
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmssSSS");
            String fileName = "fig" + sec + sdf.format(new Date()) + file.getSubmittedFileName().substring(file.getSubmittedFileName().lastIndexOf("."));
            
            ServletContext context = request.getSession().getServletContext();
            temppath = context.getRealPath("/") + "\\resources\\uploads\\" + mb;
            
            //saving file in fileserver
            File dir = new File(temppath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            
            File f = new File(temppath, fileName);
            try (FileOutputStream fileOutStream = new FileOutputStream(f)) {
                fileOutStream.write(IOUtils.toByteArray(file.getInputStream()));
                fileOutStream.flush();
            }
            
            //sending json to response
            Map<String, String> gson = new LinkedHashMap<>();
            gson.put("link", "/gescon/faces/javax.faces.resource/"+fileName+"?ln=uploads/"+mb);
            String json = new Gson().toJson(gson);
            out.write(json);
        } finally {
            out.flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
