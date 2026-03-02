package org.arbol.classes.lista;

import org.arbol.classes.nodo.Nodo;
import org.arbol.classes.persona.Persona;

public class Lista {

    private Nodo cabeza;

    public Lista() {
        this.cabeza = null;
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

    public boolean Registrar (int padre, Persona persona) {
        Nodo nodoPadre = buscar(padre, cabeza);
        if (nodoPadre == null) {
            return false;
        }
        Nodo nuevo = new Nodo(persona);
        nuevo.setLiga(nodoPadre.getLiga());
        nodoPadre.setLiga(nuevo);
        return true;
    }

    public void mostrarLista (Nodo actual) {

        if (actual == null) {
            System.out.println("La lista está vacía");
        }
        while (actual != null) {
            if (actual.getSw() == 0) {
                System.out.println(actual.getPersona().toString());
            } else {
                mostrarLista(actual.getLigaLista());
            }
            actual = actual.getLiga();
        }
    }

    private Nodo buscar(int id, Nodo nodo) {
        Nodo aux = nodo;
        if (aux == null) {
            System.out.println("No se encontró el nodo buscado");
            return null;
        }

        Nodo recursivo = null;

        while (aux != null) {
            if (aux.getSw() == 0) {
                if (aux.getPersona().getId() == id) {
                    return aux;
                }
            } else {
                recursivo = buscar(id, aux.getLigaLista());
                if (recursivo != null) {
                    return recursivo;
                }
            }
            aux = aux.getLiga();
        }

        return recursivo;
    }
}
