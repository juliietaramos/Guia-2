package Servicios;

import Entidades.DireccionEntidad;
import Repositorios.AlumnosRepositorio;
import Repositorios.DireccionRepositorio;

import java.sql.SQLException;
import java.util.Optional;

public class DireccionServicios {
    private static DireccionServicios instance;
    private AlumnosRepositorio alumnosRepositorio;
    private DireccionRepositorio direccionRepositorio;

    private DireccionServicios() {
        this.alumnosRepositorio = AlumnosRepositorio.getInstanceOf();
        this.direccionRepositorio = DireccionRepositorio.getInstanceOf();
    }

    public static DireccionServicios getInstanceOf() {
        if (instance == null) {
            instance = new DireccionServicios();
        }
        return instance;
    }

    public DireccionRepositorio getDireccionRepositorio() {
        return direccionRepositorio;
    }

    private DireccionEntidad crearDireccion(String calle, int altura, int alumnoId) {
        DireccionEntidad direccionEntidad = new DireccionEntidad(calle, altura, alumnoId);
        Optional<DireccionEntidad> direccionExistente = direccionRepositorio
                .encontrarPorId(direccionEntidad.getAlumno_id());
        if (direccionExistente.isPresent()) {
            return direccionEntidad;
        } else {
            return null;
        }
    }

    public void guardarDireccion(String calle, int altura, int alumnoId) throws SQLException {
        DireccionEntidad direccion = crearDireccion(calle, altura, alumnoId);
        if (direccion != null) {
            direccionRepositorio
                    .guardar(direccion);
            System.out.println("Direccion guardada con exito.");
        } else {
            System.out.println("El id del alumno no fue encontrado.");
        }
    }


    public void eliminarDireccion(int id_alumno) {
        Optional<DireccionEntidad> direcciones = direccionRepositorio.encontrarPorId(id_alumno);
        if (direcciones.isPresent()){
            direccionRepositorio.eliminar(id_alumno);
        }
    }
}
