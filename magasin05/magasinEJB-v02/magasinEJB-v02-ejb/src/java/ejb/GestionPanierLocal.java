
package ejb;

import entites.LignePanier;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface GestionPanierLocal {
    
    public void add(String reference);

    public Collection<LignePanier> getListe();

    public float getTotalTTC();

    public int getNombreArticles();
}
