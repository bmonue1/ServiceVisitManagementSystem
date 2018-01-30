package edu.westga.cs3212.ServiceTechMobileApplication.model;

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
