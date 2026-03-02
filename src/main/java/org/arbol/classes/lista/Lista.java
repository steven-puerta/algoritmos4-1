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

    public int alturaArbol(){
        return alturaArbol(getCabeza());
    }

    private int alturaArbol(Nodo actual){
        int altura = 0;
        int alturaSubarbol = 0;
        int maxAlturaSubarbol = 0;
        if(actual != null){
            while(actual != null){
                // Si sw=1, este nodo es un puente a una sublista (un hijo con sus propios hijos)
                // Se calcula recursivamente la altura de esa sublista
                if(actual.getSw() != 0){
                    alturaSubarbol = alturaArbol(actual.getLigaLista());
                    // Se guarda solo la mayor altura encontrada entre todas las sublistas
                    if(alturaSubarbol > maxAlturaSubarbol){
                        maxAlturaSubarbol = alturaSubarbol;
                    }
                }
                // sw=0: nodo hoja, no aporta profundidad adicional, se omite
                actual = actual.getLiga();
            }
            // El nivel actual (el padre de esta lista) suma 1
            altura = 1 + maxAlturaSubarbol;
        }
        return altura;
    }

    public int nivelRegistro (int id){
        return nivelRegistro(id, getCabeza(), 1);
    }

    private int nivelRegistro(int id, Nodo actual, int nivelActual) {
        if (actual == null) return 0;

        // El primer nodo de cualquier sublista es siempre el padre → nivel actual
        if (actual.getSw() == 0 && actual.getPersona().getId() == id) {
            return nivelActual;
        }

        // A partir del segundo nodo, todos son hijos → nivel actual + 1
        actual = actual.getLiga();
        while (actual != null) {
            if (actual.getSw() == 0) {
                // Hijo simple (hoja): está en nivelActual + 1
                if (actual.getPersona().getId() == id) {
                    return nivelActual + 1;
                }
            } else {
                // sw=1: puente a sublista. Su primer nodo (padre) estará en nivelActual+1
                int nivel = nivelRegistro(id, actual.getLigaLista(), nivelActual + 1);
                if (nivel != 0) return nivel; // retorno temprano si se encontró
            }
            actual = actual.getLiga();
        }
        return 0; // no encontrado en esta rama
    }

    public void registrosNivel(int nivelObjetivo) {
        registrosNivel(nivelObjetivo, getCabeza(), 1);
    }

    private void registrosNivel(int nivelObjetivo, Nodo actual, int nivelActual){
        // Caso especial: si buscamos el nivel 1 y estamos en nivel 1,
        // el primer nodo (la raíz) se imprime directamente
        if(nivelObjetivo == 1 && nivelActual == 1){
            System.out.println(actual.getPersona().toString());
        }
        // Avanzamos al segundo nodo (primer hijo)
        actual = actual.getLiga();
        while (actual != null){
            if (nivelActual+1 == nivelObjetivo){
                if(actual.getSw() != 0){
                    // sw=1: el nodo objetivo es el padre de esta sublista (primer nodo)
                    // Se imprime directamente sin recursar toda la sublista → mas eficiente que usar recursividad para
                    // solo imprimir el primer nodo
                    System.out.println(actual.getLigaLista().getPersona().toString());
                }else{
                    // sw=0: el hijo es una hoja, se imprime directamente
                    System.out.println(actual.getPersona().toString());
                }
            }else{
                // Aún no llegamos al nivel objetivo
                if(actual.getSw() != 0){
                    // Descender a la sublista sumando un nivel
                    registrosNivel(nivelObjetivo, actual.getLigaLista(), nivelActual+1);
                }
                // sw=0 y no es el nivel: es un hijo que no tiene descendencia, se ignora
            }
            actual = actual.getLiga();
        }
    }
    public boolean eliminarNivel(int nivelObjetivo){
        return eliminarNivel(nivelObjetivo, getCabeza(), 1);
    }

    private boolean eliminarNivel(int nivelObjetivo, Nodo actual, int nivelActual){
        boolean nivelEliminado = false;
        Nodo anterior = actual; // guarda el nodo previo para reenlazar
        //En la estructura de lista generalizada actual no puede existir mas de una raiz por lo que eliminar la raiz
        //seria dividir la lista en multiples listas
        if(nivelObjetivo == 1 && nivelActual == 1){
            System.out.println("No se puede eliminar el primer nivel");
            return false;
        }
        actual = actual.getLiga(); // salta al primer hijo
        while (actual != null){
            if (nivelActual+1 == nivelObjetivo){
                if(actual.getSw() != 0){
                    // El nodo a eliminar es el padre de esta sublista (primer nodo de getLigaLista())
                    // Sus hijos (getLigaLista().getLiga()) ascienden al nivel padre
                    Nodo sublista = actual.getLigaLista().getLiga(); // primer hijo del nodo eliminado
                    anterior.setLiga(sublista); // el anterior ahora apunta a los hijos promovidos

                    // Recorre hasta el último hijo promovido para reconectar la cadena
                    while (sublista.getLiga() != null){
                        sublista = sublista.getLiga();
                    }
                    sublista.setLiga(actual.getLiga()); // reconecta el resto de la lista padre
                    actual = sublista;
                    // Se mueve el actual a el ultimo hijo para reprosesarlos y por ende eliminarlos
                }else{
                    // Nodo hoja en el nivel objetivo: se desconecta
                    anterior.setLiga(actual.getLiga());
                    actual = anterior; // se retrocede el actual para que la variable de anterior no quede en el nodo
                                       // desconectado en la siguiente iteracion
                }
                nivelEliminado = true;
            }else{
                if(actual.getSw() != 0){
                    // Descender a la sublista a buscar el nivel objetivo
                    nivelEliminado = eliminarNivel(nivelObjetivo, actual.getLigaLista(), nivelActual+1);
                }
            }
            anterior = actual;
            actual = actual.getLiga();
        }
        return nivelEliminado;
    }
}
