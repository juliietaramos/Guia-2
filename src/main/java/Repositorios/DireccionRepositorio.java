package Repositorios;

import Conecciones.SQLiteConeccion;
import Entidades.AlumnosEntidad;
import Entidades.DireccionEntidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DireccionRepositorio implements Repositorio<DireccionEntidad> {
    private static DireccionRepositorio instance;

    private DireccionRepositorio() {
    }


    public static DireccionRepositorio getInstanceOf() {
        if (instance == null) {
            instance = new DireccionRepositorio();
        }
        return instance;
    }

    @Override
    public void guardar(DireccionEntidad direccionEntidad) {
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO direcciones (calle , altura , alumno_id) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, direccionEntidad.getCalle());
            preparedStatement.setInt(2, direccionEntidad.getAltura());
            preparedStatement.setInt(3, direccionEntidad.getAlumno_id());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error inesperado en guardar" + exception.getMessage());
        }
    }

    @Override
    public Optional<DireccionEntidad> encontrarPorId(int id) {
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * from direcciones WHERE alumno_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new DireccionEntidad(
                            resultSet.getInt("id"),
                            resultSet.getString("calle"),
                            resultSet.getInt("altura"),
                            resultSet.getInt("alumno_id")));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error inesperado en encontrarPorId: " + e.getMessage());
        }
        return Optional.empty();
    }


    public List<DireccionEntidad> listarPorId(int id_alumno) {
        String sql = "SELECT * from direcciones where alumno_id = ?;";
        List <DireccionEntidad> listaDirecciones = new ArrayList<>();
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id_alumno);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    DireccionEntidad nueva = new DireccionEntidad(
                            resultSet.getInt("id"),
                            resultSet.getString("calle"),
                            resultSet.getInt("altura"),
                            resultSet.getInt("alumno_id"));
                    listaDirecciones.add(nueva);
                }
            }
        }catch(SQLException e){
            System.out.println("No se pudo listar las direcciones del alumno.");
            System.out.println(e.getMessage());
        }
        return listaDirecciones;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE from direcciones WHERE alumno_id = ?;";
        try(Connection connection = SQLiteConeccion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            int filasBoradas = preparedStatement.executeUpdate();
            System.out.println("Se han eliminado " + filasBoradas + " direcciones correctamente.");
        }catch (Exception e){
            System.out.println("Ocurrio un error al intentar eliminar la/s direccines");
            System.out.println(e.getMessage());
        }
    }
}
