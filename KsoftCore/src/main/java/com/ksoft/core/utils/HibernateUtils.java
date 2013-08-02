

package com.ksoft.core.utils;

import java.net.URL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
public class HibernateUtils {
	
	private static HibernateUtils instance = null;
	private static SessionFactory sessionFactory = null;
	
	public static HibernateUtils getInstance(){
		
		if(null == instance){
			instance = new HibernateUtils();
		}
		return instance;
	}
  
	public  void initSessionFactory(AnnotationConfiguration cfg) {
		try {
						
			
			HibernateUtils.sessionFactory = cfg.configure().buildSessionFactory();	
 
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
 
	public SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}
 
	public  void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
 
}