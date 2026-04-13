package dao;

// Importa a classe Produto do pacote model
import model.Produto;

// Importa a classe de conexão com o banco
import util.Conexao;

// Importa classes JDBC para trabalhar com banco de dados
import java.sql.*;

// Importa classes para trabalhar com lista
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    /**
     * Método responsável por inserir um novo produto no banco de dados.
     * 
     * @param p objeto Produto com os dados a serem gravados
     */
    public void inserir(Produto p) {

        // Comando SQL para inserir um novo registro na tabela produtos
        String sql = "INSERT INTO produtos (descricao, valor_compra, valor_venda, marca, quantidade) " +
                     "VALUES (?, ?, ?, ?, ?)";

        // try-with-resources: abre a conexão e o PreparedStatement
        // e garante que ambos serão fechados automaticamente ao final
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Substitui os ? da instrução SQL pelos valores do objeto Produto
            ps.setString(1, p.getDescricao());
            ps.setBigDecimal(2, p.getValorCompra());
            ps.setBigDecimal(3, p.getValorVenda());
            ps.setString(4, p.getMarca());
            ps.setInt(5, p.getQuantidade());

            // Executa o INSERT no banco
            ps.executeUpdate();

        } catch (SQLException e) {
            // Em caso de erro, lança uma exceção com mensagem personalizada
            throw new RuntimeException("Erro ao inserir produto.", e);
        }
    }

    /**
     * Método responsável por atualizar um produto existente no banco.
     * 
     * @param p objeto Produto com os novos dados
     */
    public void atualizar(Produto p) {

        // Comando SQL para atualizar um produto pelo ID
        String sql = "UPDATE produtos SET descricao=?, valor_compra=?, valor_venda=?, marca=?, quantidade=? " +
                     "WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Preenche os parâmetros da instrução SQL com os dados do produto
            ps.setString(1, p.getDescricao());
            ps.setBigDecimal(2, p.getValorCompra());
            ps.setBigDecimal(3, p.getValorVenda());
            ps.setString(4, p.getMarca());
            ps.setInt(5, p.getQuantidade());
            ps.setInt(6, p.getId());

            // Executa o UPDATE no banco
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto.", e);
        }
    }

    /**
     * Método responsável por excluir um produto do banco a partir do ID.
     * 
     * @param id identificador do produto
     */
    public void excluir(int id) {

        // Comando SQL para deletar um produto pelo ID
        String sql = "DELETE FROM produtos WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Define o ID que será usado na exclusão
            ps.setInt(1, id);

            // Executa o DELETE
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir produto.", e);
        }
    }

    /**
     * Método responsável por buscar um produto específico pelo ID.
     * 
     * @param id identificador do produto
     * @return objeto Produto encontrado ou null caso não exista
     */
    public Produto buscarPorId(int id) {

        // Comando SQL para buscar um produto pelo ID
        String sql = "SELECT id, descricao, valor_compra, valor_venda, marca, quantidade " +
                     "FROM produtos WHERE id=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Define o parâmetro ID na consulta
            ps.setInt(1, id);

            // Executa a consulta
            try (ResultSet rs = ps.executeQuery()) {

                // Se encontrou um registro
                if (rs.next()) {

                    // Cria um novo objeto Produto
                    Produto p = new Produto();

                    // Preenche o objeto com os dados vindos do banco
                    p.setId(rs.getInt("id"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setValorCompra(rs.getBigDecimal("valor_compra"));
                    p.setValorVenda(rs.getBigDecimal("valor_venda"));
                    p.setMarca(rs.getString("marca"));
                    p.setQuantidade(rs.getInt("quantidade"));

                    // Retorna o produto encontrado
                    return p;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por ID.", e);
        }

        // Se não encontrou nenhum produto, retorna null
        return null;
    }

    /**
     * Método responsável por listar todos os produtos cadastrados no banco.
     * 
     * @return lista de produtos
     */
    public List<Produto> listar() {

        // Comando SQL para buscar todos os produtos
        String sql = "SELECT id, descricao, valor_compra, valor_venda, marca, quantidade " +
                     "FROM produtos ORDER BY id DESC";

        // Cria a lista que armazenará os produtos encontrados
        List<Produto> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Percorre todos os registros retornados pela consulta
            while (rs.next()) {

                // Cria um novo objeto Produto para cada linha
                Produto p = new Produto();

                // Preenche o objeto com os dados da linha atual
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValorCompra(rs.getBigDecimal("valor_compra"));
                p.setValorVenda(rs.getBigDecimal("valor_venda"));
                p.setMarca(rs.getString("marca"));
                p.setQuantidade(rs.getInt("quantidade"));

                // Adiciona o produto na lista
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos.", e);
        }

        // Retorna a lista final
        return lista;
    }
}