/**
 * 
 */
package com.hibdb;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Rafael Rezende
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Alarm alarm1 = new Alarm();
		Alarm alarm2 = new Alarm(3, new Date(0), true);
		
		alarm2.setDescr("yada yada yada");
		
		AlarmsHome alarmDAO = new AlarmsHome();
//		alarmDAO.attachClean(alarm1);
		alarmDAO.attachClean(alarm2);
		
//		SessionFactory factory = createHibernateFactory();
//		saveAlarm(factory.openSession(), alarm2);

	}
	
	private static SessionFactory createHibernateFactory(){
		
		// Create Hibernate Configuration from file
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		
		// Create Service Registry
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		
		// Create factory and session to handle calls
		SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
				
		return factory;
	}
	
	private static void saveAlarm(Session session, Alarm alarm){
		
		Transaction trans = null;
		try {
			// Begin transaction
			trans = session.beginTransaction();
			// Save alarm to the session
			session.save(alarm);
			// Commit transaction
			trans.commit();
		} catch (Exception e) {
			if (trans!=null) trans.rollback();
			e.printStackTrace();
		}
	}
}
