package controleurs.secondaires;

import ejb.GestionProduitLocal;
import entites.Produit;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AfficherCatalogueCtrl implements Serializable, SousControleurInterface {

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        GestionProduitLocal gestionProduit = lookupGestionProduitLocal();
        List<Produit> lp = gestionProduit.selectAllProduit();
        request.setAttribute("catalogue", lp);
        return "/WEB-INF/catalogue.jsp";

    }

    private GestionProduitLocal lookupGestionProduitLocal() {
        try {
            Context c = new InitialContext();
            return (GestionProduitLocal) c.lookup("java:global/magasinEJB-v02/magasinEJB-v02-ejb/GestionProduit!ejb.GestionProduitLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
