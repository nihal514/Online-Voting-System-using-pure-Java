
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

public class check extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            int voterid = Integer.parseInt(request.getParameter("Voter_id"));
            String Password = request.getParameter("password");
            int role = Integer.parseInt(request.getParameter("Role"));

            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            String dbName = "voting";
            String dbUsername = "root";
            String dbPassword = "";
            Statement st = null;
            int check = 0;

            try 
            {
                Class.forName(dbDriver);
                Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
                st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from voting1");

                if (!rs.next()) {
                    out.println("<html><head></head><body onload=\"alert('Database is Empty')\"></body></html>");
                    RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                    rd.include(request, response);
                    
                } 
                else 
                {
                    ResultSet rs1 = st.executeQuery("select * from voting1");//bcz we have done re.next one time above
                    while (rs1.next())
                    {
                        int v = rs1.getInt("voter_id");
                        String p = rs1.getString("password");
                        int r = rs1.getInt("Role");

                        if (voterid == v && p.equals(Password) &&role==r && r == 1) 
                        {
                             HttpSession session=request.getSession();  
                             session.setAttribute("voterid",v); 
                             response.sendRedirect("Vote.jsp");
                        }
                        
                        if (voterid == v && p.equals(Password) &&role==r && r == 2) 
                        {
                            HttpSession session=request.getSession();  
                            session.setAttribute("voterid",v); 
                            response.sendRedirect("AdminHome.html");
                        }
                    }
                    rs1.close();
                    if (check == 0) {
                        out.println("<html><head></head><body onload=\"alert('Wrong Details')\"></body></html>");
                        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                        rd.include(request, response);
                    }
                }
                rs.close();
                con.close();

            } catch (Exception e) {
                out.println(e);
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
