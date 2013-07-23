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

		// Creating first alarm with default values
		Alarm alarm1 = new Alarm();

		// Creating second alarm with input values (including ID)
		Alarm alarm2 = new Alarm(3, new Date(0), true);
		alarm2.setDescr("yada yada yada");
		alarm2.setId(6);

		// Creating DAO to handle DB transactions
		AlarmDAO alarmDAO = new AlarmDAO();

		// Adding alarm with default values
		alarmDAO.addAlarm(alarm1);

		/* After added, alarm1 has received the auto-incremental ID */
		System.out.println("ID of alarm1: " + alarm1.getId());

		/* Adding alarm with predefined ID */
		alarmDAO.addAlarm(alarm2);

		/*
		 * Even having a predefined ID, alarm2 has received a new
		 * auto-incremental ID
		 */
		System.out.println("ID of alarm2: " + alarm2.getId());

		/* So, to delete the just inserted alarm */
		alarmDAO.deleteAlarm(alarm2);

		/*
		 * Adding a new description to the default alarm and updating in the
		 * database. The reference is the ID of the alarm.
		 */
		alarm1.setDescr("yada yada yada");
		alarmDAO.updateAlarm(alarm1);

		/*
		 * So, if we change the ID and try to update, nothing happens, because
		 * the reference is another one, even if the object is still the same.
		 */
		alarm1.setId(99999);
		alarm1.setDescr("it should not happen!!!");
		alarmDAO.updateAlarm(alarm1);

		/* Lists all alarms in the database */
		alarmDAO.listAlarms();
	}
}
