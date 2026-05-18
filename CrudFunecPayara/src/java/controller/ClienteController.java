package controller;

import dao.CidadeDAO;
import dao.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Cliente;

/**
 * Controller responsável pelo CRUD de clientes.
 *
 * Correção realizada:
 * - O cadastro de cliente precisa carregar a lista de cidades, pois o formulário
 *   possui um <select> para escolher a cidade do cliente.
 * - No editar, o atributo enviado para a JSP deve ser um único Cliente, não uma
 *   lista de clientes.
 * - No POST, o código pode vir vazio quando é um novo cadastro; por isso ele só
 *   é convertido para int quando realmente existe valor.
 */
@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO;
    private CidadeDAO cidadeDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
        cidadeDAO = new CidadeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        // Quando nenhuma ação for informada, o sistema abre a listagem.
        if (acao == null || acao.isBlank()) {
            acao = "listar";
        }

        switch (acao) {
            case "novo":
                // CORREÇÃO: o formulário precisa receber a lista de cidades.
                request.setAttribute("cidades", cidadeDAO.listar());
                request.getRequestDispatcher("formCliente.jsp").forward(request, response);
                break;

            case "editar":
                int idEdit = Integer.parseInt(request.getParameter("id"));

                // Busca o cliente selecionado para preencher o formulário.
                Cliente cliente = clienteDAO.buscarPorId(idEdit);

                // CORREÇÃO: envia um objeto Cliente para a JSP, não uma lista.
                request.setAttribute("cliente", cliente);

                // CORREÇÃO: também envia as cidades para montar o combo/select.
                request.setAttribute("cidades", cidadeDAO.listar());

                request.getRequestDispatcher("formCliente.jsp").forward(request, response);
                break;

            case "excluir":
                int idExc = Integer.parseInt(request.getParameter("id"));
                clienteDAO.excluir(idExc);
                response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
                break;

            case "listar":
            default:
                List<Cliente> lista = clienteDAO.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("listCliente.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String codigoStr = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String sexo = request.getParameter("sexo");
        String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");
        String codigoCidade = request.getParameter("codigoCidade");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setSexo(sexo);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setCodigo_cidade(Integer.parseInt(codigoCidade));

        // CORREÇÃO: no cadastro novo o campo codigo não existe ou vem vazio.
        // Portanto, só convertemos para int quando há valor.
        if (codigoStr != null && !codigoStr.isBlank()) {
            cliente.setCodigo(Integer.parseInt(codigoStr));
        }

        // Se tiver código maior que zero, altera. Caso contrário, insere.
        if (cliente.getCodigo() > 0) {
            clienteDAO.alterar(cliente);
        } else {
            clienteDAO.inserir(cliente);
        }

        response.sendRedirect(request.getContextPath() + "/clientes?acao=listar");
    }
}
