
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Cliente;
import util.Conexao;

public class ClienteDAO {
    
public void inserir(Cliente c) {

        // Comando SQL de inserção
        String sql = "INSERT INTO cliente (nome, sexo, telefone, cpf, codigo_cidade) VALUES (?, ?, ?, ?, ?)";

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

            // Executa o UPDATE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar cliente.", ex);
        }
    }
}
