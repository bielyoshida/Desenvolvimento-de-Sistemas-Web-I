<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Cidade" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Cidades</title>
    </head>
    <body>

        <h2>Lista de Cidades</h2>

        <p>
            <a href="cidades?acao=novo">Nova cidade</a>
        </p>

        <%
            List<Cidade> lista = (List<Cidade>) request.getAttribute("lista");

            if (lista == null || lista.isEmpty()) {
        %>
                <p>Nenhuma cidade cadastrada.</p>
        <%
            } else {
        %>
                <table border="1" cellpadding="6">
                    <tr>
                        <th>ID</th>
                        <th>Cidade</th>
                        <th>Estado</th>
                        <th>Sigla</th>
                        <th>Ações</th>
                    </tr>

                    <%
                        for (Cidade c : lista) {
                    %>
                    <tr>
                        <td><%= c.getCodigo() %></td>
                        <td><%= c.getNome() %></td>
                        <td><%= c.getEstado().getNome() %></td>
                        <td><%= c.getEstado().getSigla() %></td>
                        <td>
                            <a href="cidades?acao=editar&id=<%= c.getCodigo() %>">Editar</a>
                            |
                            <a href="cidades?acao=excluir&id=<%= c.getCodigo() %>"
                               onclick="return confirm('Excluir esta cidade?');">
                                Excluir
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
        <%
            }
        %>

    </body>
</html>
