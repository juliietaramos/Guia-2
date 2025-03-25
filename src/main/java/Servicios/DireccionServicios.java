package Servicios;

import Entidades.DireccionEntidad;
import Repositorios.DireccionRepositorio;

import java.sql.SQLException;
import java.util.Optional;

public class DireccionServicios {
    private static DireccionServicios instance;

    public static DireccionServicios getInstanceOf() {
        if (instance == null) {
            instance = new DireccionServicios();
        }
        return instance;
    }

    private DireccionEntidad crearDireccion(String calle , int altura , int alumnoId){
        DireccionEntidad direccionEntidad = new DireccionEntidad(calle,altura,alumnoId);
        Optional<DireccionEntidad> direccionExistente = DireccionRepositorio
                .getInstanceOf()
                .encontrarPorId(direccionEntidad.getAlumno_id());
        if (direccionExistente.isPresent()){
            return direccionEntidad;
        }
        else {
            return null;
        }
    }

    public void guardarDireccion(String calle , int altura , int alumnoId) throws SQLException {
        DireccionEntidad direccion = crearDireccion(calle,altura,alumnoId);
        if(direccion!=null){
            DireccionRepositorio
                    .getInstanceOf()
                    .guardar(direccion);
            System.out.println("Direccion guardada con exito.");
        }else {
            System.out.println("El id del alumno no fue encontrado.");
        }
    }
}
