
package controleurs.secondaires;

import ejb.GestionCompteLocal;
import entites.Utilisateur;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import outils.CustomException;


public class InscriptionCtrl implements Serializable, SousControleurInterface{
    

    @Override
    public String executer(HttpServletRequest request, HttpServletResponse response) {
        String pseudo = request.getParameter("pseudo");
            String mdp = request.getParameter("mdp");
            String mail=  request.getParameter("mail");
        try {            
            GestionCompteLocal gestionCompte = lookupGestionCompteLocal();
            Utilisateur user = gestionCompte.inscrire(pseudo, mdp, mail);
            String info = "inscription validée. utilisateur : "+user.getPseudo();
            request.setAttribute("info", info);
            return "/WEB-INF/home.jsp";
        } catch (CustomException ex) {
            int num =ex.getNumero();
            String page = "";
//            if(num == CustomException.SQL_ERR){
//                // to do
//                
//                page = "/WEB-INF/pageErreur.jsp";
//            }
            if(num == CustomException.USER_ERR){
                request.setAttribute("pseudoF", pseudo);
                request.setAttribute("mailF", mail);
                request.setAttribute("infoErreur", ex.getMessage());
                HashMap<String, String> mp = ex.getErreurs();
                for(String cle : mp.keySet()){
                    request.setAttribute(cle, mp.get(cle));
                }
                page ="/WEB-INF/inscription-form.jsp";
            }
            return page;
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
