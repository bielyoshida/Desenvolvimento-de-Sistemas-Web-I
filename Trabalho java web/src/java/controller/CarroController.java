package controller;

import dao.CarroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Carro;

@WebServlet("/carros")
public class CarroController extends HttpServlet {

    private CarroDAO dao;

    @Override
    public void init() {
        dao = new CarroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {

            case "novo":
                request.getRequestDispatcher("formCarro.jsp").forward(request, response);
                break;

            case "editar":
                int idEdit = Integer.parseInt(request.getParameter("id"));

                Carro c = dao.buscarPorId(idEdit);

                request.setAttribute("carro", c);

                request.getRequestDispatcher("formCarro.jsp").forward(request, response);
                break;

            case "excluir":
                int idExc = Integer.parseInt(request.getParameter("id"));

                dao.excluir(idExc);

                response.sendRedirect(request.getContextPath() + "/carros?acao=listar");
                break;

            default:
                List<Carro> lista = dao.listar();

                request.setAttribute("lista", lista);

                request.getRequestDispatcher("listCarro.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idStr = request.getParameter("id");

        Carro c = new Carro();

        if (idStr != null && !idStr.isBlank()) {
            c.setId(Integer.parseInt(idStr));
        }

        c.setMarca(request.getParameter("marca"));
        c.setModelo(request.getParameter("modelo"));
        c.setAno(Integer.parseInt(request.getParameter("ano")));
        c.setCor(request.getParameter("cor"));
        c.setPreco(Double.parseDouble(request.getParameter("preco")));

        if (c.getId() > 0) {
            dao.alterar(c);
        } else {
            dao.inserir(c);
        }

        response.sendRedirect(request.getContextPath() + "/carros?acao=listar");
    }
}