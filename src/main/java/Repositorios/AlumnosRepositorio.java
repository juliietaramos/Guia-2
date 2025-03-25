package Repositorios;

import Conecciones.SQLiteConeccion;
import Entidades.AlumnosEntidad;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AlumnosRepositorio implements Repositorio<AlumnosEntidad> {
    private static AlumnosRepositorio instance;

    private AlumnosRepositorio() {
    }

    public static AlumnosRepositorio getInstanceOf() {
        if (instance == null) {
            instance = new AlumnosRepositorio();
        }
        return instance;
    }

    @Override
    public void guardar(AlumnosEntidad alumnosEntidad) {
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO alumnos (nombre , apellido , edad , email) VALUES(?, ?, ?, ?)")) {
            preparedStatement.setString(1, alumnosEntidad.getNombre());
            preparedStatement.setString(2, alumnosEntidad.getApellido());
            preparedStatement.setInt(3, alumnosEntidad.getEdad());
            preparedStatement.setString(4, alumnosEntidad.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("Error inesperado en guardar" + exception.getMessage());
        }
    }

    @Override
    public Optional<AlumnosEntidad> encontrarPorId(int id) throws SQLException {
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * from alumnos WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new AlumnosEntidad(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getInt("edad"),
                            resultSet.getString("email")));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error inesperado en encontrarPorId: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<AlumnosEntidad> listar() {
        List<AlumnosEntidad> listaDeAlumnos = new ArrayList<>();
        String sql = "SELECT * from alumnos;";
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    AlumnosEntidad nuevo = new AlumnosEntidad(
                            resultSet.getInt("id"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getInt("edad"),
                            resultSet.getString("email"));
                    listaDeAlumnos.add(nuevo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inesperado al momento de listar.");
            System.out.println(e.getMessage());
        }
        return listaDeAlumnos;
    }

    public void modificarEdad(int id, int edad) {
        String sql = "UPDATE alumnos SET edad = ? WHERE id = ?;";
        try (Connection connection = SQLiteConeccion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, edad);
            preparedStatement.setInt(2, id);
            int filasModificadas = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println("No se pudo modificar la edad del alumno indicado.");
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE from alumnos WHERE id = ?;";
        try(Connection connection = SQLiteConeccion.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,id);
            int filasBorradas = preparedStatement.executeUpdate();
            System.out.println("Se ha borrdado el alumno correctamente.");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al eliminar el alumno");
            System.out.println(e.getMessage());
        }

    }
}
