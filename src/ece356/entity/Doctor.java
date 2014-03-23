package ece356.entity;

public class Doctor {

	private int id;
	private int person_id;


	public Doctor() {

	}

	public Doctor(int id, int person_id){

		this.id = id;
		this.person_id = person_id;

	}


	/**
	 * Get the value of salary
	 * 
	 * @return the value of salary
	 */
	public int getID() {
		return id;
	}

	public void setID(int id) {

		this.id = id;

	}

	/**
	 * Get the value of deptID
	 * 
	 * @return the value of deptID
	 */
	public int getPersonID() {
		return person_id;
	}

	public void setPersonID(int person_id) {

		this.person_id = person_id;
		
	}



}