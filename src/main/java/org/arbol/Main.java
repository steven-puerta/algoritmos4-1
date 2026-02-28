package org.arbol;

import org.arbol.classes.lista.Lista;
import org.arbol.classes.nodo.Nodo;
import org.arbol.classes.persona.Persona;

public class Main {
    public static void main(String[] args) {

        System.out.println("Se armó esta vaina");

        // -- Sublista 1 (nodos normales con sw=0) --
        Nodo sub1Nodo2 = new Nodo(0, null, new Persona(4, "Diana", 28), null);
        Nodo sub1Nodo1 = new Nodo(0, null, new Persona(3, "Carlos", 35), sub1Nodo2);

        // -- Sublista 2 (nodos normales con sw=0) --
        Nodo sub2Nodo2 = new Nodo(0, null, new Persona(6, "Fernanda", 22), null);
        Nodo sub2Nodo1 = new Nodo(0, null, new Persona(5, "Eduardo", 40), sub2Nodo2);

        // -- Lista principal --
        // Nodo normal
        Nodo nodo1 = new Nodo(0, null, new Persona(1, "Ana", 30), null);
        // Nodo que apunta a sublista 1 (sw=1)
        Nodo nodo2 = new Nodo(1, sub1Nodo1, null, null);
        // Nodo normal
        Nodo nodo3 = new Nodo(0, null, new Persona(2, "Bruno", 25), null);
        // Nodo que apunta a sublista 2 (sw=1)
        Nodo nodo4 = new Nodo(1, sub2Nodo1, null, null);

        // Encadenamos la lista principal
        nodo1.setLiga(nodo2);
        nodo2.setLiga(nodo3);
        nodo3.setLiga(nodo4);

        // Creamos la lista y probamos mostrarLista
        Lista lista = new Lista(nodo1);

        System.out.println("=== Mostrar Lista ===");
        lista.mostrarLista(lista.getCabeza());

        System.out.println("\n === Lista tras modificar === \n");
        Persona prueba = new Persona(7, "Pepe", 5);
        lista.Registrar(5, prueba);
        lista.mostrarLista(lista.getCabeza());
    }
}