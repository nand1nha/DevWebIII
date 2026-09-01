<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtil" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teste Hibernate</title>
</head>

<body>

    <h1>Teste de conexão com o banco</h1>

    <%
    try (Session sessionHibernate =
            HibernateUtil.getSessionFactory().openSession()) {

        out.println("<p>Conexão com o banco realizada com sucesso!</p>");

    } catch (Exception e) {

        out.println("<p>Erro ao conectar com o banco:</p>");
        out.println("<pre>");
        e.printStackTrace(new java.io.PrintWriter(out));
        out.println("</pre>");
    }
    %>

    <h2>Cadastro de Cliente</h2>
    <form name="cadastrocliente" method="get" action="controller/SrvCadastrarPessoa">
        
        <input type="hidden" name="operacao" value="incluir"><br>
        
        Nome:<br>
        <input type="text" name="nome" value=""><br>
        CPF:<br>
        <input type="text" name="cpf" value=""><br>
        Endereco:<br>
        <input type="text" name="endereco" value=""><br>
        Sexo:<br>
        <input type="radio" name="sexo" value="masc">Masculino<input type="radio" name="sexo"
        value="fem">Feminino<br>
        Tipo:<br>
        <select name="tipo">
        <option value="Normal">Normal</option>
        <option value="VIP">VIP</option>
        </select><br>
        Receber comunicados:<br>
        <input type="checkbox" name="comunicados" value="sim"><br>
        Obs.:<br>
        <textarea name="obs" rows="4" cols="20"></textarea><br>
        <input type="submit" value="OK">
        <input type="reset"  value="Reset">
    </form>

</body>
</html>