package com.hibdb;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Alarm Data Access Object. Contains a session factory builder and methods to
 * perform transactions with the database.
 * 
 * @author Rafael Rezende
 * 
 */
public class AlarmDAO {

	/**
	 * Global session factory. It is a thread-safe unique factory for the whole
	 * application.
	 */
	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Section factory builder.
	 * 
	 * @return a session factory.
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Getter of global session factory.
	 * 
	 * @return global session factory.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Add alarm to the database. The alarm parameter receives a new ID (key)
	 * even if it had a previously assigned ID.
	 * 
	 * @param alarm
	 *            Alarm to be added.
	 */
	public void addAlarm(Alarm alarm) {

		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(alarm);
			session.getTransaction().commit();

			System.out.println("Alarm added -> id: " + alarm.getId());

		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	/**
	 * Update alarm in the database. The key is the alarm ID.
	 * 
	 * @param alarm
	 *            Alarm to be updated.
	 */
	public void updateAlarm(Alarm alarm) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(alarm);
			session.getTransaction().commit();

			System.out.println("Alarm updated -> id: " + alarm.getId());

		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			// session.flush();
			session.close();
		}
	}

	/**
	 * Delete the alarm passed as parameter. The key is the alarm ID. The ID
	 * must exist in the database. Otherwise the function generates an
	 * exception.
	 * 
	 * @param alarm
	 *            Alarm to be deleted.
	 */
	public void deleteAlarm(Alarm alarm) {
		Transaction trns = null;
		Session session = getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();

			session.delete(alarm);

			session.getTransaction().commit();

			System.out.println("Alarm deleted -> id: " + alarm.getId());

		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	/**
	 * List all alarms from the database. TO BE FIXED!
	 */
	public void listAlarms() {
		Transaction tx = null;
		Session session = getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			List alarms = session.createQuery("FROM alarms").list();
			for (Iterator iterator = alarms.iterator(); iterator.hasNext();) {
				Alarm alarm = (Alarm) iterator.next();
				System.out.print("ID: " + alarm.getId());
				System.out.print("Owner: " + alarm.getOwner());
				System.out.print("Instant: " + alarm.getInstant());
				System.out.println("Private: " + alarm.isPriv());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
