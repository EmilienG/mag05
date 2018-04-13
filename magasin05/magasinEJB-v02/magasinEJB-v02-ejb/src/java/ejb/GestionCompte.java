package ejb;

import entites.Utilisateur;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import outils.CustomException;

@Stateless
public class GestionCompte implements GestionCompteLocal {
    
    @EJB
    private GestionUtilisateurLocal gestionUtilisateur;
    
    @PersistenceContext(unitName = "magasinEJB-v02-ejbPU")
    private EntityManager em;
    
    
    
    @Override
    public Utilisateur inscrire(String pseudo, String mdp, String mail) throws CustomException {      
        Utilisateur user = gestionUtilisateur.creerNewUtilisateur(mail, pseudo, mdp);
        em.persist(user);
        return user;
    }

    public Utilisateur seConnecter(String pseudo, String mdp) throws CustomException{
        Query qr = em.createNamedQuery("entites.Utilisateur.login");
        qr.setParameter("paramPseudo", pseudo);
        qr.setParameter("paramMdp", mdp);
        try{
            Utilisateur user = (Utilisateur) qr.getSingleResult();
            return user;
        }catch(NoResultException ex){
            throw new CustomException(CustomException.USER_ERR, "compte invalide");
        }
    }
    
    @Override
    public Utilisateur modifier(Utilisateur user){
        user = em.merge(user);
        String pseudo = user.getPseudo()+"-modifié";
        user.setPseudo(pseudo);
        return user;
    }
    
}
