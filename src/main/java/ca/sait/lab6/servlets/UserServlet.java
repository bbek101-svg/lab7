
package ca.sait.lab6.servlets;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import ca.sait.lab6.services.RoleService;
import ca.sait.lab6.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class UserServlet extends HttpServlet {

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
        UserService service = new UserService();
        String query = request.getQueryString();
        if(query != null && query.contains("delete")){
           
         String email = query.substring(7);
            try {
                service.delete(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if(query != null && query.contains("edit")){
            String email = query.substring(5);
            try {
                User user = service.get(email);
                request.setAttribute("email", email);
                request.setAttribute("password", user.getPassword());
                request.setAttribute("fname", user.getFirstName());
                request.setAttribute("lname", user.getLastName());
                request.setAttribute("roles", user.getRole().getName());
                
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        try {
            List<User> users = service.getAll();
            request.setAttribute("users", users);
            
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
      
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
        String action = request.getParameter("action");
         RoleService roleservice = new RoleService();
         UserService service = new UserService();
        if(action != null && action.equals("add")){
            String email = request.getParameter("email");
            String firstName = request.getParameter("fname");
             String lastName = request.getParameter("lname");
              String password = request.getParameter("password");
              String active = request.getParameter("active");
              String role = request.getParameter("role");
              
               List<Role> roles = null;
               Role ro = null;
            try {
             roles  = roleservice.getAll();
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
              for(Role rol: roles){
                  if((rol.getName()).equals(role)){
                      ro = new Role(rol.getId(), role);
                  }
              }
              boolean isActive;
              isActive = active != null;
              
            try {
                service.insert(email, isActive, firstName, lastName, password, ro);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
              
        }
        
        if(action != null && action.equals("update")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String roleName = request.getParameter("role");
            List<Role> roles = null;
               Role ro = null;
               System.out.println(email);
            try {
             roles  = roleservice.getAll();
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
              for(Role rol: roles){
                  if((rol.getName()).equals(roleName)){
                      ro = new Role(rol.getId(), roleName);
                  }
              }
              
            try {
                service.update(email, true, firstName, lastName, password, ro);
                        } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            List<User> users = service.getAll();
            request.setAttribute("users", users);
            
             getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
