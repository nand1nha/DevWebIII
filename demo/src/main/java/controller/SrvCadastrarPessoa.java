package main.java.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.application.AplCadastrarCliente;
import model.domain.Pessoa;
import utils.HibernateUtil;

@WebServlet("/controller/SrvCadastrarPessoa")
public class SrvCadastrarPessoa extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {


    String aux_nome = request.getParameter("nome");
    String aux_cpf = request.getParameter("cpf");
    String aux_endereco = request.getParameter("endereco");
    String aux_sexo = request.getParameter("sexo");
    String aux_tipo = request.getParameter("tipo");
    String aux_comunicados = request.getParameter("comunicados");
    String aux_obs = request.getParameter("obs");
    
    String aux_operacao = request.getParameter("operacao");


    switch (aux_operacao) {
        case "incluir":
        
            int retorno = AplCadastrarCliente.incluirCliente(aux_nome, aux_cpf, aux_endereco, aux_sexo, aux_tipo, aux_comunicados, aux_obs);
            if (retorno == 4){
                //chama pagina de sucesso
                response.sendRedirect("../listagem-atores.jsp");
            }else
                //chama pagina de erro.
                
                break;
            
        case "alterar":
            
            break;

        case "excluir":
            int id = Integer.parseInt(request.getParameter("id"));

            AplCadastrarCliente.excluirCliente(id);

            response.sendRedirect("../listagem-atores.jsp");

            break;
    }


    System.out.println(aux_nome);
    }

}
