package outils;

import java.util.HashMap;

public class CustomException extends Exception{
    public static final int USER_ERR = 10;
    public static final int SQL_ERR = 20;
    
    private int numero;
    private HashMap<String, String> erreurs;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(int numero, String message) {
        super(message);
        this.numero = numero;
    }
    
    
    
    public CustomException(int numero, HashMap<String, String> erreurs, String message) {
        super(message);
        this.numero = numero;
        this.erreurs = erreurs;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public HashMap<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(HashMap<String, String> erreurs) {
        this.erreurs = erreurs;
    }
    
    
    
    
}
