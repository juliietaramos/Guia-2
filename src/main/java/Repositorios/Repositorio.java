package Repositorios;

import java.util.ArrayList;
import java.util.Optional;

public interface Repositorio <T>{
    public void guardar(T t);
    public Optional<T> encontrarPorId (int id);
    public void eliminarPorId (int id);
    public int contar();
    public ArrayList<T> encontrarTodos();
}
