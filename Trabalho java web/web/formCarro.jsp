<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Carro"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Carros</title>
</head>
<body>

    <h1>Cadastro de Carros</h1>

    <%
        Carro carro = (Carro) request.getAttribute("carro");
    %>

    <form action="carros" method="post">

        <%
            if (carro != null) {
        %>
            <input type="hidden" name="id" value="<%= carro.getId() %>">
        <%
            }
        %>

        <p>
            Marca:
            <input type="text" name="marca"
                   value="<%= carro != null ? carro.getMarca() : "" %>" required>
        </p>

        <p>
            Modelo:
            <input type="text" name="modelo"
                   value="<%= carro != null ? carro.getModelo() : "" %>" required>
        </p>

        <p>
            Ano:
            <input type="number" name="ano"
                   value="<%= carro != null ? carro.getAno() : "" %>" required>
        </p>

        <p>
            Cor:
            <input type="text" name="cor"
                   value="<%= carro != null ? carro.getCor() : "" %>">
        </p>

        <p>
            Preço:
            <input type="number" step="0.01" name="preco"
                   value="<%= carro != null ? carro.getPreco() : "" %>" required>
        </p>

        <button type="submit">Salvar</button>
        <a href="carros?acao=listar">Voltar</a>

    </form>

</body>
</html>