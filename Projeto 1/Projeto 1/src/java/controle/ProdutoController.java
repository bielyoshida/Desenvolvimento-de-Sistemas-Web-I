
package controle;

import dao.ProdutoDAO;
import modelo.produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoController extends HttpServlet {

    private ProdutoDAO dao;

    @Override
    public void init() {
        dao = new ProdutoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");
        if (acao == null) acao = "listar";

        switch (acao) {
            case "novo":
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "editar":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                produto p = dao.buscarPorId(idEdit);
                req.setAttribute("produto", p);
                req.getRequestDispatcher("form.jsp").forward(req, resp);
                break;

            case "excluir":
                int idExc = Integer.parseInt(req.getParameter("id"));
                dao.excluir(idExc);
                resp.sendRedirect("produtos?acao=listar");
                break;

            default: // listar
                List<produto> lista = dao.listar();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idStr = req.getParameter("id");

        produto p = new produto();
        if (idStr != null) {
            p.setId(Integer.parseInt(idStr));
        }

        p.setDescricao(req.getParameter("descricao"));
        p.setValorCompra(new BigDecimal(req.getParameter("valorCompra")));
        p.setValorVenda(new BigDecimal(req.getParameter("valorVenda")));
        p.setMarca(req.getParameter("marca"));
        p.setQuantidade(Integer.parseInt(req.getParameter("quantidade")));

        if (p.getId() > 0) dao.atualizar(p);
        else dao.inserir(p);

        resp.sendRedirect("produtos?acao=listar");
    }
}

