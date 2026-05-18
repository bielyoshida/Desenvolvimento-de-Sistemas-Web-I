package controller;

// Importa o DAO responsável pelo acesso ao banco
import dao.ProdutoDAO;

// Importações do Servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Classe modelo Produto
import model.Produto;

// Outras importações
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

// Mapeia a URL: /produtos
@WebServlet("/produtos")
public class ProdutoController extends HttpServlet {

    private ProdutoDAO dao;

    // Método executado quando o servlet inicia
    @Override
    public void init() {
        dao = new ProdutoDAO(); // Instancia o DAO
    }

    /**
     * Método responsável por tratar requisições GET
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Recupera a ação enviada pela URL
        String acao = req.getParameter("acao");

        // Se não vier nada, assume listar
        if (acao == null) acao = "listar";

        switch (acao) {

            case "novo":
                // Abre o formulário vazio
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "editar":
                // Recupera o ID enviado na URL
                int idEdit = Integer.parseInt(req.getParameter("id"));

                // Busca o produto no banco
                Produto p = dao.buscarPorId(idEdit);

                // Envia o produto para o JSP
                req.setAttribute("produto", p);

                // Abre o formulário preenchido
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "excluir":
                // Recupera ID
                int idExc = Integer.parseInt(req.getParameter("id"));

                // Remove do banco
                dao.excluir(idExc);

                // Redireciona para lista
                resp.sendRedirect("produtos?acao=listar");
                break;

            default: // listar
                // Busca todos os produtos
                List<Produto> lista = dao.listar();

                // Envia para o JSP
                req.setAttribute("lista", lista);

                // Abre a página de listagem
                req.getRequestDispatcher("list.jsp").forward(req, resp);
        }
    }

    /**
     * Método responsável por tratar requisições POST (salvar)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");

        Produto p = new Produto();

        // Se tiver ID → é edição
        if (idStr != null && !idStr.isBlank()) {
            p.setId(Integer.parseInt(idStr));
        }

        // Preenche os dados vindos do formulário
        p.setDescricao(req.getParameter("descricao"));

        // Conversão para BigDecimal (valores monetários)
        p.setValorCompra(new BigDecimal(req.getParameter("valorCompra")));
        p.setValorVenda(new BigDecimal(req.getParameter("valorVenda")));

        p.setMarca(req.getParameter("marca"));
        p.setQuantidade(Integer.parseInt(req.getParameter("quantidade")));

        // Decide se insere ou atualiza
        if (p.getId() > 0) {
            dao.atualizar(p);
        } else {
            dao.inserir(p);
        }

        // Redireciona para listagem
        resp.sendRedirect("produtos?acao=listar");
    }
}