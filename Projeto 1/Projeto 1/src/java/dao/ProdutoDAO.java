package dao;

import modelo.Produto;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private DataSource ds;

    public ProdutoDAO() {
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/lojabd");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao localizar DataSource jdbc/LojaDS", e);
        }
    }

    public List<Produto> listar() {
        String sql = "SELECT id, descricao, valor_compra, valor_venda, marca, quantidade FROM produtos ORDER BY id DESC";
        List<Produto> lista = new ArrayList<>();

        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorCompra(rs.getBigDecimal("valor_compra"));
                p.setValorVenda(rs.getBigDecimal("valor_venda"));
                p.setMarca(rs.getString("marca"));
                p.setQuantidade(rs.getInt("quantidade"));
                lista.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
        return lista;
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT id, descricao, valor_compra, valor_venda, marca, quantidade FROM produtos WHERE id = ?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setValorCompra(rs.getBigDecimal("valor_compra"));
                    p.setValorVenda(rs.getBigDecimal("valor_venda"));
                    p.setMarca(rs.getString("marca"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto", e);
        }
        return null;
    }

    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (descricao, valor_compra, valor_venda, marca, quantidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDescricao());
            ps.setBigDecimal(2, p.getValorCompra());
            ps.setBigDecimal(3, p.getValorVenda());
            ps.setString(4, p.getMarca());
            ps.setInt(5, p.getQuantidade());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }

    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET descricao=?, valor_compra=?, valor_venda=?, marca=?, quantidade=? WHERE id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDescricao());
            ps.setBigDecimal(2, p.getValorCompra());
            ps.setBigDecimal(3, p.getValorVenda());
            ps.setString(4, p.getMarca());
            ps.setInt(5, p.getQuantidade());
            ps.setInt(6, p.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto", e);
        }
    }
}

