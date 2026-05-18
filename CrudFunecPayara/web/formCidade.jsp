<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cidade"%>
<%@page import="model.Estado"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Cidade</title>
    </head>
    <body>

        <h1>Cadastro de Cidade</h1>

        <%
            // Cidade enviada pelo controller em caso de edição
            Cidade mdCidade = (Cidade) request.getAttribute("cidade");

            // Lista de estados usada para preencher o combo
            List<Estado> estados = (List<Estado>) request.getAttribute("estados");

            // Verifica se a tela está em modo de edição
            boolean editando = (mdCidade != null);
        %>

        <h2><%= editando ? "Editar Cidade" : "Nova Cidade" %></h2>

        <form method="post" action="cidades">

            <% if (editando) { %>
                <!-- Em edição, envia o código da cidade -->
                <input type="hidden" name="codigo" value="<%= mdCidade.getCodigo() %>">
            <% } %>

            <p>
                Nome da cidade:
                <input type="text" name="nome" required
                       value="<%= editando ? mdCidade.getNome() : "" %>">
            </p>

            <p>
                Estado:
                <select name="codigoEstado" required>
                    <option value="">Selecione</option>

                    <%
                        if (estados != null) {
                            for (Estado e : estados) {
                                boolean selecionado = editando
                                        && mdCidade.getEstado() != null
                                        && mdCidade.getEstado().getCodigo() == e.getCodigo();
                    %>
                        <option value="<%= e.getCodigo() %>" <%= selecionado ? "selected" : "" %>>
                            <%= e.getNome() %> - <%= e.getSigla() %>
                        </option>
                    <%
                            }
                        }
                    %>
                </select>
            </p>

            <button type="submit">Salvar</button>
            <a href="cidades?acao=listar">Cancelar</a>
        </form>

    </body>
</html>
