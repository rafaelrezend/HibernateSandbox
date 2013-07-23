package com.hibdb;

// Generated Jul 23, 2013 5:39:14 AM by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Home object for domain model class Alarms.
 * @see com.hibdb.Alarms
 * @author Hibernate Tools
 */
public class AlarmsHome {

	private static final Log log = LogFactory.getLog(AlarmsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			// Create Hibernate Configuration from file
			Configuration conf = new Configuration();
			conf.configure("hibernate.cfg.xml");
			
			// Create Service Registry
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
			
			// Create factory and session to handle calls
			SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
			return factory;
			
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Alarms transientInstance) {
		log.debug("persisting Alarms instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Alarms instance) {
		log.debug("attaching dirty Alarms instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Alarms instance) {
		log.debug("attaching clean Alarms instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			session.buildLockRequest(LockOptions.NONE).lock(instance);
			
			tx.commit();
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Alarms persistentInstance) {
		log.debug("deleting Alarms instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Alarms merge(Alarms detachedInstance) {
		log.debug("merging Alarms instance");
		try {
			Alarms result = (Alarms) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Alarms findById(java.lang.Integer id) {
		log.debug("getting Alarms instance with id: " + id);
		try {
			Alarms instance = (Alarms) sessionFactory.getCurrentSession().get(
					"com.hibdb.Alarms", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Alarms instance) {
		log.debug("finding Alarms instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.hibdb.Alarms")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
