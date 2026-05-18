<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Importa a classe Estado -->
<%@page import="model.Estado"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Estado</title>
    </head>

    <body>

        <!-- Título principal da página -->
        <h1>Cadastro de Estado</h1>

        <%
            // Recupera o objeto enviado pelo controller (se for edição)
            Estado mdEstado = (Estado) request.getAttribute("estado");

            // Verifica se está editando ou criando novo
            boolean editando = (mdEstado != null);
        %>

        <!-- Título dinâmico -->
        <h2><%= editando ? "Editar Estado" : "Novo Estado" %></h2>

        <!-- Formulário que envia os dados para o controller -->
        <form method="post" action="estados">

            <!-- Campo oculto (opcional, não está sendo usado no controller) -->
            <input type="hidden" name="acao" value="salvar">

            <% if (editando) { %>
                <!-- Se estiver editando, envia o código do estado -->
                <input type="hidden" name="codigo" value="<%= mdEstado.getCodigo() %>">
            <% } %>

            <!-- Campo Nome -->
            <p>
                Descrição:
                <input type="text" name="nome" required
                       value="<%= editando ? mdEstado.getNome() : "" %>">
            </p>

            <!-- Campo Sigla -->
            <p>
                Sigla:
                <input type="text" name="sigla" required maxlength="2"
                       value="<%= editando ? mdEstado.getSigla() : "" %>">
            </p>

            <!-- Botão de envio -->
            <button type="submit">Salvar</button>

            <!-- Link para cancelar e voltar para lista -->
            <a href="estados?acao=listar">Cancelar</a>

        </form>

    </body>
</html>