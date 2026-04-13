<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Importações necessárias -->
<%@page import="java.util.List"%>
<%@page import="model.Produto"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produtos</title>
</head>
<body>

<!-- Título -->
<h2>Lista de Produtos</h2>

<!-- Link para novo cadastro -->
<p>
    <a href="produtos?acao=novo">Novo Produto</a>
</p>

<%
    // Recupera a lista enviada pelo controller
    List<Produto> lista = (List<Produto>) request.getAttribute("lista");

    // Verifica se está vazia
    if (lista == null || lista.isEmpty()) {
%>
    <p>Nenhum produto cadastrado.</p>
<%
    } else {
%>

<!-- Tabela de produtos -->
<table border="1" cellpadding="6">
    <tr>
        <th>ID</th>
        <th>Descrição</th>
        <th>Compra</th>
        <th>Venda</th>
        <th>Marca</th>
        <th>Qtd</th>
        <th>Ações</th>
    </tr>

<%
    // Percorre a lista de produtos
    for (Produto p : lista) {
%>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getDescricao() %></td>

        <!-- Valores monetários -->
        <td><%= p.getValorCompra() %></td>
        <td><%= p.getValorVenda() %></td>

        <td><%= p.getMarca() %></td>
        <td><%= p.getQuantidade() %></td>

        <!-- Ações -->
        <td>
            <a href="produtos?acao=editar&id=<%= p.getId() %>">Editar</a>
            |
            <a href="produtos?acao=excluir&id=<%= p.getId() %>"
               onclick="return confirm('Excluir este produto?');">
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