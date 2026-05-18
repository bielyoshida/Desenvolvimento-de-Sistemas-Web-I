<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de clientes</title>
    </head>
    <body>
        <!-- Título -->
        <h2>Lista de Clientes</h2>

        <!-- Link para novo cadastro -->
        <p>
            <a href="clientes?acao=novo">Novo Clientes</a>
        </p>
        <%
        List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
        if (lista == null || lista.isEmpty()) {
        %>
        <p>Nenhum cliente cadastrado.</p>
        <%
            } else {
        %>

        <!-- Tabela de clientes -->
        <table border="1" cellpadding="6">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Sexo</th>
                <th>Telefone</th>
                <th>CPF</th>
                <th>Cidade</th>
                <th>Ações</th>
            </tr>
            <%
            // Percorre a lista de clientes
             for (Cliente c : lista) {// clientes com s esse era o erro
            %>
            <tr>
                <td><%= c.getCodigo() %></td>
                <td><%= c.getNome() %></td>
                <td><%= c.getSexo() %></td>
                <td><%= c.getTelefone() %></td>
                <td><%= c.getCpf() %></td>
                <td><%= c.getNomeCidade()%></td>

                <!-- Ações -->
                <td>
                    <a href="clientes?acao=editar&id=<%= c.getCodigo() %>">Editar</a>
                    |
                    <a href="clientes?acao=excluir&id=<%= c.getCodigo()%>"
                       onclick="return confirm('Excluir este Cliente?');">
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
