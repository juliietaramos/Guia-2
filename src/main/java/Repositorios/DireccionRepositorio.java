package Repositorios;

import Entidades.DireccionEntidad;

import java.util.ArrayList;
import java.util.Optional;

public class DireccionRepositorio implements Repositorio<DireccionEntidad> {
    private static DireccionRepositorio instance;
    private DireccionRepositorio(){};
    public static DireccionRepositorio getInstanceOf(){
        if (instance == null){
            instance = new DireccionRepositorio();
        }
        return instance;
    }
    @Override
    public void guardar(DireccionEntidad direccionEntidad) {

    }

    @Override
    public Optional<DireccionEntidad> encontrarPorId(int id) {
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
    public ArrayList<DireccionEntidad> encontrarTodos() {
        return null;
    }
}
