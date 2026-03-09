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
        if (nodoPadre.getSw() == 1) {
            nodoPadre = nodoPadre.getLigaLista();
        } else {
            Nodo nuevaCabeza = new Nodo(0, null, nodoPadre.getPersona(), nuevo);
            nodoPadre.setSw(1);
            nodoPadre.setLigaLista(nuevaCabeza);
            nodoPadre.setPersona(null);
            return true;
        }
        if (nodoPadre.getLiga() == null) {
            nodoPadre.setLiga(nuevo);
            return true;
        }
        Nodo iterador = nodoPadre.getLiga();
        Nodo anterior = nodoPadre;
        while (iterador != null) {
            int idIterador = (iterador.getSw() == 0) ? iterador.getPersona().getId() : iterador.getLigaLista().getPersona().getId();
            if (persona.getId() < idIterador) {
                nuevo.setLiga(iterador);
                anterior.setLiga(nuevo);
                return true;
            } else {
                if (iterador.getLiga() == null) {
                    iterador.setLiga(nuevo);
                    return true;
                }
            }
            anterior = iterador;
            iterador = iterador.getLiga();
        }
        return true;
    }

    public boolean Actualizar (int id, String nombre, int edad, String campoACambiar ) {
        Nodo nodo = buscar(id, cabeza);
        if (nodo == null) {
            return false;
        }
        if (nodo.getSw() == 1) {
            nodo = nodo.getLigaLista();
        }
        switch (campoACambiar) {
            case "nombre":
                nodo.getPersona().setNombre(nombre);
                break;
            case "edad":
                nodo.getPersona().setEdad(edad);
                break;
        }
        return true;
    }

    //Implementación anterior
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

    public void mostrarArbol() {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        // 1. La cabeza principal (Ana) es el primer padre.
        System.out.println(formatearNodo(cabeza));

        // 2. Sus hijos son los que están en su cadena de ligas.
        if (cabeza.getLiga() != null) {
            mostrarArbolRecursivo(cabeza.getLiga(), "");
        }
    }

    private void mostrarArbolRecursivo(Nodo actual, String prefijo) {
        while (actual != null) {
            // Determinamos si este nodo tiene hermanos después en esta misma lista
            boolean esUltimo = (actual.getLiga() == null);
            String conector = esUltimo ? "└── " : "├── ";
            String nuevoPrefijo = prefijo + (esUltimo ? "    " : "│   ");

            if (actual.getSw() == 0) {
                System.out.println(prefijo + conector + formatearNodo(actual));
            }
            else {
                Nodo cabezaSub = actual.getLigaLista();
                if (cabezaSub != null) {
                    System.out.println(prefijo + conector + formatearNodo(cabezaSub));
                    if (cabezaSub.getLiga() != null) {
                        mostrarArbolRecursivo(cabezaSub.getLiga(), nuevoPrefijo);
                    }
                }
            }

            // Aquí no aumentamos el prefijo porque están al mismo nivel.
            actual = actual.getLiga();
        }
    }

    private String formatearNodo(Nodo n) {
        if (n == null || n.getPersona() == null) return "N/A";
        Persona p = n.getPersona();
        return "(" + p.getId() + " | " + p.getNombre() + " | " + p.getEdad() + ")";
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
                if (aux.getLigaLista().getPersona().getId() == id) {
                    return aux;
                }
                recursivo = buscar(id, aux.getLigaLista());
                if (recursivo != null) {
                    return recursivo;
                }
            }
            aux = aux.getLiga();
        }

        return recursivo;
    }

    public void mostrarPadre (int id) {
        if (id == getCabeza().getPersona().getId()) {
            System.out.println("La persona buscada es la raíz, por lo tanto no tiene padre en la lista");
        }
        mostrarPadre(id, getCabeza().getLiga(), getCabeza());
    }

    private void mostrarPadre (int id, Nodo actual, Nodo padre) {
        if (actual == null) {
            System.out.println("No se encontró el nodo buscado debajo del padre " + padre.getPersona().getNombre());
        }
        while (actual != null) {
            if (actual.getSw() == 0) {
                if (actual.getPersona().getId() == id) {
                    System.out.println("El nodo padre de " + id + " es " + padre.getPersona().getNombre());
                }
            } else {
                if (actual.getLigaLista().getPersona().getId() == id) {
                    System.out.println("El nodo padre de " + id + " es " + padre.getPersona().getNombre());
                } else {
                    mostrarPadre(id, actual.getLigaLista().getLiga(), actual.getLigaLista());
                }
            }
            actual = actual.getLiga();
        }
    }

    public void mostrarHijos (int id) {
        if (id == getCabeza().getPersona().getId()) {
            Nodo aux = getCabeza().getLiga();
            if (aux == null) {
                System.out.println("La raíz no tiene hijos");
            }
            while (aux != null) {
                if (aux.getSw() == 0) {
                    System.out.println(aux.getPersona().toString());
                } else {
                    System.out.println(aux.getLigaLista().getPersona().toString());
                }
                aux = aux.getLiga();
            }
        } else {
            Nodo buscado = buscar(id, cabeza);
            if (buscado != null) {
                if (buscado.getSw() == 0) {
                    System.out.println("La persona solicitada no tiene hijos");
                } else {
                    Nodo aux = buscado.getLigaLista().getLiga();
                    while (aux != null) {
                        if (aux.getSw() == 0) {
                            System.out.println(aux.getPersona().toString());
                        } else {
                            System.out.println(aux.getLigaLista().getPersona().toString());
                        }
                        aux = aux.getLiga();
                    }
                }
            }
        }
    }

    public int alturaArbol(){
        return alturaArbol(getCabeza());
    }

    private int alturaArbol(Nodo actual){
        int altura = 0;
        int alturaSubarbol = 0;
        int maxAlturaSubarbol = 0;
        if(actual != null){
            if (actual.getLiga() != null){
                maxAlturaSubarbol = 1;
            }
            while(actual != null){
                // Si sw=1, este nodo es un puente a una sublista (un hijo con sus propios hijos)
                // Se calcula recursivamente la altura de esa sublista
                if(actual.getSw() != 0){
                    alturaSubarbol = alturaArbol(actual.getLigaLista());
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
        if(nivelObjetivo == 1){
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

                    Nodo ultimoHijo = sublista;

                    // Recorre hasta el último hijo promovido para reconectar la cadena
                    while (ultimoHijo.getLiga() != null){
                        ultimoHijo = ultimoHijo.getLiga();
                    }
                    ultimoHijo.setLiga(actual.getLiga()); // reconecta el resto de la lista padre
                    actual = ultimoHijo;
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

                    if (actual.getLigaLista().getLiga() == null) {
                        // Recuperar la persona del primer nodo de la sublista (el propio padre)
                        anterior.setLiga(actual.getLigaLista());
                        actual.getLigaLista().setLiga(actual.getLiga());
                    }
                }
            }
            anterior = actual;
            actual = actual.getLiga();
        }
        return nivelEliminado;
    }

    public void mostrarHermanos(int id) {
        mostrarHermanos(id, getCabeza());
    }

    private void mostrarHermanos(int id, Nodo nodoPadre) {
        if (nodoPadre == null) {
            return;
        }

        Nodo iter = nodoPadre.getLiga();
        boolean encontrado = false;

        // Primera pasada: verificar si el ID buscado es hijo directo de nodoPadre
        while (iter != null) {
            int idActual = (iter.getSw() == 0) ? iter.getPersona().getId() : iter.getLigaLista().getPersona().getId();

            if (idActual == id) {
                encontrado = true;
                break;
            }
            iter = iter.getLiga();
        }

        if (encontrado) {
            System.out.println("Hermanos del nodo " + id + " (Hijos de " + nodoPadre.getPersona().getNombre() + "):");
            iter = nodoPadre.getLiga();
            while (iter != null) {
                int idActual = (iter.getSw() == 0) ? iter.getPersona().getId() : iter.getLigaLista().getPersona().getId();
                String info = (iter.getSw() == 0) ? iter.getPersona().toString() : iter.getLigaLista().getPersona().toString();

                if (idActual != id) {
                    System.out.println(info);
                }
                iter = iter.getLiga();
            }
        } else {
            // Si no se encontró en este nivel, buscar recursivamente en las sublistas
            iter = nodoPadre.getLiga();
            while (iter != null) {
                if (iter.getSw() != 0) {
                    mostrarHermanos(id, iter.getLigaLista());
                }
                iter = iter.getLiga();
            }
        }
    }

    public void mostrarAncestros(int id) {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        if (cabeza.getSw() == 0 && cabeza.getPersona().getId() == id) {
            System.out.println("El nodo es la raíz, no tiene ancestros.");
            return;
        }
        if (!mostrarAncestros(id, cabeza)) {
            System.out.println("No se encontró el nodo con id: " + id);
        }
    }

    private boolean mostrarAncestros(int id, Nodo padre) {
        if (padre == null) return false;

        Nodo iter = padre.getLiga();
        while (iter != null) {
            int idActual = (iter.getSw() == 0) ? iter.getPersona().getId() : iter.getLigaLista().getPersona().getId();
            if (idActual == id) {
                System.out.println(padre.getPersona().toString());
                return true;
            }
            if (iter.getSw() != 0) {
                if (mostrarAncestros(id, iter.getLigaLista())) {
                    System.out.println(padre.getPersona().toString());
                    return true;
                }
            }
            iter = iter.getLiga();
        }
        return false;
    }

    public void mostrarMayorGrado() {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        // Usamos arreglos de 1 posición para simular paso por referencia
        Nodo[] maxNodo = new Nodo[1];
        int[] maxGrado = new int[1];
        maxGrado[0] = -1;

        buscarMayorGrado(cabeza, maxNodo, maxGrado);

        if (maxNodo[0] != null) {
            System.out.println("El nodo con mayor grado es: " + maxNodo[0].getPersona().getNombre() +
                    " con grado: " + maxGrado[0]);
        }
    }

    private void buscarMayorGrado(Nodo actual, Nodo[] maxNodo, int[] maxGrado) {
        if (actual == null) return;

        // Calcular grado del nodo actual (contar hijos en la lista enlazada)
        int contador = 0;
        Nodo iter = actual.getLiga();
        while (iter != null) {
            contador++;
            iter = iter.getLiga();
        }

        // Actualizamos si encontramos un grado mayor estricto (mantiene el primero encontrado en caso de empate)
        if (contador > maxGrado[0]) {
            maxGrado[0] = contador;
            maxNodo[0] = actual;
        }

        // Recorrido recursivo a los hijos que son sublistas
        iter = actual.getLiga();
        while (iter != null) {
            if (iter.getSw() == 1) {
                buscarMayorGrado(iter.getLigaLista(), maxNodo, maxGrado);
            }
            iter = iter.getLiga();
        }
    }

    public void mostrarMayorNivel() {
        if (cabeza == null) {
            System.out.println("La lista está vacía");
            return;
        }
        int[] maxNivel = new int[1];
        maxNivel[0] = -1;
        Nodo[] maxNodo = new Nodo[1];

        buscarMayorNivel(cabeza, 1, maxNivel, maxNodo);

        if (maxNodo[0] != null) {
            System.out.println("El nodo en el mayor nivel (" + maxNivel[0] + ") es: " + maxNodo[0].getPersona().toString());
        }
    }

    private void buscarMayorNivel(Nodo actual, int nivelActual, int[] maxNivel, Nodo[] maxNodo) {
        if (actual == null) return;

        // El nodo 'actual' es la cabeza de este nivel (Padre), actualizamos si es el mayor encontrado
        if (nivelActual > maxNivel[0]) {
            maxNivel[0] = nivelActual;
            maxNodo[0] = actual;
        }

        Nodo iter = actual.getLiga();
        while (iter != null) {
            if (iter.getSw() == 0) {
                // Es un hijo hoja (Persona), está un nivel más abajo que el padre
                if (nivelActual + 1 > maxNivel[0]) {
                    maxNivel[0] = nivelActual + 1;
                    maxNodo[0] = iter;
                }
            } else {
                // Es un puente a una sublista. El nodo apuntado es el hijo (cabeza de sublista)
                // Se llama recursivamente con el nivel del hijo (nivelActual + 1)
                buscarMayorNivel(iter.getLigaLista(), nivelActual + 1, maxNivel, maxNodo);
            }
            iter = iter.getLiga();
        }
    }

    public void mostrarDescendientes(int id) {

        //Si el buscado es la cabeza
        if (cabeza.getPersona().getId() == id) {
            Nodo hijo = cabeza.getLiga();
            if (hijo == null) {
                System.out.println("La persona no tiene descendientes");
                return;
            }
            buscarDescendientes(hijo);
            return;
        }

        Nodo nodo = buscar(id, cabeza);
        if (nodo == null) {
            System.out.println("No se encontró el nodo");
            return;
        }
        if (nodo.getSw() == 0) {
            System.out.println("La persona no tiene descendientes");
            return;
        }

        Nodo hijo = nodo.getLigaLista().getLiga();
        if (hijo == null) {
            System.out.println("La persona no tiene descendientes");
            return;
        }

        buscarDescendientes(hijo);
    }

    private void buscarDescendientes(Nodo actual) {

        while (actual != null) {

            if (actual.getSw() == 0) {
                System.out.println(actual.getPersona().toString());
            }
            else {
                Nodo sub = actual.getLigaLista();

                System.out.println(sub.getPersona().toString());

                buscarDescendientes(sub.getLiga());
            }
            actual = actual.getLiga();
        }
    }




}
