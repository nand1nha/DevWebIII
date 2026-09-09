<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String mensagem = request.getParameter("msg");
    if (mensagem == null || mensagem.equals("")) {
        mensagem = "Ocorreu um erro inesperado.";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Erro</title>
</head>
<body>
    <h2>Ops! Algo deu errado</h2>
    <p><%= mensagem %></p>
    <a href="listagemAtores.jsp">Voltar para a listagem</a>
</body>
</html>