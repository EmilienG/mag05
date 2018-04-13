package controleurs.secondaires;

import ejb.JeuxDEssaisLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreerDonneesCtrl implements Serializable, SousControleurInterface {

    
    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        JeuxDEssaisLocal jeuxDEssais = lookupJeuxDEssaisLocal();
        jeuxDEssais.creerDonnees();
        request.setAttribute("info", "creation du jeu d'essais terminée!");
        return "/WEB-INF/home.jsp";
    }

    private JeuxDEssaisLocal lookupJeuxDEssaisLocal() {
        try {
            Context c = new InitialContext();
            return (JeuxDEssaisLocal) c.lookup("java:global/magasinEJB-v02/magasinEJB-v02-ejb/JeuxDEssais!ejb.JeuxDEssaisLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
