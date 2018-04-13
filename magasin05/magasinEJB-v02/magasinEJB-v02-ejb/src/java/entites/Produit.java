package entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
    @NamedQuery(name = "entites.Produit.findAll", query = "select p from Produit p"),
    @NamedQuery(name = "entites.Produit.findLikeNom", query = "select p from Produit p where p.nom like :paramNom")
})
public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String reference;
    private String nom;
    private String description;
    private float prixHT;
    private int stock;
    
    
    @ManyToOne
    private TVA taxe;

    public Produit() {
    }

    public Produit(String reference, String nom, float prixHT, int stock) {
        this.reference = reference;
        this.nom = nom;
        this.prixHT = prixHT;
        this.stock = stock;
    }

    public Produit(String reference, String nom, String description, float prixHT, int stock) {
        this.reference = reference;
        this.nom = nom;
        this.description = description;
        this.prixHT = prixHT;
        this.stock = stock;
    }

    public TVA getTaxe() {
        return taxe;
    }

    public void setTaxe(TVA taxe) {
        this.taxe = taxe;
    }
    
    
    
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(float prixHT) {
        this.prixHT = prixHT;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public float getPrixTTC(){
        float prixTTC = (1 + taxe.getTaux()/100)*prixHT; // à arrondir
        return prixTTC;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reference != null ? reference.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.reference == null && other.reference != null) || (this.reference != null && !this.reference.equals(other.reference))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit[ nom=" + nom+"(ref =" +reference+ ")]";
    }
    
}
