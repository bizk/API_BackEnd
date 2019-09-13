package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entitys.DuenioEntity;
import entitys.EdificioEntity;
import entitys.InquilinoEntity;
import entitys.PersonaEntity;
import entitys.UnidadEntity;

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
             config.addAnnotatedClass(DuenioEntity.class);
             config.addAnnotatedClass(UnidadEntity.class);
             config.addAnnotatedClass(InquilinoEntity.class);
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