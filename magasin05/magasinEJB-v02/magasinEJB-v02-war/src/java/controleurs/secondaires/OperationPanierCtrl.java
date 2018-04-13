package controleurs.secondaires;

import ejb.GestionPanierLocal;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OperationPanierCtrl implements Serializable, SousControleurInterface {

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        String action = request.getParameter("action");
        String ref = request.getParameter("ref");
        String page = ""; // parfois il faut une page par defaut
        if ("add".equals(action)) {
            gestionPanier.add(ref);
            request.setAttribute("redirect", true);
            page = "FrontControleur?section=afficher-catalogue";
        }
        if ("delete".equals(action)) {
            // .....
        }
        return page;
    }

}
