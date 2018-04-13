
package ejb;

import entites.Utilisateur;
import javax.ejb.Local;
import outils.CustomException;


@Local
public interface GestionCompteLocal {

    public Utilisateur inscrire(String pseudo, String mdp, String mail) throws CustomException;

    public Utilisateur seConnecter(String pseudo, String mdp) throws CustomException;

    public Utilisateur modifier(Utilisateur user);
    
}
