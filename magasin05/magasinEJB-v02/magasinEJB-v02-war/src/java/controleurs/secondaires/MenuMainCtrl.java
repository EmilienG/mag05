package controleurs.secondaires;

import ejb.GestionPanierLocal;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuMainCtrl implements Serializable, SousControleurInterface {

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        GestionPanierLocal gestionPanier = (GestionPanierLocal) session.getAttribute("gestionPanier");
        
        String info = "";
        int nombre = gestionPanier.getNombreArticles();
        info = nombre + " article";
        if (nombre > 1) {
            info += "s";
        }
        request.setAttribute("infoPanier", info);
        float total = gestionPanier.getTotalTTC();
        request.setAttribute("prixTotal", total);
        String url = "/WEB-INF/menus/menu-main.jsp";
        return url;
    }

}
