package dao;

// Importações JDBC
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importações de lista
import java.util.ArrayList;
import java.util.List;

// Modelos
import model.Cidade;
import model.Estado;

// Classe utilitária de conexão
import util.Conexao;

public class CidadeDAO {

    /**
     * Insere uma nova cidade no banco de dados.
     */
    public void inserir(Cidade c) {

        String sql = "INSERT INTO cidade (nome, codigo_estado) VALUES (?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setInt(2, c.getEstado().getCodigo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir cidade.", ex);
        }
    }

    /**
     * Atualiza uma cidade existente.
     */
    public void alterar(Cidade c) {

        String sql = "UPDATE cidade SET nome = ?, codigo_estado = ? WHERE codigo = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setInt(2, c.getEstado().getCodigo());
            ps.setInt(3, c.getCodigo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar cidade.", ex);
        }
    }

    /**
     * Exclui uma cidade pelo código.
     */
    public void excluir(int id) {

        String sql = "DELETE FROM cidade WHERE codigo = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir cidade.", ex);
        }
    }

    /**
     * Busca uma cidade pelo seu ID.
     * Traz também os dados do estado relacionado usando JOIN.
     */
    public Cidade buscarPorId(int id) {

        String sql = "SELECT "
                + "c.codigo AS codigo_cidade, "
                + "c.nome AS nome_cidade, "
                + "e.codigo AS codigo_estado, "
                + "e.nome AS nome_estado, "
                + "e.sigla AS sigla_estado "
                + "FROM cidade c "
                + "JOIN estado e ON c.codigo_estado = e.codigo "
                + "WHERE c.codigo = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Estado e = new Estado();
                    e.setCodigo(rs.getInt("codigo_estado"));
                    e.setNome(rs.getString("nome_estado"));
                    e.setSigla(rs.getString("sigla_estado"));

                    Cidade c = new Cidade();
                    c.setCodigo(rs.getInt("codigo_cidade"));
                    c.setNome(rs.getString("nome_cidade"));
                    c.setEstado(e);

                    return c;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar cidade por ID.", ex);
        }

        return null;
    }

    /**
     * Lista todas as cidades com o respectivo estado.
     */
    public List<Cidade> listar() {

        String sql = "SELECT "
                + "c.codigo AS codigo_cidade, "
                + "c.nome AS nome_cidade, "
                + "e.codigo AS codigo_estado, "
                + "e.nome AS nome_estado, "
                + "e.sigla AS sigla_estado "
                + "FROM cidade c "
                + "JOIN estado e ON c.codigo_estado = e.codigo "
                + "ORDER BY c.codigo DESC";

        List<Cidade> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estado e = new Estado();
                e.setCodigo(rs.getInt("codigo_estado"));
                e.setNome(rs.getString("nome_estado"));
                e.setSigla(rs.getString("sigla_estado"));

                Cidade c = new Cidade();
                c.setCodigo(rs.getInt("codigo_cidade"));
                c.setNome(rs.getString("nome_cidade"));
                c.setEstado(e);

                lista.add(c);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar cidades.", ex);
        }

        return lista;
    }
}
