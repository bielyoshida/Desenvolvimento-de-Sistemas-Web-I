
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Venda;
import util.Conexao;
/**
 *
 * @author Gabriel
 */
public class VendaDAO {
    public void inserir (Venda v){
        String sql = "insert into venda(codigo_cliente, valor_compra, valor_desconto, valor_total) values (?,?,?,?)";
        
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os ? da instrução SQL pelos valores do objeto Produto
            ps.setInt(1, v.getCodigo_cliente());
            ps.setFloat(2, v.getValor_compra());
            ps.setFloat(3, v.getValor_desconto());
            ps.setFloat(4, v.getValor_total());

            // Executa o INSERT no banco
            ps.execute();

        } catch (SQLException ex) {
            // Em caso de erro, lança uma exceção com mensagem personalizada
            throw new RuntimeException("Erro ao abrir a venda.", ex);
        }
    }
    
    public void inserirItensVenda (Venda v){
        String sql = "insert into venda(codigo_venda, codigo_produto, quantidade) values (?,?,?)";
        
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os ? da instrução SQL pelos valores do objeto Produto
            ps.setInt(1, v.getCodigo_venda());
            ps.setFloat(2, v.getCodigo_produto());
            ps.setInt(3, v.getQuantidade_vendida());

            // Executa o INSERT no banco
            ps.executeUpdate();

        } catch (SQLException ex) {
            // Em caso de erro, lança uma exceção com mensagem personalizada
            throw new RuntimeException("Erro ao inserir item na venda.", ex);
        }
    }
    public void alterarVenda (Venda v){
        String sql = "update venda set codigo_cliente =?, valor_compra =?, valor_desconto =?, valor_total =? where codigo =?";
        
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os ? da instrução SQL pelos valores do objeto Produto
            ps.setInt(1, v.getCodigo_cliente());
            ps.setFloat(2, v.getValor_compra());
            ps.setFloat(3, v.getValor_desconto());
            ps.setFloat(4, v.getValor_total());
            ps.setInt(5, v.getCodigo_venda());

            // Executa o INSERT no banco
            ps.executeUpdate();

        } catch (SQLException ex) {
            // Em caso de erro, lança uma exceção com mensagem personalizada
            throw new RuntimeException("Erro ao alterar a venda.", ex);
        }
    }
    
    public void alterarItemVenda (Venda v){
        String sql = "update itens_venda_produto set codigo_venda =?, codigo_produto =?, quantidade=? where codigo =?";
        
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os ? da instrução SQL pelos valores do objeto Produto
            ps.setInt(1, v.getCodigo_venda());
            ps.setFloat(2, v.getCodigo_produto());
            ps.setInt(3, v.getQuantidade_vendida());
            ps.setInt(4, v.getCodigo_item_venda());
            


            // Executa o INSERT no banco
            ps.executeUpdate();

        } catch (SQLException ex) {
            // Em caso de erro, lança uma exceção com mensagem personalizada
            throw new RuntimeException("Erro ao alterar o intem da venda.", ex);
        }
    }
    
    public void excluirVenda(int id) {

        // Comando SQL de exclusão
        String sql = "DELETE FROM venda WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            // Executa o DELETE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir venda.", ex);
        }
    }
    
    public void excluirItemVenda(int idItem) {

        // Comando SQL de exclusão
        String sql = "DELETE FROM itens_enda_produto WHERE codigo = ?";

        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idItem);

            // Executa o DELETE
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao excluir o item da venda.", ex);
        }
    }
    
    public Venda buscarVendaID (int idVenda) {
        String sql = "select from venda where codigo = ?";
    
        try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenda);
            
            try (ResultSet rs = ps.executeQuery()){
                 
                if (rs.next()){
                    Venda v = new Venda();
                    
                    v.setCodigo_venda(rs.getInt("codigo"));
                    v.setCodigo_cliente(rs.getInt("codigo_cliente"));
                    v.setValor_compra(rs.getFloat("valor_compra"));
                    
                    return v;
                }
            }
            
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao buscar venda.", ex);
        }
        return null;
    }
    public Venda buscarItemVendaID (int idItem) {
        String sql = "select from itens_venda_produto where codigo = ?";
        
         try (Connection con = Conexao.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            
            try (ResultSet rs = ps.executeQuery()){
                 
                if (rs.next()){
                    Venda v = new Venda();
                    
                    v.setCodigo_venda(rs.getInt("codigo_venda"));
                    v.setCodigo_produto(rs.getInt("codigo_produto"));
                    v.setQuantidade_vendida(rs.getInt("quantidade"));
                    
                    return v;
                }
            }
            
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao buscar item da venda.", ex);
        }
        return null;
    }
}


