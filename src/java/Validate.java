import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

/**
 *
 * @author nihal
 */
public class Validate extends HttpServlet {

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
            String name = request.getParameter("Username");
            int voterid=Integer.parseInt(request.getParameter("voterid"));
            String password=request.getParameter("Upassword");
            String address = request.getParameter("Address");
            String cpassword=request.getParameter("cpassword");
            int role=Integer.parseInt(request.getParameter("rolevalue"));
            int Voted=0;
            int temp=0;
            
             String dbDriver = "com.mysql.jdbc.Driver";
             String dbURL = "jdbc:mysql:// localhost:3306/";
             String dbName = "voting";
             String dbUsername = "root";
             String dbPassword = "";
             Statement st=null;
        
            try
            {
                Class.forName(dbDriver);
                Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername,dbPassword);
                st=con.createStatement();
                
                if(password.equals(cpassword))
                {
                PreparedStatement ps = con.prepareStatement("insert into voting1 values(?,?,?,?,?,?)");
                ps.setInt(1, voterid);
                ps.setString(2, name);
                ps.setString(3, password);
                ps.setString(4, address);
                ps.setInt(5, role);
                ps.setInt(6, Voted);
                System.out.println("Data Uploaded" + ps.executeUpdate());
                }
                else
                {
                     temp++;
                     out.println("<html><head></head><body onload=\"alert('Password and Confirm password should be same')\"></body></html>");
                     RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
                     rd.include(request, response);
                }
            }
            catch(Exception e)
            {
                temp++;
                out.println("<html><head></head><body onload=\"alert('Member Already Exist')\"></body></html>");
                RequestDispatcher rd=request.getRequestDispatcher("Register.jsp");
                rd.include(request, response);
            }
             
            if(temp==0)
            response.sendRedirect("Login.jsp"); 
   
            
            
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
