package edu.westga.cs3212.ServiceTechMobileApplication.model;

/** Valid status options for Tasks
 * 
 * @author CS3212
 * @version Spring 2018
 */
public enum TaskStatus {
	ASSIGNED,
	INCOMPLETE,
	COMPLETE,
	UNNECESSARY;

	@Override
	public String toString() {
		String text = "";
		if(this == ASSIGNED) {
			text = "ASSIGNED";
		}
		else if(this == INCOMPLETE) {
			text = "INCOMPLETE";
		}
		else if(this == COMPLETE) {
			text = "COMPLETE";
		}
		else if(this == UNNECESSARY) {
			text = "UNNECESSARY";
		}
		return text;
	}
}
