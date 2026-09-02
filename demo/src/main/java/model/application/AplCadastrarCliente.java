package main.java.model.application;

import model.domain.Pessoa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import utils.HibernateUtil; 

public class AplCadastrarCliente {

    public static int ERRO_NOMEINVALIDO = 1;
    public static int ERRO_PERSISTENCIA = 2;
    public static int ERRO_GERAL = 3;
    public static int SUCESSO = 4;


    public static int incluirCliente(String nome, String cpf, String endereco, String sexo, String tipo, String receberComunicados, String obs) {
        if (nome.equals("")) {
            return ERRO_NOMEINVALIDO;
        }

        // Criar o objeto a ser persistido
        Pessoa a = new Pessoa();
        a.setNome(nome);
        a.setCPF(cpf);
        a.setEndereco(endereco);
        a.setSexo(sexo);
        a.setTipo(tipo);
        a.setReceberComunicados(receberComunicados);
        a.setObs(obs);

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        Transaction t = null;
        try {
            t = session.beginTransaction();

            session.save(a);

            t.commit();

            return SUCESSO;
        } catch (HibernateException he) {

            t.rollback();

            return ERRO_PERSISTENCIA;

        } catch (Exception e) {

            t.rollback();

            return ERRO_GERAL;

        } finally {
            session.close();
        }
    }

    public static int excluirCliente(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();

            Pessoa pessoa = session.get(Pessoa.class, id);

            if (pessoa != null) {
                session.delete(pessoa);
            }

            t.commit();

            return SUCESSO;

        } catch (HibernateException e) {

            if (t != null) {
                t.rollback();
            }

            return ERRO_PERSISTENCIA;

        } finally {
            session.close();
        }
    }
    
}
