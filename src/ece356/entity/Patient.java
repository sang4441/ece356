/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.entity;

/**
 * 
 * @author Wojciech Golab
 */
public class Patient {
	private int id;
	private int person_id;
	private int default_doc;
	private String health_card;
	private int sin;
	private String current_health;

	public Patient(int id, int person_id, int default_doc, String health_card,
			int sin, String current_health) {
		this.id = id;
		this.person_id = person_id;
		this.default_doc = default_doc;
		this.health_card = health_card;
		this.sin = sin;
		this.current_health = current_health;
	}

	/**
	 * Get the value of salary
	 * 
	 * @return the value of salary
	 */
	public int getID() {
		return id;
	}

	/**
	 * Get the value of deptID
	 * 
	 * @return the value of deptID
	 */
	public int getPersonID() {
		return person_id;
	}

	/**
	 * Set the value of deptID
	 * 
	 * @param deptID
	 *            new value of deptID
	 */
	public void setSIN(int sin) {
		this.sin = sin;
	}

	/**
	 * Get the value of deptID
	 * 
	 * @param deptID
	 *            new value of deptID
	 */
	public int getSIN() {
		return sin;
	}

	public int getDefaultDoc() {
		return default_doc;
	}

	public String getCurrentHealth() {
		return current_health;
	}

	public int getHealthCard() {
		return sin;
	}

}
