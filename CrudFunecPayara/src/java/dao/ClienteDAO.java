package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.Conexao;

public class ClienteDAO {

    public void inserir(Cliente c) {
        String sql = "INSERT INTO cliente (nome,sexo,telefone,cpf,codigo_cidade ) VALUES (?,?,?,?,?)";
        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os parâmetros (?) pelos valores do objeto
            ps.setString(1, c.getNome());
            ps.setString(2, c.getSexo());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getCpf());
            ps.setInt(5, c.getCodigo_cidade());

            // Executa o INSERT
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir Cliente.", ex);
        }
    }

    public void alterar(Cliente c) {

        // Comando SQL de atualização
        String sql = "UPDATE cliente SET nome = ?, sexo = ?, telefone = ?, cpf = ?, codigo_cidade = ? WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNome());
            ps.setString(2, c.getSexo());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getCpf());
            ps.setInt(5, c.getCodigo_cidade());
            ps.setInt(6, c.getCodigo());

            // Executa o UPDATE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar cliente.", ex);
        }
    }

    public void excluir(int id) {

        // Comando SQL de exclusão
        String sql = "DELETE FROM cliente WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            // Executa o DELETE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir cliente.", ex);
        }
    }

    public Cliente buscarPorId(int id) {

        String sql = "SELECT cliente.codigo,cliente.nome,cliente.sexo,cliente.telefone," +
        "cliente.cpf,cliente.codigo_cidade, cidade.codigo," +
        "cidade.nome as nome_cidade,cidade.codigo_estado " +
        "FROM cliente " +
        "inner join cidade ON cidade.codigo = cliente.codigo_cidade WHERE cliente.codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                // Se encontrou um registro
                if (rs.next()) {

                    Cliente c = new Cliente();

                    // Preenche o objeto com os dados do banco
                    c.setCodigo(rs.getInt("codigo"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setSexo(rs.getString("sexo"));
                    c.setTelefone(rs.getString("telefone"));
                    c.setCpf(rs.getString("cpf"));
                    c.setCodigo_cidade(rs.getInt("codigo_cidade"));
                    c.setNomeCidade(rs.getString("nome_cidade"));
                    return c;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar cliente por ID.", ex);
        }

        // Retorna null se não encontrou
        return null;
    }

    public List<Cliente> listar() {

        String sql = "SELECT cliente.codigo,cliente.nome,cliente.sexo,cliente.telefone," +
        "cliente.cpf,cliente.codigo_cidade, cidade.codigo," +
        "cidade.nome as nome_cidade,cidade.codigo_estado " +
        "FROM cliente " +
        "inner join cidade ON cidade.codigo = cliente.codigo_cidade ORDER BY cliente.codigo DESC";

        List<Cliente> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            // Percorre os resultados
            while (rs.next()) {

                Cliente c = new Cliente();

                // Preenche o objeto com os dados do banco
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setSexo(rs.getString("sexo"));
                c.setTelefone(rs.getString("telefone"));
                c.setCpf(rs.getString("cpf"));
                c.setCodigo_cidade(rs.getInt("codigo_cidade"));
                c.setNomeCidade(rs.getString("nome_cidade"));
                lista.add(c);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar Clientes.", ex);
        }

        return lista;
    }

}
