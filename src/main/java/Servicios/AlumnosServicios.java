package Servicios;

import Entidades.AlumnosEntidad;
import Repositorios.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class AlumnosServicios {
    private static AlumnosServicios instance;
    private AlumnosRepositorio alumnosRepositorio;
    private DireccionRepositorio direccionRepositorio;


    private AlumnosServicios() {
        this.alumnosRepositorio = AlumnosRepositorio.getInstanceOf();
        this.direccionRepositorio = DireccionRepositorio.getInstanceOf();
    }

    public static AlumnosServicios getInstanceOf() {
        if (instance == null) {
            instance = new AlumnosServicios();
        }
        return instance;
    }

    public AlumnosRepositorio getAlumnosRepositorio() {
        return alumnosRepositorio;
    }

    private AlumnosEntidad crearAlumno(String nombre, String apellido, int edad, String email) throws SQLException {
        AlumnosEntidad alumno = new AlumnosEntidad(apellido, nombre, edad, email);
//        Optional<AlumnosEntidad> usuarioExistente = AlumnosRepositorio
//                .getInstanceOf()
//                .encontrarPorId(alumno.getId());
        Optional<AlumnosEntidad> usuarioExistente = alumnosRepositorio
                .encontrarPorId(alumno.getId());
        if (usuarioExistente.isPresent()) {
            return null;
        }
        return alumno;
    }

    public void guardarAlumno(String nombre, String apellido, int edad, String email) throws SQLException {
        AlumnosEntidad alumno = crearAlumno(nombre, apellido, edad, email);
        if (alumno != null) {
            alumnosRepositorio
                    .guardar(alumno);
            System.out.println("Alumno agregado con exito.");
        } else {
            System.out.println("El ID ya se encuentra registrado.");
        }
    }

    public void modificarEdad(int id, int edad) {
        try {
            Optional<AlumnosEntidad> alumno = alumnosRepositorio.encontrarPorId(id);
            if (alumno.isPresent()) {
                alumnosRepositorio.modificarEdad(id, edad);
                System.out.println("Edad modificada con exito.");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al intentar modificar la edad.");
            System.out.println(e.getMessage());
        }
    }

    public void eliminarAlumno (int id){
        try{
            Optional<AlumnosEntidad> alumno = alumnosRepositorio.encontrarPorId(id);
            if(alumno.isPresent()){
                alumnosRepositorio.eliminar(id);
                direccionRepositorio.eliminar(id);
            }
        }catch (SQLException e){
            System.out.println("Ocurrio un error al intentar eliminar el alumno");
            System.out.println(e.getMessage());
        }
    }

}
