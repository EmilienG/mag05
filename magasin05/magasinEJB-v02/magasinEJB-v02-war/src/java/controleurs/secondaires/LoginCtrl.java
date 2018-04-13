
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
import outils.CustomException;


public class LoginCtrl implements Serializable, SousControleurInterface{
    

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String pseudo = request.getParameter("pseudo");
        String mdp = request.getParameter("mdp");
        GestionCompteLocal gestionCompte = lookupGestionCompteLocal();
        try {
            Utilisateur user = gestionCompte.seConnecter(pseudo, mdp);
            session.setAttribute("user", user);
            String info = "Bonjour  "+user.getPseudo();
            request.setAttribute("info", info);
            //System.out.println("--------------------->>> "+session.getAttribute("user"));
            return "/WEB-INF/home.jsp";
        } catch (CustomException ex) {            
            String infoErr = ex.getMessage();
            request.setAttribute("errLogin", infoErr);
            request.setAttribute("pseudoF", pseudo);
            return "/WEB-INF/login.jsp";
        }
        
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
