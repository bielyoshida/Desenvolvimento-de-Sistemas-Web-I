<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Importa a classe Produto -->
<%@page import="model.Produto"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produto</title>
</head>
<body>

<%
    // Recupera o objeto enviado pelo controller (se for edição)
    Produto produto = (Produto) request.getAttribute("produto");

    // Verifica se está editando ou criando novo
    boolean editando = (produto != null);
%>

<!-- Título dinâmico -->
<h2><%= editando ? "Editar Produto" : "Novo Produto" %></h2>

<!-- Formulário que envia para o controller -->
<form method="post" action="produtos">

    <% if (editando) { %>
        <!-- Campo oculto com ID (necessário para UPDATE) -->
        <input type="hidden" name="id" value="<%= produto.getId() %>">
    <% } %>

    <!-- Campo Descrição -->
    <p>Descrição:
        <input type="text" name="descricao" required
               value="<%= editando ? produto.getDescricao() : "" %>">
    </p>

    <!-- Campo Valor de Compra -->
    <p>Valor Compra:
        <input type="number" step="0.01" name="valorCompra" required
               value="<%= editando ? produto.getValorCompra() : "" %>">
    </p>

    <!-- Campo Valor de Venda -->
    <p>Valor Venda:
        <input type="number" step="0.01" name="valorVenda" required
               value="<%= editando ? produto.getValorVenda() : "" %>">
    </p>

    <!-- Campo Marca -->
    <p>Marca:
        <input type="text" name="marca" required
               value="<%= editando ? produto.getMarca() : "" %>">
    </p>

    <!-- Campo Quantidade -->
    <p>Quantidade:
        <input type="number" name="quantidade" required
               value="<%= editando ? produto.getQuantidade() : 0 %>">
    </p>

    <!-- Botão de envio -->
    <button type="submit">Salvar</button>

    <!-- Voltar para listagem -->
    <a href="produtos?acao=listar">Cancelar</a>

</form>

</body>
</html>