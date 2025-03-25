package Repositorios;

import Entidades.AlumnosEntidad;

import java.util.ArrayList;
import java.util.Optional;

public class AlumnosRepositorio implements Repositorio<AlumnosEntidad> {
    private static AlumnosRepositorio instance;
    private AlumnosRepositorio(){}
    public static AlumnosRepositorio getInstanceOf(){
        if (instance == null){
            instance = new AlumnosRepositorio();
        }
        return instance;
    }

    @Override
    public void guardar(AlumnosEntidad alumnosEntidad) {

    }

    @Override
    public Optional<AlumnosEntidad> encontrarPorId(int id) {
        return Optional.empty();
    }

    @Override
    public void eliminarPorId(int id) {

    }

    @Override
    public int contar() {
        return 0;
    }

    @Override
    public ArrayList<AlumnosEntidad> encontrarTodos() {
        return null;
    }
}
