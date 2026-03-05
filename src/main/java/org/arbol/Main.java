package org.arbol;

import org.arbol.classes.lista.Lista;
import org.arbol.classes.nodo.Nodo;
import org.arbol.classes.persona.Persona;

public class Main {
    public static void main(String[] args) {

        System.out.println("Se armó esta vaina");

        Nodo sub1Nodo2 = new Nodo(0, null, new Persona(4, "Diana", 28), null);
        Nodo sub1Nodo1 = new Nodo(0, null, new Persona(3, "Carlos", 35), sub1Nodo2);

        Nodo sub2Nodo2 = new Nodo(0, null, new Persona(6, "Fernanda", 22), null);
        Nodo sub2Nodo1 = new Nodo(0, null, new Persona(5, "Eduardo", 40), sub2Nodo2);

        Nodo nodo1 = new Nodo(0, null, new Persona(1, "Ana", 30), null);
        Nodo nodo2 = new Nodo(1, sub1Nodo1, null, null);
        Nodo nodo3 = new Nodo(0, null, new Persona(2, "Bruno", 25), null);
        Nodo nodo4 = new Nodo(1, sub2Nodo1, null, null);

        nodo1.setLiga(nodo2);
        nodo2.setLiga(nodo3);
        nodo3.setLiga(nodo4);

        // Creamos la lista y probamos mostrarLista
        Lista lista = new Lista(nodo1);

        System.out.println("=== Mostrar Lista ===");
        lista.mostrarArbol();

        System.out.println("\n === Lista tras modificar === \n");
        Persona prueba1 = new Persona(8, "Pepe", 5);
        lista.Registrar(5, prueba1);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras modificar de nuevo === \n");
        Persona prueba2 = new Persona(7, "Mario", 3);
        lista.Registrar(5, prueba2);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras modificar la última vez === \n");
        Persona prueba3 = new Persona(9, "Jacinta", 10);
        lista.Registrar(5, prueba3);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras bisnieto1 === \n");
        Persona prueba4 = new Persona(12, "Cínforosa", 3);
        lista.Registrar(6, prueba4);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras bisnieto2 === \n");
        Persona prueba5 = new Persona(11, "Anacrespe", 3);
        lista.Registrar(6, prueba5);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras tataranieto === \n");
        Persona prueba6 = new Persona(13, "Marimar", 2);
        lista.Registrar(11, prueba6);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras tataratataranieto === \n");
        Persona prueba7 = new Persona(14, "Juanito", 1);
        lista.Registrar(13, prueba7);
        lista.mostrarArbol();

        System.out.println("\n === Lista tras actualizar dato === \n");
        lista.Actualizar(7, "Mario Ernesto", 5, "nombre");
        lista.mostrarArbol();

        System.out.println("\n === Mostrar Hermanos === \n");
        System.out.println("--- Buscando hermanos de Fernanda (id: 6) ---");
        lista.mostrarHermanos(6);

        System.out.println("\n--- Buscando hermanos de Bruno (id: 2) ---");
        lista.mostrarHermanos(2);

        System.out.println("\n === Mostrar Ancestros === \n");
        System.out.println("--- Buscando ancestros de Eduardo (id: 5) ---");
        lista.mostrarAncestros(5);

        System.out.println("\n === Nodo de Mayor Grado === \n");
        lista.mostrarMayorGrado();

        System.out.println("\n === Mayor Nivel === \n");
        lista.mostrarMayorNivel();
    }
}