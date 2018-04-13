package controleurs;

import controleurs.secondaires.AfficherCatalogueCtrl;
import controleurs.secondaires.CreerDonneesCtrl;
import controleurs.secondaires.MenuMainCtrl;
import controleurs.secondaires.OperationPanierCtrl;
import controleurs.secondaires.SousControleurInterface;
import ejb.GestionPanierLocal;
import ejb.GestionProduitLocal;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FrontControleur extends HttpServlet {
    
    private HashMap<String, SousControleurInterface> mp;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        
        mp = new HashMap<>();
//        mp.put("menu-main", new MenuMainCtrl());
//        mp.put("creer-donnees", new CreerDonneesCtrl());
//        mp.put("afficher-catalogue", new AfficherCatalogueCtrl());
//        mp.put("operations-panier", new OperationPanierCtrl());
        
        Enumeration<String> noms = config.getInitParameterNames();
        while(noms.hasMoreElements()){
            try {
                String n = noms.nextElement();
                String valeur = config.getInitParameter(n);               
                SousControleurInterface ctrl = (SousControleurInterface) Class.forName(valeur).newInstance();
                mp.put(n, ctrl);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
    }
    
    

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();

            String section = request.getParameter("section");
            String page = "/WEB-INF/home.jsp";

            if (session.getAttribute("gestionPanier") == null) {
                session.setAttribute("gestionPanier", lookupGestionPanierLocal());
            }
            GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
            
            if (mp.containsKey(section)) {
                SousControleurInterface cdf = mp.get(section);
                page = cdf.executer(request, response);
            }


            page = response.encodeURL(page);
            Boolean redirect = (Boolean) request.getAttribute("redirect");
            if(redirect !=null && redirect){
                response.sendRedirect(page);
            }else{
                getServletContext().getRequestDispatcher(page).include(request, response);
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
