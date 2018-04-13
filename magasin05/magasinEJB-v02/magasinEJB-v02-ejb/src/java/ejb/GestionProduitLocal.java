
package ejb;

import entites.Produit;
import java.util.List;
import javax.ejb.Local;


@Local
public interface GestionProduitLocal {
    public List<Produit> selectLikeNom(String nom);
    public List<Produit> selectAllProduit();
}
