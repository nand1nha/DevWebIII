<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtil" %>
<%@ page import="model.domain.Ator" %>
<%@ page import="main.java.model.application.AplCadastrarAtor" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Ator ator = AplCadastrarAtor.buscarAtor(id); 
%>
<form action="../controller/SrvCadastrarAtor" method="get">
    <input type="hidden" name="operacao" value="alterar">
    <input type="hidden" name="id" value="<%= ator.getId() %>">
    <input type="text" name="nome" value="<%= ator.getNome() %>">
    <button type="submit">Salvar</button>
</form>