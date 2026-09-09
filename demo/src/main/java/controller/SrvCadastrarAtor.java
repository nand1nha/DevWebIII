package main.java.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.application.AplCadastrarAtor;
import model.domain.Ator;
import utils.HibernateUtil;

@WebServlet("/controller/SrvCadastrarAtor")
public class SrvCadastrarAtor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response)
    throws ServletException, IOException {


    String aux_nome = request.getParameter("nome");
    
    String aux_operacao = request.getParameter("operacao");


    switch (aux_operacao) {
        case "incluir":
        
            int retorno = AplCadastrarAtor.incluirAtor(aux_nome);
            if (retorno == 4){
                //chama pagina de sucesso
                response.sendRedirect("../view/listagemAtores.jsp");
            }else{
                //chama pagina de erro.
                response.sendRedirect("../view/erro.jsp?msg=" + mensagemErro(retorno));
            }
                
            break;
            
        case "alterar":

            int idAlterar = Integer.parseInt(request.getParameter("id"));
            int retornoAlterar = AplCadastrarAtor.alterarAtor(idAlterar, aux_nome);
            if (retornoAlterar == 4) {
                response.sendRedirect("../view/listagemAtores.jsp");
            } else {
                //chama pagina de erro.
                response.sendRedirect("../view/erro.jsp?msg=" + mensagemErro(retornoAlterar));
            }
        
            break;

        case "excluir":
            int id = Integer.parseInt(request.getParameter("id"));
            int retornoExcluir = AplCadastrarAtor.excluirAtor(id);
            if (retornoExcluir == 4){
                //chama pagina de sucesso
                response.sendRedirect("../view/listagemAtores.jsp");
            }else{
                //chama pagina de erro.
                response.sendRedirect("../view/erro.jsp?msg=" + mensagemErro(retornoExcluir));
            }    
            break;
    }


    System.out.println(aux_nome);
    }

    private String mensagemErro(int codigo) {
    switch (codigo) {
        case 1: return "Nome inválido.";
        case 2: return "Erro ao acessar o banco de dados.";
        case 3: return "Erro inesperado no sistema.";
        default: return "Erro desconhecido.";
    }
}

}
