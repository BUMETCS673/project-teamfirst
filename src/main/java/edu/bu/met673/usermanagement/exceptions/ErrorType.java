package edu.bu.met673.usermanagement.exceptions;

public enum ErrorType {
    FUNCTIONAL("B"),
    SYSTEM("S"),
    VALIDATION("V");

    private String valeur;

    private ErrorType(String valeur){
        this.valeur = valeur;
    }

    public String getValeur(){
        return this.valeur;
    }
}
