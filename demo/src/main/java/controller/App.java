package controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.domain.Pessoa;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        Pessoa p = new Pessoa();
        p.setNome("João");
        session.persist(p);

        session.getTransaction().commit();
        session.close();
        factory.close();

        System.out.println("Conectado e salvo com sucesso!");
    }
}
