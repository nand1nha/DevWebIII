<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtil" %>
<%@ page import="model.domain.Pessoa" %>

<!DOCTYPE html>

<html> <head> <meta charset="UTF-8"> <title>Teste Hibernate</title> </head> <body>

<h1>Teste de conexão com o banco</h1>

<%

try (Session sessionHibernate = HibernateUtil.getSessionFactory().openSession()) {

System.out.println("====== Conexão com o banco realizada com sucesso! ======= ");

List<Pessoa> pessoas = sessionHibernate
.createQuery("from Pessoa", Pessoa.class)
.getResultList();

out.println("<table border='1'>");
out.println("<tr>");
out.println("<th>ID</th>");
out.println("<th>Nome</th>");

for (Pessoa pessoa : pessoas) {

out.println("<tr>");
out.println("<td>" + pessoa.getId() + "</td>");
out.println("<td><a href='SrvCadastrarPessoa?aux_operacao=excluir&id=" + pessoa.getId() + "'>" + pessoa.getNome() + "</a></td>");
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