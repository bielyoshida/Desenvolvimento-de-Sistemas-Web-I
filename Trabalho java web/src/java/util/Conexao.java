package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/lojabd";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "5432";

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado.", e);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco.", e);
        }
    }

    public static void fechar(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão.");
        }
    }
}