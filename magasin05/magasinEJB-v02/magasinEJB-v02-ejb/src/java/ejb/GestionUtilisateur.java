package ejb;

import entites.Utilisateur;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import outils.CustomException;

@Stateless
public class GestionUtilisateur implements GestionUtilisateurLocal {

    @PersistenceContext(unitName = "magasinEJB-v02-ejbPU")
    private EntityManager em;

    @Override
    public Utilisateur creerNewUtilisateur(String mail, String pseudo, String mdp) throws CustomException {
        HashMap<String, String> mp = new HashMap<>();
        if (mail == null || mail.trim().isEmpty()) {
            mp.put("errMail", "email obligatoire");
        } else {
            mail = mail.trim();
            String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
            if (!mail.matches(regex)) {
                mp.put("errMail", "email invalide");
            } else {
                Query qr = em.createNamedQuery("entites.Utilisateur.findByEmail");
                qr.setParameter("param", mail);
                try {
                    qr.getSingleResult();
                    mp.put("errMail", "email déjà utilisé");
                } catch (NoResultException ex) {

                }
            }
        }

        if (pseudo == null || pseudo.trim().isEmpty()) {
            mp.put("errPseudo", "pseudo obligatoire");
        } else {
            pseudo = pseudo.trim();
            Query qr = em.createNamedQuery("entites.Utilisateur.findByPseudo");
            qr.setParameter("param", pseudo);
                try {
                    qr.getSingleResult();
                    mp.put("errPseudo", "pseudo déjà utilisé");
                } catch (NoResultException ex) {

                }
        }

        if (mdp == null) {
            mp.put("errMdp", "mot de passe obligatoire");
        } else {
            if (mdp.length() < 8) {
                mp.put("errMdp", "mot de passe d'au moins 8 caracteres");
            }
        }
        
        if(!mp.isEmpty()){
            CustomException ce = new CustomException("echec de creation du compte");
            ce.setNumero(CustomException.USER_ERR);
            ce.setErreurs(mp);
            throw ce;
        }
        Utilisateur user = new Utilisateur(pseudo, mail, mdp);
        return user;
    }

}
