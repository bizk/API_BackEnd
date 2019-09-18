package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import entitys.DuenioEntity;
import entitys.EdificioEntity;
import entitys.ImagenEntity;
import entitys.InquilinoEntity;
import entitys.PersonaEntity;
import entitys.ReclamoEntity;
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
        	 config.addAnnotatedClass(ImagenEntity.class);
             config.addAnnotatedClass(PersonaEntity.class);
             config.addAnnotatedClass(ReclamoEntity.class); 
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