package Conecciones;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLiteConeccion {
    private static  final DataSource dataSource;
    private static final String url = "jdbc:sqlite:alumnos.db";

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
