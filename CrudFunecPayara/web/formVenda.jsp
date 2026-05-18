
<%@page import="model.Cliente"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendas</title>
    </head>
    <body>
        <h1>Formulário de venda</h1>
        <%
            List<Cliente> clientes =  (List<Cliente>) request.getAttribute("listaCliente");
        %>
        <form>
            <label>Código da venda:</label>
            <input type="number" name="codVenda" id="codVenda"/> 
            <label>Nome do cliente:</label>
            <select>
                <%
                    
                %>
                <option></option>
            </select>
            <br/><br/>
            <label>Insira um produto</label>
            <input type="text" name="descProduto" id="idProd"/>
            <input type="button" name="addProd" id="addProd" value="Add"/>
            <br/><br/> 
            <table border="1">
                <thead>
                <th style="font-weight: bold">
                    <td>Código de produto</td>
                    <td>Descrição</td>
                    <td>Quantidade</td>
                    <td>Valor total</td>
                </th>
                </thead>
            </table>
            <br/>
            <label>Valor do desconto:</label>
            <input type="text" name="valorDesconto" id="valorDesconto"/> 
            <label>Valor total:</label>
            <input type="text" name="nomeCliente" id="nomeCliente"/> 
        </form>
    </body>
</html>
