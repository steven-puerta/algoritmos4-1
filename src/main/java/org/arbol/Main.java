package org.arbol;

import org.arbol.classes.lista.Lista;
import org.arbol.classes.nodo.Nodo;
import org.arbol.classes.persona.Persona;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // ========================== CONSTRUCCIÓN DEL ÁRBOL INICIAL ==========================
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

        Lista lista = new Lista(nodo1);
        Scanner sc = new Scanner(System.in);
        int opcionPrincipal;

        do {
            // ==================== MENÚ PRINCIPAL ====================
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║         SISTEMA DE ÁRBOL FAMILIAR        ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Gestión de Personas                  ║");
            System.out.println("║  2. Consultas de Relaciones Familiares   ║");
            System.out.println("║  3. Consultas sobre la Estructura        ║");
            System.out.println("║  4. Eliminación de Niveles               ║");
            System.out.println("║  5. Mostrar Árbol                        ║");
            System.out.println("║  0. Salir                                ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Seleccione una categoría: ");
            opcionPrincipal = sc.nextInt();
            sc.nextLine();

            switch (opcionPrincipal) {

                // ══════════════════════════════════════════════════════
                // 1. GESTIÓN DE PERSONAS
                // ══════════════════════════════════════════════════════
                case 1:
                    int opcionGestion;
                    System.out.println("\n┌──────────────────────────────────┐");
                    System.out.println("│       1. GESTIÓN DE PERSONAS     │");
                    System.out.println("├──────────────────────────────────┤");
                    System.out.println("│  1. Registrar persona            │");
                    System.out.println("│  2. Eliminar persona             │");
                    System.out.println("│  3. Actualizar persona           │");
                    System.out.println("│  0. Volver                       │");
                    System.out.println("└──────────────────────────────────┘");
                    System.out.print("Seleccione una opción: ");
                    opcionGestion = sc.nextInt();
                    sc.nextLine();

                    switch (opcionGestion) {

                        // ── Registrar ────────────────────────────────
                        case 1:
                            System.out.print("Cédula del padre: ");
                            int idPadre = sc.nextInt(); sc.nextLine();
                            System.out.print("Cédula de la nueva persona: ");
                            int idNuevo = sc.nextInt(); sc.nextLine();
                            System.out.print("Nombre: ");
                            String nombreNuevo = sc.nextLine();
                            System.out.print("Edad: ");
                            int edadNueva = sc.nextInt(); sc.nextLine();

                            boolean registrado = lista.Registrar(idPadre, new Persona(idNuevo, nombreNuevo, edadNueva));
                            System.out.println(registrado
                                    ? "✔ Persona registrada exitosamente."
                                    : "✘ No se encontró el padre con esa cédula.");
                            break;

                        // ── Eliminar (método faltante) ────────────────
                        case 2:
                            System.out.print("Cédula de la persona a eliminar: ");
                            int idEliminar = sc.nextInt(); sc.nextLine();
                            System.out.println("⚠ Método 'eliminar(" + idEliminar + ")' aún no implementado.");
                            // lista.eliminar(idEliminar);
                            break;

                        // ── Actualizar ───────────────────────────────
                        case 3:
                            System.out.print("Cédula de la persona a actualizar: ");
                            int idActualizar = sc.nextInt(); sc.nextLine();

                            System.out.println("¿Qué campo desea modificar?");
                            System.out.println("  1. Nombre");
                            System.out.println("  2. Edad");
                            System.out.print("Opción: ");
                            int campoOpcion = sc.nextInt(); sc.nextLine();

                            String nuevoNombre = "";
                            int nuevaEdad = 0;
                            String campo = "";

                            if (campoOpcion == 1) {
                                System.out.print("Nuevo nombre: ");
                                nuevoNombre = sc.nextLine();
                                campo = "nombre";
                            } else if (campoOpcion == 2) {
                                System.out.print("Nueva edad: ");
                                nuevaEdad = sc.nextInt(); sc.nextLine();
                                campo = "edad";
                            } else {
                                System.out.println("✘ Campo no válido.");
                                break;
                            }

                            boolean actualizado = lista.Actualizar(idActualizar, nuevoNombre, nuevaEdad, campo);
                            System.out.println(actualizado
                                    ? "✔ Persona actualizada exitosamente."
                                    : "✘ No se encontró la persona con esa cédula.");
                            break;

                        case 0:
                            break;
                        default:
                            System.out.println("✘ Opción no válida.");
                    }
                    break;

                // ══════════════════════════════════════════════════════
                // 2. CONSULTAS DE RELACIONES FAMILIARES
                // ══════════════════════════════════════════════════════
                case 2:
                    int opcionRelaciones;
                    System.out.println("\n┌────────────────────────────────────────────┐");
                    System.out.println("│   2. CONSULTAS DE RELACIONES FAMILIARES    │");
                    System.out.println("├────────────────────────────────────────────┤");
                    System.out.println("│  1. Padre                                  │");
                    System.out.println("│  2. Hijos                                  │");
                    System.out.println("│  3. Hermanos                               │");
                    System.out.println("│  4. Ancestros                              │");
                    System.out.println("│  5. Descendientes                          │");
                    System.out.println("│  0. Volver                                 │");
                    System.out.println("└────────────────────────────────────────────┘");
                    System.out.print("Seleccione una opción: ");
                    opcionRelaciones = sc.nextInt(); sc.nextLine();

                    switch (opcionRelaciones) {

                        // ── Padre ────────────────────────────────────
                        case 1:
                            System.out.print("Cédula de la persona: ");
                            int idPadreConsulta = sc.nextInt(); sc.nextLine();
                            System.out.println();
                            lista.mostrarPadre(idPadreConsulta);
                            break;

                        // ── Hijos ────────────────────────────────────
                        case 2:
                            System.out.print("Cédula de la persona: ");
                            int idHijos = sc.nextInt(); sc.nextLine();
                            System.out.println();
                            lista.mostrarHijos(idHijos);
                            break;

                        // ── Hermanos ─────────────────────────────────
                        case 3:
                            System.out.print("Cédula de la persona: ");
                            int idHermanos = sc.nextInt(); sc.nextLine();
                            System.out.println();
                            lista.mostrarHermanos(idHermanos);
                            break;

                        // ── Ancestros ────────────────────────────────
                        case 4:
                            System.out.print("Cédula de la persona: ");
                            int idAncestros = sc.nextInt(); sc.nextLine();
                            System.out.println();
                            lista.mostrarAncestros(idAncestros);
                            break;

                        // ── Descendientes (método faltante) ──────────
                        case 5:
                            System.out.print("Cédula de la persona: ");
                            int idDescendientes = sc.nextInt(); sc.nextLine();
                            System.out.println("⚠ Método 'mostrarDescendientes(" + idDescendientes + ")' aún no implementado.");
                            // lista.mostrarDescendientes(idDescendientes);
                            break;

                        case 0:
                            break;
                        default:
                            System.out.println("✘ Opción no válida.");
                    }
                    break;

                // ══════════════════════════════════════════════════════
                // 3. CONSULTAS SOBRE LA ESTRUCTURA
                // ══════════════════════════════════════════════════════
                case 3:
                    int opcionEstructura;
                    System.out.println("\n┌──────────────────────────────────────────────┐");
                    System.out.println("│   3. CONSULTAS SOBRE LA ESTRUCTURA DEL ÁRBOL │");
                    System.out.println("├──────────────────────────────────────────────┤");
                    System.out.println("│  1. Nodo con Mayor Grado                     │");
                    System.out.println("│  2. Nodo con Mayor Nivel                     │");
                    System.out.println("│  3. Altura del Árbol                         │");
                    System.out.println("│  4. Nivel de un Registro                     │");
                    System.out.println("│  5. Registros por Nivel                      │");
                    System.out.println("│  0. Volver                                   │");
                    System.out.println("└──────────────────────────────────────────────┘");
                    System.out.print("Seleccione una opción: ");
                    opcionEstructura = sc.nextInt(); sc.nextLine();

                    switch (opcionEstructura) {

                        // ── Mayor Grado ──────────────────────────────
                        case 1:
                            System.out.println();
                            lista.mostrarMayorGrado();
                            break;

                        // ── Mayor Nivel ──────────────────────────────
                        case 2:
                            System.out.println();
                            lista.mostrarMayorNivel();
                            break;

                        // ── Altura ───────────────────────────────────
                        case 3:
                            System.out.println("\nAltura del árbol: " + lista.alturaArbol());
                            break;

                        // ── Nivel de un Registro ─────────────────────
                        case 4:
                            System.out.print("Cédula de la persona: ");
                            int idNivel = sc.nextInt(); sc.nextLine();
                            int nivel = lista.nivelRegistro(idNivel);
                            System.out.println(nivel != 0
                                    ? "La persona con cédula " + idNivel + " está en el nivel: " + nivel
                                    : "✘ No se encontró la persona con cédula " + idNivel);
                            break;

                        // ── Registros por Nivel ──────────────────────
                        case 5:
                            System.out.print("Nivel a consultar: ");
                            int nivelConsulta = sc.nextInt(); sc.nextLine();
                            System.out.println("\nRegistros en el nivel " + nivelConsulta + ":");
                            lista.registrosNivel(nivelConsulta);
                            break;

                        case 0:
                            break;
                        default:
                            System.out.println("✘ Opción no válida.");
                    }
                    break;

                // ══════════════════════════════════════════════════════
                // 4. ELIMINACIÓN DE NIVELES
                // ══════════════════════════════════════════════════════
                case 4:
                    System.out.println("\n┌──────────────────────────────┐");
                    System.out.println("│   4. ELIMINACIÓN DE NIVELES  │");
                    System.out.println("└──────────────────────────────┘");
                    System.out.print("Nivel a eliminar: ");
                    int nivelEliminar = sc.nextInt(); sc.nextLine();

                    boolean eliminado = lista.eliminarNivel(nivelEliminar);
                    System.out.println(eliminado
                            ? "✔ Nivel " + nivelEliminar + " eliminado exitosamente."
                            : "✘ No se pudo eliminar el nivel " + nivelEliminar + ".");
                    break;

                // ══════════════════════════════════════════════════════
                // 5. MOSTRAR ÁRBOL
                // ══════════════════════════════════════════════════════
                case 5:
                    System.out.println("\n=== Árbol Familiar ===");
                    lista.mostrarArbol();
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("✘ Opción no válida. Intente de nuevo.");
            }

        } while (opcionPrincipal != 0);

        sc.close();
    }
}