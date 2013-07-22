/**
 * 
 */
package com.hibdb;

import java.util.Date;

/**
 * @author Rafael Rezende
 *
 */
public class Alarm {
	
	private int alarmId;
	private String alarmDescr;
	private String alarmOwner;
	private Date alarmInstant;
	private boolean isAlarmPrivate;
	
	
	/**
	 * @param alarmId
	 * @param alarmDescr
	 * @param alarmOwner
	 * @param alarmInstant
	 * @param isAlarmPrivate
	 */
	public Alarm(int alarmId, String alarmDescr, String alarmOwner,
			Date alarmInstant, boolean isAlarmPrivate) {
		super();
		this.alarmId = alarmId;
		this.alarmDescr = alarmDescr;
		this.alarmOwner = alarmOwner;
		this.alarmInstant = alarmInstant;
		this.isAlarmPrivate = isAlarmPrivate;
	}


	/**
	 * @return the alarmId
	 */
	public int getAlarmId() {
		return alarmId;
	}


	/**
	 * @param alarmId the alarmId to set
	 */
	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}


	/**
	 * @return the alarmDescr
	 */
	public String getAlarmDescr() {
		return alarmDescr;
	}


	/**
	 * @param alarmDescr the alarmDescr to set
	 */
	public void setAlarmDescr(String alarmDescr) {
		this.alarmDescr = alarmDescr;
	}


	/**
	 * @return the alarmOwner
	 */
	public String getAlarmOwner() {
		return alarmOwner;
	}


	/**
	 * @param alarmOwner the alarmOwner to set
	 */
	public void setAlarmOwner(String alarmOwner) {
		this.alarmOwner = alarmOwner;
	}


	/**
	 * @return the alarmInstant
	 */
	public Date getAlarmInstant() {
		return alarmInstant;
	}


	/**
	 * @param alarmInstant the alarmInstant to set
	 */
	public void setAlarmInstant(Date alarmInstant) {
		this.alarmInstant = alarmInstant;
	}


	/**
	 * @return the isAlarmPrivate
	 */
	public boolean isAlarmPrivate() {
		return isAlarmPrivate;
	}


	/**
	 * @param isAlarmPrivate the isAlarmPrivate to set
	 */
	public void setAlarmPrivate(boolean isAlarmPrivate) {
		this.isAlarmPrivate = isAlarmPrivate;
	}
	
}
