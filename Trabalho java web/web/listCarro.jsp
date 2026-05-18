<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Carro"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Carros</title>
</head>
<body>

    <h1>Lista de Carros</h1>

    <p>
        <a href="carros?acao=novo">Novo Cadastro</a>
    </p>

    <%
        List<Carro> lista = (List<Carro>) request.getAttribute("lista");
    %>

    <table border="1">
        <tr>
            <th>Id</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Ano</th>
            <th>Cor</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>

        <%
            if (lista != null) {
                for (Carro c : lista) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getMarca() %></td>
            <td><%= c.getModelo() %></td>
            <td><%= c.getAno() %></td>
            <td><%= c.getCor() %></td>
            <td><%= c.getPreco() %></td>
            <td>
                <a href="carros?acao=editar&id=<%= c.getId() %>">Editar</a>
                <a href="carros?acao=excluir&id=<%= c.getId() %>">Excluir</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

</body>
</html>