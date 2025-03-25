package Repositorios;

import Conecciones.SQLiteConeccion;
import Entidades.AlumnosEntidad;
import Entidades.DireccionEntidad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO direcciones (calle , altura , alumno_id) VALUES(?, ?, ?)")){
            preparedStatement.setString(1, direccionEntidad.getCalle());
            preparedStatement.setInt(2,direccionEntidad.getAltura());
            preparedStatement.setInt(3,direccionEntidad.getAlumno_id());
            preparedStatement.executeUpdate();
        }catch (SQLException exception){
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
}
