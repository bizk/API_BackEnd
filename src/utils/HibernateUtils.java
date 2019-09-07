package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entitys.EdificioEntity;
import entitys.PersonaEntity;
import modelo.*;

public class HibernateUtils
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
             config.addAnnotatedClass(EdificioEntity.class);
        	 // config.addAnnotatedClass(Imagen.class);
             config.addAnnotatedClass(PersonaEntity.class);
             //config.addAnnotatedClass(Reclamo.class);
             //config.addAnnotatedClass(Unidad.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}