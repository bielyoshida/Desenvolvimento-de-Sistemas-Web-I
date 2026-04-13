package dao;

// Importações necessárias para trabalhar com banco de dados
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

// Importações para lista
import java.util.ArrayList;
import java.util.List;

// Classe modelo Estado
import model.Estado;

// Classe de conexão com banco
import util.Conexao;

public class EstadoDAO {

    /**
     * Insere um novo estado no banco de dados
     */
    public void inserir(Estado e) {

        // Comando SQL de inserção
        String sql = "INSERT INTO estado (nome, sigla) VALUES (?, ?)";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os parâmetros (?) pelos valores do objeto
            ps.setString(1, e.getNome());
            ps.setString(2, e.getSigla());

            // Executa o INSERT
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir estado.", ex);
        }
    }

    /**
     * Atualiza um estado existente
     */
    public void alterar(Estado e) {

        // Comando SQL de atualização
        String sql = "UPDATE estado SET nome = ?, sigla = ? WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNome());
            ps.setString(2, e.getSigla());
            ps.setInt(3, e.getCodigo());

            // Executa o UPDATE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar estado.", ex);
        }
    }

    /**
     * Exclui um estado pelo ID (codigo)
     */
    public void excluir(int id) {

        // Comando SQL de exclusão
        String sql = "DELETE FROM estado WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            // Executa o DELETE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir estado.", ex);
        }
    }

    /**
     * Busca um estado pelo ID
     */
    public Estado buscarPorId(int id) {

        String sql = "SELECT codigo, nome, sigla FROM estado WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                // Se encontrou um registro
                if (rs.next()) {

                    Estado e = new Estado();

                    // Preenche o objeto com os dados do banco
                    e.setCodigo(rs.getInt("codigo"));
                    e.setNome(rs.getString("nome"));
                    e.setSigla(rs.getString("sigla"));

                    return e;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar estado por ID.", ex);
        }

        // Retorna null se não encontrou
        return null;
    }

    /**
     * Lista todos os estados cadastrados
     */
    public List<Estado> listar() {

        String sql = "SELECT codigo, nome, sigla FROM estado ORDER BY codigo DESC";

        List<Estado> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            // Percorre os resultados
            while (rs.next()) {

                Estado e = new Estado();

                e.setCodigo(rs.getInt("codigo"));
                e.setNome(rs.getString("nome"));
                e.setSigla(rs.getString("sigla"));

                // Adiciona na lista
                lista.add(e);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar estados.", ex);
        }

        return lista;
    }
}
