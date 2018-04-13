
package ejb;

import entites.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class GestionProduit implements GestionProduitLocal {
    
    @PersistenceContext(unitName = "magasinEJB-v02-ejbPU")
    private EntityManager em;
    
    
    @Override
    public List<Produit> selectAllProduit(){        
        Query qr = em.createNamedQuery("entites.Produit.findAll");
        List<Produit> lp = qr.getResultList();
        return lp;
    }

    
    @Override
    public List<Produit> selectLikeNom(String nom){
        if(nom == null){
            return null;
        }else{
            nom = nom.trim();
        }        
        Query qr = em.createNamedQuery("entites.Produit.findLikeNom");
        qr.setParameter("paramNom", "%"+nom+"%");
        List<Produit> lp = qr.getResultList();
        return lp;
    }
    
}
