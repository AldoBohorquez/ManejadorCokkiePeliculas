/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author swoke
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro_servlet"})
public class RegistroServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String nombre = escapeJson(request.getParameter("nombre"));
        String descripcion = escapeJson(request.getParameter("descripcion"));
        String fecha = escapeJson(request.getParameter("fecha"));
        String hora = escapeJson(request.getParameter("hora"));
        String precio = escapeJson(request.getParameter("precio"));

        try {
            String datosPeliculaJson = "{"
                    + "\"nombre\":\"" + nombre + "\","
                    + "\"descripcion\":\"" + descripcion + "\","
                    + "\"fecha\":\"" + fecha + "\","
                    + "\"hora\":\"" + hora + "\","
                    + "\"precio\":\"" + precio + "\""
                    + "}";

            String datosPeliculaEncoded = URLEncoder.encode(datosPeliculaJson, "UTF-8");

            Cookie cookie = new Cookie("datosPelicula", datosPeliculaEncoded);

            if (request.getParameter("chk_recordar") != null) {
                cookie.setMaxAge(60 * 60 * 24); 
            } else {
                cookie.setMaxAge(60 * 5); 
            }

            cookie.setPath("/");
            response.addCookie(cookie);

            request.getRequestDispatcher("/jsp/verificar.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error al crear la cookie: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r") 
                .replace("\t", "\\t");
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
