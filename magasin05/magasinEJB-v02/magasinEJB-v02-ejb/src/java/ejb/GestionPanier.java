package ejb;

import entites.LignePanier;
import entites.Produit;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateful
public class GestionPanier implements GestionPanierLocal {

    @PersistenceContext(unitName = "magasinEJB-v02-ejbPU")
    private EntityManager em;
    
    private HashMap<String, LignePanier> panier;
    
    @PostConstruct
    public void init(){
        panier = new HashMap<>();
    }

    @Override
    public void add(String reference){
        
        if(reference == null || reference.trim().isEmpty()){
            return ;
        }
        reference = reference.trim();
        
        if(panier.containsKey(reference)){
            LignePanier lg = panier.get(reference);
            int newQte = lg.getQte() + 1;
            if(newQte > lg.getProduit().getStock()){
                newQte = lg.getProduit().getStock();
            }
            lg.setQte(newQte);
        }else{
            Produit p = em.find(Produit.class, reference);
            if(p == null){
                return;
            }else if(p.getStock() >=  1){
                LignePanier lg01 = new LignePanier(p);
                panier.put(reference, lg01);
            }
        }        
    }
    
    @Override
    public Collection<LignePanier> getListe(){
        return panier.values();
    }
    
    @Override
    public float getTotalTTC(){
        Collection<LignePanier> lc = panier.values();
        float total = 0;
        for(LignePanier lg : lc){
            total+= lg.getPrixTTC();
        }
        return total;
    }
    
    @Override
    public int getNombreArticles(){
        Collection<LignePanier> lc = panier.values();
        int nombre = 0;
        for(LignePanier lg : lc){
            nombre+= lg.getQte();
        }
        return nombre;
    }
    
}
