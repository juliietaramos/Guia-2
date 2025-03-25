package Repositorios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Repositorio <T>{
    public void guardar(T t);
    public Optional<T>encontrarPorId(int id) throws SQLException;
    public List<T> listar();
}
