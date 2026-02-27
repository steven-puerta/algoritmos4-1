package org.arbol.classes.nodo;

import org.arbol.classes.persona.Persona;

public class Nodo {

    private int sw;
    private Nodo ligaLista;
    private Persona persona;
    private Nodo liga;

    public Nodo(int sw, Nodo ligaLista, Persona persona, Nodo liga) {
        this.sw = sw;
        this.ligaLista = ligaLista;
        this.persona = persona;
        this.liga = liga;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public Nodo getLigaLista() {
        return ligaLista;
    }

    public void setLigaLista(Nodo ligaLista) {
        this.ligaLista = ligaLista;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "sw=" + sw +
                ", ligaLista=" + ligaLista +
                ", persona=" + persona +
                ", liga=" + liga +
                '}';
    }
}
