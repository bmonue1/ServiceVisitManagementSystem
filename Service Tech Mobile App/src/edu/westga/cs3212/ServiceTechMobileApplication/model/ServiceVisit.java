package edu.westga.cs3212.ServiceTechMobileApplication.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Store and manage information for a single Service Visit
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class ServiceVisit {
	private String 			description;
	private String 			customerName;
	private String 			customerAddress;
	private LocalDateTime 	scheduledStartTime;
	private LocalDateTime 	actualStartTime;
	private LocalDateTime 	completionTime;
	private List<Task> 		tasks;
	
	/** create a new ServiceVisit that has not been started yet.
	 * 
	 * @precondition description != null &&
	 * 				 customerName != null &&
	 * 				 customerAddress != null &&
	 * 				 scheduledStartTime != null
	 * @postcondition getDescription() == description &&
	 * 				  getCustomerName() == customerName &&
	 * 				  getCustomerAddress() == customerAddress &&
	 * 				  getScheduledStartTime() == scheduledStartTime &&
	 * 				  getActualStartTime() == null &&
	 * 				  getCompletionTime() == null &&
	 * 				  getTasks().isEmpty()
	 * 
	 * @param description describes the purpose of the visit
	 * @param customerName name of the customer that requested the visit
	 * @param customerAddress address of the customer that requested the visit
	 * @param scheduledStartTime date/time the visit is scheduled to start
	 */
	public ServiceVisit(String description, String customerName, String customerAddress, LocalDateTime scheduledStartTime) {
		if(description == null) {
			throw new IllegalArgumentException("Must provide a valid description");
		}
		if(customerName == null) {
			throw new IllegalArgumentException("Must provide a valid name for the customer");
		}
		if(customerAddress == null) {
			throw new IllegalArgumentException("Must provide a valid address for the customer");
		}
		if(scheduledStartTime == null) {
			throw new IllegalArgumentException("Must provide a valid scheduled-start-time");
		}
		this.description = description;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.scheduledStartTime = scheduledStartTime;
		this.actualStartTime = null;
		this.completionTime = null;
		this.tasks = new ArrayList<Task>();
	}

	/** Get the description of the purpose for the service visit
	 * 
	 * @return the description of the purpose for the service visit
	 */
	public String getDescription() {
		return description;
	}

	/** Get the name of the customer for the service visit
	 * 
	 * @return the name of the customer for the service visit
	 */
	public String getCustomerName() {
		return customerName;
	}

	/** Get the address of the customer for the service visit
	 * 
	 * @return the address of the customer for the service visit
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/** Get the scheduled start time for the service visit
	 * 
	 * @return the scheduled start time for the service visit
	 */
	public LocalDateTime getScheduledStartTime() {
		return scheduledStartTime;
	}

	/** Get the actual start time for the service visit
	 * 
	 * @return the actual start time for the service visit
	 */
	public LocalDateTime getActualStartTime() {
		return actualStartTime;
	}

	/** Get the completion time for the service visit
	 * 
	 * @return the completion time for the service visit
	 */
	public LocalDateTime getCompletionTime() {
		return completionTime;
	}

	/** Get the list of tasks for the service visit
	 * 
	 * @return the list of tasks for the service visit
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/** Set the actual start time for the service visit
	 * 
	 * @precondition actualStartTime != null &&
	 * 				 getActualStartTime() == null
	 * @postcondition getActualStartTime() == actualStartTime
	 * 
	 * @param actualStartTime the time the service tech arrived onsite for a service visit
	 */
	public void setActualStartTime(LocalDateTime actualStartTime) {
		if(actualStartTime == null) {
			throw new IllegalArgumentException("Must provide valid start time");
		}
		if(this.actualStartTime != null) {
			throw new IllegalStateException("Actual start time has already been set for this visit");
		}
		this.actualStartTime = actualStartTime;
	}

	/** Set the completion time for the service visit
	 * 
	 * @precondition completionTime != null &&
	 * 				 getActualStartTime() != null &&
	 * 				 getCompletionTime() == null && 
	 * 				 getActualStartTime.isBefore(completionTime)
	 * @postcondition getCompletionTime() == completionTime
	 * 
	 * @param completionTime the time the service tech completed all tasks onsite for a service visit
	 */
	public void setCompletionTime(LocalDateTime completionTime) {
		if(completionTime == null) {
			throw new IllegalArgumentException("Must provide valid start time");
		}
		if(this.actualStartTime == null) {
			throw new IllegalStateException("Actual start time has not yet been set for this visit");
		}
		if(this.completionTime != null) {
			throw new IllegalStateException("Completion time has already been set for this visit");
		}
		if(!this.actualStartTime.isBefore(completionTime)) {
			throw new IllegalArgumentException("Completion time must be after the actual start time for a visit");
		}
		this.completionTime = completionTime;
	}
	
	/** Add a new Task for this service visit
	 * 
	 * @precondition task != null
	 * @postcondition getTasks().size() == getTasks().size()@pre + 1
	 * 
	 * @param task the task to be added to this service visit
	 */
	public void addTask(Task task) {
		if(task == null) {
			throw new IllegalArgumentException("Must provide a valid task");
		}
		this.tasks.add(task);
	}
	
	@Override
	public String toString() {
		return this.description + ", " + this.customerAddress + ", " + this.scheduledStartTime;
	}

}
