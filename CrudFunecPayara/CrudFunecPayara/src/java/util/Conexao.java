package util; // Pacote utilitário, geralmente usado para classes de apoio (como conexão com banco)

import java.sql.Connection;      // Representa a conexão com o banco de dados
import java.sql.DriverManager;   // Classe responsável por gerenciar drivers JDBC
import java.sql.SQLException;    // Exceção para erros de banco de dados

public class Conexao {

    // URL de conexão com o banco PostgreSQL
    // Formato: jdbc:postgresql://host:porta/nome_do_banco
    private static final String URL = "jdbc:postgresql://localhost:5432/loja123";

    // Usuário do banco de dados
    private static final String USUARIO = "postgres";

    // Senha do banco de dados
    private static final String SENHA = "123";

    /**
     * Método responsável por criar e retornar uma conexão com o banco de dados.
     * 
     * @return Connection (objeto de conexão com o banco)
     */
    public static Connection conectar() {
        try {
            // Carrega o driver do PostgreSQL na memória
            // Isso permite que o Java saiba como se comunicar com o banco
            Class.forName("org.postgresql.Driver");

            // Cria e retorna a conexão usando a URL, usuário e senha
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException e) {
            // Erro caso o driver não seja encontrado no projeto
            throw new RuntimeException("Driver PostgreSQL não encontrado.", e);

        } catch (SQLException e) {
            // Erro caso não consiga conectar ao banco (usuário, senha, URL, banco offline, etc.)
            throw new RuntimeException("Erro ao conectar com o banco.", e);
        }
    }

    /**
     * Método responsável por fechar a conexão com o banco.
     * 
     * @param con conexão que será fechada
     */
    public static void fechar(Connection con) {
        try {
            // Verifica se a conexão não é nula antes de fechar
            if (con != null) {
                con.close(); // Fecha a conexão liberando recursos
            }
        } catch (SQLException e) {
            // Caso ocorra erro ao fechar, apenas exibe mensagem no console
            System.out.println("Erro ao fechar conexão.");
        }
    }
}