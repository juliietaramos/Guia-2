package Servicios;

import Repositorios.*;

public class AlumnosServicios   {
    private static AlumnosServicios instance;
    private AlumnosRepositorio alumnosRepositorio;
    private DireccionRepositorio direccionRepositorio;

    private AlumnosServicios(){
        this.alumnosRepositorio = AlumnosRepositorio.getInstanceOf();
        this.direccionRepositorio = DireccionRepositorio.getInstanceOf();
    }

    public static AlumnosServicios getInstanceOf(){
        if (instance == null){
            instance = new AlumnosServicios();
        }
        return instance;
    }
}
