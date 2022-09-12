/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amans
 */
public class PhotoServlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
       
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            final String DB_URL = "jdbc:mysql://localhost:3306/voting";
            final String User = "root";
            final String Password = "";
            try 
            {
//                out.println("\n\nyaaaaaaaaaaaaaahooooooooooooooooooooo\n\n");
                Class.forName(JDBC_DRIVER);
                Connection conn = DriverManager.getConnection(DB_URL, User, Password);

                PreparedStatement stmt = conn.prepareStatement("select partylogo from party1 where partyname=?");
                String n=(String)request.getParameter("partyname");
//                out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaHooooooooooooooooooooooooooo :"+n);
                stmt.setString(1, n);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) 
                {
                    response.getOutputStream().write(rs.getBytes("partylogo"));
                }
                
                conn.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
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
