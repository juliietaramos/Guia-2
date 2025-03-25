package com.ecodeup.jdbc;

import Entidades.AlumnosEntidad;
import Repositorios.AlumnosRepositorio;
import Repositorios.DireccionRepositorio;
import Servicios.AlumnosServicios;
import Servicios.DireccionServicios;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    static AlumnosServicios alumnosServicios = AlumnosServicios.getInstanceOf();
    static DireccionServicios direccionServicios = DireccionServicios.getInstanceOf();

    public static void menu() {
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar alumno por id");
            System.out.println("2. Insertar un Nuevo Alumno");
            System.out.println("3. Insertar una nueva dirección");
            System.out.println("4. Listar Todos los Alumnos");
            System.out.println("5. Listar todas las direcciones por alumno");
            System.out.println("6. Actualizar la Edad de un Alumno");
            System.out.println("7. Eliminar un Alumno");
            System.out.println("8. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarAlumnoPorId();

                    break;
                case 2:
                    insertarNuevoAlumno();
                    break;
                case 3:
                    insertarNuevaDireccion();
                    break;
                case 4:
                    listarTodosLosAlumnos();
                    break;
                case 5:
                    listarDireccionesPorAlumno();
                    break;
                case 6:
                    actualizarEdadAlumno();
                    break;
                case 7:
                    eliminarUnAlumno();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void insertarNuevoAlumno() {
        System.out.println("Ingrese el nombre del alumno:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del alumno:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la edad del alumno:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese el email del alumno:");
        String email = scanner.nextLine();
        try {
            alumnosServicios.guardarAlumno(nombre, apellido, edad, email);
        } catch (SQLException e) {
            System.out.println("Error al registrar el alumno: " + e.getMessage());
        }
    }

    private static void insertarNuevaDireccion() {
        System.out.println("Ingrese el id del alumno que desee ingresar una direccion: ");
        int id_alumno = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la calle: ");
        String calle = scanner.nextLine();
        System.out.println("Ingrese la altura: ");
        int altura = scanner.nextInt();
        scanner.nextLine();
        try {
            direccionServicios.guardarDireccion(calle, altura, id_alumno);
        } catch (SQLException e) {
            System.out.println("Error al registrar la direccion: " + e.getMessage());
        }
    }

    private static void listarTodosLosAlumnos() {
        System.out.println("Listado de alumnos: ");
        System.out.println(alumnosServicios
                .getAlumnosRepositorio()
                .listar());
    }

    private static void listarDireccionesPorAlumno() {
        System.out.println("Ingrese el ID del alumno:");
        int idAlumno = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println(direccionServicios
                .getDireccionRepositorio()
                .listarPorId(idAlumno));
    }

    private static void actualizarEdadAlumno() {
        System.out.println("Ingrese el ID del alumno:");
        int idActualizar = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la nueva edad del alumno:");
        int nuevaEdad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        alumnosServicios.modificarEdad(idActualizar, nuevaEdad);
    }

    private static void eliminarUnAlumno() {
        System.out.println("Ingrese el ID del alumno:");
        int idEliminar = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        alumnosServicios.eliminarAlumno(idEliminar);
    }

    private static void buscarAlumnoPorId() {
        System.out.println("Ingrese el ID del alumno:");
        int alumnoId = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        try {
            Optional<AlumnosEntidad> alumno = alumnosServicios.getAlumnosRepositorio().encontrarPorId(alumnoId);
            if (alumno.isPresent()) {
                System.out.println("Alumno: " + alumno.get());
            } else {
                System.out.println("No se encontró ningún alumno con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }
    }
}

