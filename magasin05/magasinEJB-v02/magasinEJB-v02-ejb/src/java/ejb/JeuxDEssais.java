package ejb;

import entites.Produit;
import entites.TVA;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class JeuxDEssais implements JeuxDEssaisLocal {
    
    @PersistenceContext(unitName = "magasinEJB-v02-ejbPU")
    private EntityManager em;
    
    
    
    @Override
    public void creerDonnees(){
        TVA tva01 = new TVA(20f, "tva pour produits courants");
        em.persist(tva01);
        em.flush();
        
        Produit p01 = new Produit("ref-AAA00001", "produit-01","super produit", 5, 7);
        p01.setTaxe(tva01);
        Produit p02 = new Produit("ref-ABB00002", "produit-02", "pas très utile",9.3f, 6);
        p02.setTaxe(tva01);
        Produit p03 = new Produit("ref-EFF000150", "produit-03","très à la mode", 12, 9);
        p03.setTaxe(tva01);
        Produit p04 = new Produit("ref-HJK00002", "produit-04", "tout le monde se l'arrache",11.25f, 11);
        p04.setTaxe(tva01);
        Produit p05 = new Produit("ref-DEF00005", "produit-05","très à la mode", 7.25f, 7);
        p05.setTaxe(tva01);
        Produit p06 = new Produit("ref-REZ00022", "produit-06", "tout le monde se l'arrache",21.23f, 10);
        p06.setTaxe(tva01);
        
        em.persist(p01);
        em.persist(p02);
        em.persist(p03);
        em.persist(p04);
        em.persist(p05);
        em.persist(p06);
        em.flush();
    }

    
    
}
