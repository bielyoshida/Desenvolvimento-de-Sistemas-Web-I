package controller;

import dao.EstadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Estado;

@WebServlet("/estados")
public class EstadoController extends HttpServlet {

    // Objeto DAO responsável por acessar o banco
    private EstadoDAO dao;

    // Método executado quando o servlet é iniciado
    @Override
    public void init() {
        dao = new EstadoDAO();
    }

    /**
     * Método responsável por tratar requisições GET
     * Exemplos:
     * - listar
     * - novo
     * - editar
     * - excluir
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera a ação enviada pela URL
        String acao = request.getParameter("acao");

        // Se nenhuma ação vier, assume listar
        if (acao == null) acao = "listar";

        switch (acao) {

            case "novo":
                // Abre o formulário vazio para novo cadastro
                request.getRequestDispatcher("formEstado.jsp").forward(request, response);
                break;

            case "editar":
                // Recupera o ID enviado pela URL
                int idEdit = Integer.parseInt(request.getParameter("id"));

                // Busca o estado no banco
                Estado e = dao.buscarPorId(idEdit);

                // Envia o objeto para o JSP
                request.setAttribute("estado", e);

                // Abre o formulário preenchido
                request.getRequestDispatcher("formEstado.jsp").forward(request, response);
                break;

            case "excluir":
                // Recupera o ID enviado pela URL
                int idExc = Integer.parseInt(request.getParameter("id"));

                // Exclui o estado do banco
                dao.excluir(idExc);

                // Redireciona para a listagem
                response.sendRedirect(request.getContextPath() + "/estados?acao=listar");
                break;

            default: // listar
                // Busca todos os estados cadastrados
                List<Estado> lista = dao.listar();

                // Envia a lista para o JSP
                request.setAttribute("lista", lista);

                // Abre a tela de listagem
                request.getRequestDispatcher("listEstado.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Método responsável por tratar requisições POST
     * Usado para salvar (inserir ou alterar)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupera o código enviado pelo formulário
        String idStr = request.getParameter("codigo");

        // Cria o objeto Estado
        Estado e = new Estado();

        // Se o código existir, trata-se de uma edição
        if (idStr != null && !idStr.isBlank()) {
            e.setCodigo(Integer.parseInt(idStr));
        }

        // Preenche os demais dados do formulário
        e.setNome(request.getParameter("nome"));
        e.setSigla(request.getParameter("sigla"));

        // Decide se vai alterar ou inserir
        if (e.getCodigo() > 0) {
            dao.alterar(e);
        } else {
            dao.inserir(e);
        }

        // Redireciona para a tela de listagem
        response.sendRedirect(request.getContextPath() + "/estados?acao=listar");
    }
}