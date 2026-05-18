package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import model.Carro;
import util.Conexao;

public class CarroDAO {

    public void inserir(Carro c) {

        String sql = "INSERT INTO carros (marca, modelo, ano, cor, preco) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAno());
            ps.setString(4, c.getCor());
            ps.setDouble(5, c.getPreco());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir carro.", e);
        }
    }

    public void alterar(Carro c) {

        String sql = "UPDATE carros SET marca = ?, modelo = ?, ano = ?, cor = ?, preco = ? WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setInt(3, c.getAno());
            ps.setString(4, c.getCor());
            ps.setDouble(5, c.getPreco());
            ps.setInt(6, c.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar carro.", e);
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM carros WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir carro.", e);
        }
    }

    public Carro buscarPorId(int id) {

        String sql = "SELECT id, marca, modelo, ano, cor, preco FROM carros WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Carro c = new Carro();

                    c.setId(rs.getInt("id"));
                    c.setMarca(rs.getString("marca"));
                    c.setModelo(rs.getString("modelo"));
                    c.setAno(rs.getInt("ano"));
                    c.setCor(rs.getString("cor"));
                    c.setPreco(rs.getDouble("preco"));

                    return c;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar carro por id.", e);
        }

        return null;
    }

    public List<Carro> listar() {

        String sql = "SELECT id, marca, modelo, ano, cor, preco FROM carros ORDER BY id DESC";

        List<Carro> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Carro c = new Carro();

                c.setId(rs.getInt("id"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setAno(rs.getInt("ano"));
                c.setCor(rs.getString("cor"));
                c.setPreco(rs.getDouble("preco"));

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar carros.", e);
        }

        return lista;
    }
}