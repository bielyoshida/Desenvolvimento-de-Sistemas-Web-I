<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.Cidade"%>
<%@page import="model.Cliente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Cliente</title>
    </head>
    <body>
        <h1>Cadastro de Cliente</h1>

        <%
            /*
                CORREÇÕES REALIZADAS NESTA JSP:
                1) O cast estava como List<Cidades>, mas a classe correta é Cidade.
                2) O formulário chamava métodos que não existem no model Cliente,
                   como getCidade() e getCodigo_idade().
                3) A classe Cidade possui getCodigo(), não getCodigo_cidade().
                4) O select de sexo foi ajustado para marcar corretamente a opção
                   salva no banco durante a edição.
            */
            Cliente mdCliente = (Cliente) request.getAttribute("cliente");
            List<Cidade> cidades = (List<Cidade>) request.getAttribute("cidades");
            boolean editando = (mdCliente != null);
        %>

        <h2><%= editando ? "Editar Cliente" : "Novo Cliente" %></h2>

        <form method="post" action="clientes">
            <% if (editando) { %>
                <input type="hidden" name="codigo" value="<%= mdCliente.getCodigo() %>">
            <% } %>

            <p>
                Nome do cliente:
                <input type="text" name="nome" required
                       value="<%= editando ? mdCliente.getNome() : "" %>">
            </p>

            <p>
                Sexo:
                <select name="sexo" required>
                    <option value="Masculino" <%= editando && "Masculino".equals(mdCliente.getSexo()) ? "selected" : "" %>>Masculino</option>
                    <option value="Feminino" <%= editando && "Feminino".equals(mdCliente.getSexo()) ? "selected" : "" %>>Feminino</option>
                </select>
            </p>

            <p>
                Telefone:
                <input type="text" name="telefone"
                       value="<%= editando ? mdCliente.getTelefone() : "" %>">
            </p>

            <p>
                CPF:
                <input type="text" name="cpf" required
                       value="<%= editando ? mdCliente.getCpf() : "" %>">
            </p>

            <p>
                Cidade:
                <select name="codigoCidade" required>
                    <option value="">Selecione</option>
                    <%
                        if (cidades != null) {
                            for (Cidade c : cidades) {
                                boolean selecionado = editando
                                        && mdCliente.getCodigo_cidade() == c.getCodigo();
                    %>
                                <option value="<%= c.getCodigo() %>" <%= selecionado ? "selected" : "" %>>
                                    <%= c.getNome() %>
                                </option>
                    <%
                            }
                        }
                    %>
                </select>
            </p>

            <button type="submit">Salvar</button>
            <a href="clientes?acao=listar">Cancelar</a>
        </form>
    </body>
</html>
