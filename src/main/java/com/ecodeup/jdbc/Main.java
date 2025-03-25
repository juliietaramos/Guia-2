package com.ecodeup.jdbc;

import Entidades.AlumnosEntidad;
import Repositorios.AlumnosRepositorio;
import Repositorios.DireccionRepositorio;
import Servicios.AlumnosServicios;
import Servicios.DireccionServicios;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //LINK DE LA GUIA: file:///C:/Users/julie/Downloads/Guia%20%232%20-%20JDBC%20(3).pdf
        //LINK DEL MATERIAL: file:///C:/Users/julie/Downloads/API%20JDBC%20(1).pdf
        //LINK RESUMEN: file:///C:/Users/julie/OneDrive%20-%20UTN%20FRMDP/Escritorio/UTN/Programaci%C3%B3n%20III/JDBC.pdf

        //AlumnosServicios.getInstanceOf().guardarAlumno();

        Scanner scanner = new Scanner(System.in);
        //AlumnosServicios alumnosServicios = AlumnosServicios.getInstanceOf();
        //DireccionServicios direccionServicios = DireccionServicios.getInstanceOf();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Insertar un Nuevo Alumno");
            System.out.println("2. Insertar una nueva dirección");
            System.out.println("3. Listar Todos los Alumnos");
            System.out.println("4. Listar todas las direcciones por alumno");
            System.out.println("5. Actualizar la Edad de un Alumno");
            System.out.println("6. Eliminar un Alumno");
            System.out.println("7. Eliminar una dirección");
            System.out.println("8. Buscar alumno por id");
            System.out.println("9. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
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
                        AlumnosServicios.getInstanceOf().guardarAlumno(nombre, apellido, edad, email);
                    } catch (SQLException e) {
                        System.out.println("Error al registrar el alumno: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el id del alumno que desee ingresar una direccion: ");
                    int id_alumno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la calle: ");
                    String calle = scanner.nextLine();
                    System.out.println("Ingrese la altura: ");
                    int altura = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        DireccionServicios.getInstanceOf().guardarDireccion(calle, altura, id_alumno);
                    } catch (SQLException e) {
                        System.out.println("Error al registrar la direccion: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Listado de alumnos: ");
                    System.out.println(AlumnosRepositorio.getInstanceOf().listar());
//                    alumnosServicios.listarTodosLosAlumnos();
                    break;
                case 4:
                    System.out.println("Ingrese el ID del alumno:");
                    int idAlumno = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println(DireccionRepositorio.getInstanceOf().listarPorId(idAlumno));
                    break;
                case 5:
                    System.out.println("Ingrese el ID del alumno:");
                    int idActualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese la nueva edad del alumno:");
                    int nuevaEdad = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                        AlumnosServicios.getInstanceOf().modificarEdad(idActualizar,nuevaEdad);
                    break;
                case 6:
//                    System.out.println("Ingrese el ID del alumno:");
//                    int idEliminar = scanner.nextInt();
//                    scanner.nextLine(); // Consumir el salto de línea
//                    try {
//                        alumnosServicios.eliminarAlumno(idEliminar);
//                    } catch (SQLException e) {
//                        System.out.println("Error al eliminar el alumno: " + e.getMessage());
//                    }
                    break;
                case 7:
//                    System.out.println("Ingrese el ID de la dirección:");
//                    int idDireccionEliminar = scanner.nextInt();
//                    scanner.nextLine(); // Consumir el salto de línea
//                    try {
//                        direccionServicios.eliminarDireccion(idDireccionEliminar);
//                    } catch (SQLException e) {
//                        System.out.println("Error al eliminar la dirección: " + e.getMessage());
//                    }
                    break;
                case 8:
                    System.out.println("Ingrese el ID del alumno:");
                    int alumnoId = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    try {
                        Optional<AlumnosEntidad> alumno = AlumnosRepositorio.getInstanceOf().encontrarPorId(alumnoId);
                        if (alumno.isPresent()) {
                            System.out.println("Alumno: " + alumno.get());
                        } else {
                            System.out.println("No se encontró ningún alumno con el ID proporcionado.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al buscar el alumno: " + e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }


}
