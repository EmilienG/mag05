
package controleurs.secondaires;

import ejb.GestionCompteLocal;
import entites.Utilisateur;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ModifUserCtrl implements Serializable, SousControleurInterface{
    GestionCompteLocal gestionCompte = lookupGestionCompteLocal();

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        //System.out.println(">>>>>>>>>>> user : "+user);
        if(user != null){
            user = gestionCompte.modifier(user);
            session.setAttribute("user", user);
            request.setAttribute("info", "après modif : "+user.getPseudo());
        }
        
        return "/WEB-INF/home.jsp";
    
    }

    private GestionCompteLocal lookupGestionCompteLocal() {
        try {
            Context c = new InitialContext();
            return (GestionCompteLocal) c.lookup("java:global/magasinEJB-v02/magasinEJB-v02-ejb/GestionCompte!ejb.GestionCompteLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
