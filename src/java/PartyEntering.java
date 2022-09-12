/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 *
 * @author amans
 */
public class PartyEntering extends HttpServlet {

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
            out.println("<title>Servlet PartyEntering</title>");
            out.println("</head>");
            out.println("<body>");

            String pname = request.getParameter("partyname");
            String lname = request.getParameter("leadername");
            String plogo = request.getParameter("partylogo");

            int votes = 0;
            //for database connection
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "voting";
            String dbUsername = "root";
            String dbPassword = "";
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {

                Class.forName(dbDriver);
                con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
                st = con.createStatement();

                PreparedStatement ps = con.prepareStatement("insert into party1 values(?,?,?,?)");
                ps.setInt(1, votes);
                ps.setString(2, lname);

                //creating ServletContext object  
                ServletContext context = getServletContext();
                //Getting the value of the initialization parameter which is path of folder in which image is present
                //if you want to change it you can change it from web.xml
                String imageFolderPath = context.getInitParameter("imagepath");
                
                String path = imageFolderPath  + plogo;
                FileInputStream fs = new FileInputStream(path); // path of photo
                ps.setBinaryStream(3, fs, fs.available());
                ps.setString(4, pname);
                
                System.out.println("PHOTO UPLOADED " + ps.executeUpdate());

                out.println("<h2 style='color:green;'>New Party Entered Succesfully </h2>");
                out.println("</body>");
                out.println("</html>");
                RequestDispatcher rd = request.getRequestDispatcher("AdminSeeVotes.jsp");
                rd.include(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<html><head></head><body onload=\"alert('Party Already Exist')\"></body></html>");
                RequestDispatcher rd = request.getRequestDispatcher("AdminEnterNewParty.jsp");
                rd.include(request, response);
            }

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
