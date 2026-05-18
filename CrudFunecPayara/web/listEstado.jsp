<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Estado" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Estado</title>
    </head>
    <body>

        <!-- Título da página -->
        <h2>Lista de Estados</h2>

        <!-- Link para abrir o formulário de novo cadastro -->
        <p>
            <a href="estados?acao=novo">Novo estado</a>
        </p>

        <%
            // Recupera do request a lista enviada pelo controller
            List<Estado> lista = (List<Estado>) request.getAttribute("lista");

            // Verifica se a lista está vazia ou nula
            if (lista == null || lista.isEmpty()) {
        %>
                <p>Nenhum estado cadastrado.</p>
        <%
            } else {
        %>

                <!-- Tabela de listagem -->
                <table border="1" cellpadding="6">
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sigla</th>
                        <th>Ações</th>
                    </tr>

                    <%
                        // Percorre todos os estados da lista
                        for (Estado e : lista) {
                    %>
                            <tr>
                                <!-- Exibe o código do estado -->
                                <td><%= e.getCodigo() %></td>

                                <!-- Exibe o nome do estado -->
                                <td><%= e.getNome() %></td>

                                <!-- Exibe a sigla -->
                                <td><%= e.getSigla() %></td>

                                <!-- Links de editar e excluir -->
                                <td>
                                    <a href="estados?acao=editar&id=<%= e.getCodigo() %>">Editar</a>
                                    |
                                    <a href="estados?acao=excluir&id=<%= e.getCodigo() %>"
                                       onclick="return confirm('Excluir este estado?');">
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