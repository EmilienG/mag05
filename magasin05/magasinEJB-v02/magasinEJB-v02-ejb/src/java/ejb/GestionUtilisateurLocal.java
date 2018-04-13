/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entites.Utilisateur;
import javax.ejb.Local;
import outils.CustomException;

/**
 *
 * @author pham
 */
@Local
public interface GestionUtilisateurLocal {

    public Utilisateur creerNewUtilisateur(String mail, String pseudo, String mdp) throws CustomException;
    
}
