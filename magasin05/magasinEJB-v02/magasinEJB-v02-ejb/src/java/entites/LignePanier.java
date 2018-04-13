
package entites;


public class LignePanier {
    
    private Produit produit;
    private int qte;

    public LignePanier() {
    }

    public LignePanier(Produit produit) {
        this.produit = produit;
        qte = 1;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "LignePanier{" + "produit=" + produit + ", qte=" + qte + '}';
    }
    
    public float getPrixHT(){
        return produit.getPrixHT() * qte;
    }
    
    public float getPrixTTC(){
        return produit.getPrixTTC() * qte;
    }
    
}
