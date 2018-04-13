package controleurs;

import ejb.GestionPanierLocal;
import ejb.GestionProduitLocal;
import ejb.JeuxDEssaisLocal;
import entites.Produit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControleurOld", urlPatterns = {"/ControleurOld"})
public class ControleurOld extends HttpServlet {
       
    @EJB
    private GestionProduitLocal gestionProduit;
    
    @EJB
    private JeuxDEssaisLocal jeuxDEssais;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String section = request.getParameter("section");
        String page = "/WEB-INF/home.jsp";
        
        if(session.getAttribute("gestionPanier")== null){
            session.setAttribute("gestionPanier", lookupGestionPanierLocal());
        }        
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");

        if ("menu-main".equals(section)) {
            String info="";
            int nombre = gestionPanier.getNombreArticles();
            info = nombre +" article";
            if(nombre > 1){
                info += "s";
            }
            request.setAttribute("infoPanier", info);
            float total = gestionPanier.getTotalTTC();
            request.setAttribute("prixTotal", total);
            page = "/WEB-INF/menus/menu-main.jsp";
        }
        
        if("creer-donnees".equals(section)){
            jeuxDEssais.creerDonnees();
            request.setAttribute("info", "creation du jeu d'essais terminée!");
            page = "/WEB-INF/home.jsp";
        }
        
        if("afficher-catalogue".equals(section)){
            List<Produit> lp = gestionProduit.selectAllProduit();
            request.setAttribute("catalogue", lp);
            page = "/WEB-INF/catalogue.jsp";
        }
        
        if("operations-panier".equals(section)){
            String action = request.getParameter("action");
            String ref = request.getParameter("ref");
            if("add".equals(action)){
                gestionPanier.add(ref);                
                page = "FrontControleur?section=afficher-catalogue";
                page = response.encodeURL(page);
                response.sendRedirect(page);
                return;
            }
            if("delete".equals(action)){
                // .....
            }
        }

        page = response.encodeURL(page);
        getServletContext().getRequestDispatcher(page).include(request, response);

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

    private GestionPanierLocal lookupGestionPanierLocal() {
        try {
            Context c = new InitialContext();
            return (GestionPanierLocal) c.lookup("java:global/magasinEJB-v02/magasinEJB-v02-ejb/GestionPanier!ejb.GestionPanierLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
