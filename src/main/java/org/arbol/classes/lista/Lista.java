package org.arbol.classes.lista;

import org.arbol.classes.nodo.Nodo;

public class Lista {

    private Nodo cabeza;

    public Lista() {
    }

    public Lista(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
}
