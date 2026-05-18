package controller;

import dao.CidadeDAO;
import dao.EstadoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Cidade;
import model.Estado;

@WebServlet("/cidades")
public class CidadeController extends HttpServlet {

    // DAO da cidade
    private CidadeDAO cidadeDAO;

    // DAO do estado, usado para preencher o combo de estados
    private EstadoDAO estadoDAO;

    @Override
    public void init() {
        cidadeDAO = new CidadeDAO();
        estadoDAO = new EstadoDAO();
    }

    /**
     * Trata as requisições GET.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        // Se não vier ação, assume listagem
        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {

            case "novo":
                // Busca os estados para preencher o select do formulário
                request.setAttribute("estados", estadoDAO.listar());
                request.getRequestDispatcher("formCidade.jsp").forward(request, response);
                break;

            case "editar":
                // Busca a cidade a ser editada
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Cidade cidade = cidadeDAO.buscarPorId(idEdit);

                // Busca todos os estados para o combo do formulário
                List<Estado> estados = estadoDAO.listar();

                request.setAttribute("cidade", cidade);
                request.setAttribute("estados", estados);
                request.getRequestDispatcher("formCidade.jsp").forward(request, response);
                break;

            case "excluir":
                int idExc = Integer.parseInt(request.getParameter("id"));
                cidadeDAO.excluir(idExc);
                response.sendRedirect(request.getContextPath() + "/cidades?acao=listar");
                break;

            default:
                // Lista todas as cidades
                List<Cidade> lista = cidadeDAO.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("listCidade.jsp").forward(request, response);
                break;
        }
    }

    /**
     * Trata as requisições POST.
     * Usado para salvar inserção ou edição.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoStr = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String codigoEstadoStr = request.getParameter("codigoEstado");

        // Monta o estado selecionado no formulário
        Estado estado = new Estado();
        estado.setCodigo(Integer.parseInt(codigoEstadoStr));

        // Monta a cidade
        Cidade cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setEstado(estado);

        // Se houver código, é edição
        if (codigoStr != null && !codigoStr.isBlank()) {
            cidade.setCodigo(Integer.parseInt(codigoStr));
        }

        if (cidade.getCodigo() > 0) {
            cidadeDAO.alterar(cidade);
        } else {
            cidadeDAO.inserir(cidade);
        }

        response.sendRedirect(request.getContextPath() + "/cidades?acao=listar");
    }
}
