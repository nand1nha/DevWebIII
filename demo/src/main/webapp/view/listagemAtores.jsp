<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtil" %>
<%@ page import="model.domain.Ator" %>

<!DOCTYPE html>

<html> <head> <meta charset="UTF-8"> <title>Locadora</title> </head> <body>

<%

try (Session sessionHibernate = HibernateUtil.getSessionFactory().openSession()) {

System.out.println("====== Lista da Atores! ======= ");

List<Ator> atores = sessionHibernate
.createQuery("from Ator", Ator.class)
.getResultList();

out.println("<table border='1'>");
out.println("<tr>");
out.println("<th>ID</th>");
out.println("<th>Nome</th>");
out.println("<th>Editar</th>");
out.println("<th>Excluir</th>");

for (Ator ator : atores) {

out.println("<tr>");
out.println("<td>" + ator.getId() + "</td>");
out.println("<td>" + ator.getNome() + "</td>");
out.println("<td><a href='../view/editarAtor.jsp?id=" + ator.getId() + "'> Editar</a></td>");
out.println("<td><a href='../controller/SrvCadastrarAtor?operacao=excluir&id=" + ator.getId() + "'> Excluir</a></td>");
out.println("</tr>");
}

out.println("</table>");


} catch (Exception e) {

out.println("<p>Erro ao conectar com o banco:</p>");
out.println("<pre>");
e.printStackTrace(new java.io.PrintWriter(out));
out.println("</pre>");
}

%>

</body> </html>