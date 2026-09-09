<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtil" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teste Hibernate</title>
</head>

<body>
    <h2>Cadastro de Ator</h2>
    <form name="cadastroator" method="get" action="../controller/SrvCadastrarAtor">
        
        <input type="hidden" name="operacao" value="incluir"><br>
        
        Nome:<br>
        <input type="text" name="nome" value=""><br>

        <input type="submit" value="OK">
        <input type="reset"  value="Reset">
    </form>

</body>
</html>