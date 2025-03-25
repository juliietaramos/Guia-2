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

    private AlumnosEntidad crearAlumno(String nombre , String apellido , int edad , String email) throws SQLException {
        AlumnosEntidad alumno = new AlumnosEntidad(apellido,nombre,edad,email);
        Optional<AlumnosEntidad> usuarioExistente = AlumnosRepositorio
                .getInstanceOf()
                .encontrarPorId(alumno.getId());
        if (usuarioExistente.isPresent()) {
            return null;
        }
        return alumno;
    }

    public void guardarAlumno(String nombre , String apellido , int edad , String email) throws SQLException {
        AlumnosEntidad alumno = crearAlumno(nombre,apellido,edad,email);
        if (alumno != null) {
            AlumnosRepositorio
                    .getInstanceOf()
                    .guardar(alumno);
            System.out.println("Alumno agregado con exito.");
        } else {
            System.out.println("El ID ya se encuentra registrado.");
        }
    }
}
