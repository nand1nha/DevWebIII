package main.java.model.application;

import model.domain.Ator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import utils.HibernateUtil; 

public class AplCadastrarAtor {

    public static int ERRO_NOMEINVALIDO = 1;
    public static int ERRO_PERSISTENCIA = 2;
    public static int ERRO_GERAL = 3;
    public static int SUCESSO = 4;


    public static int incluirAtor(String nome) {
        if (nome.equals("")) {
            return ERRO_NOMEINVALIDO;
        }

        // Criar o objeto a ser persistido
        Ator a = new Ator();
        a.setNome(nome);

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

    public static int excluirAtor(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();

            Ator ator = session.get(Ator.class, id);

            if (ator != null) {
                session.delete(ator);
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

    public static Ator buscarAtor(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ator ator = session.get(Ator.class, id);
        session.close();
        return ator;
    }

    public static int alterarAtor(int id, String nome){
        if (nome.equals("")) {
            return ERRO_NOMEINVALIDO;
        }

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();

        Transaction t = null;
        try {
            t = session.beginTransaction();

            Ator a = session.get(Ator.class, id);
            a.setNome(nome);

            session.update(a);

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

    
    
}
