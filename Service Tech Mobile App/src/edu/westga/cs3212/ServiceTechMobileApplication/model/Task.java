package edu.westga.cs3212.ServiceTechMobileApplication.model;

import java.util.ArrayList;
import java.util.List;

/** Store and manage information for a Task
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class Task {
	private String description;
	private List<String> materials;
	private TaskStatus status;
	
	/** Create an initial task with no description or materials and assigned status.
	 * 
	 * @precondition none
	 * @postcondition getDescription().isEmpty() &&
	 * 				  getMaterials().isEmpty() &&
	 * 				  getStatus() == TaskStatus.ASSIGNED
	 * 
	 */
	public Task() {
		this.description = "";
		this.materials = new ArrayList<String>();
		this.status = TaskStatus.ASSIGNED;
	}

	/** Get the description of the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the description of the task
	 */
	public String getDescription() {
		return description;
	}

	/** Get the list of materials for the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of materials for the task
	 */
	public List<String> getMaterials() {
		return materials;
	}

	/** Get the status for the task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the status for the task
	 */
	public TaskStatus getStatus() {
		return status;
	}

	/**Set the description for the task
	 * 
	 * @precondition description != null && !description.isEmpty()
	 * @postcondition getDescription() == description
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		if(description == null) {
			throw new IllegalArgumentException("Must provide a valid description");
		}
		if(description.isEmpty()) {
			throw new IllegalArgumentException("Must provide a non-empty description");
		}
		this.description = description;
	}

	/** Set the status for the task
	 * 
	 * @precondition status != null
	 * @postcondition getStatus() == status
	 * 
	 * @param status the status to set
	 */
	public void setStatus(TaskStatus status) {
		if(status == null) {
			throw new IllegalArgumentException("Must provide a valid status");
		}
		this.status = status;
	}
	
	/** Add a new material used for the task
	 * 
	 * @precondition material != null && !material.isEmpty()
	 * @postcondition getMaterials().size() == getMaterials().size()@pre + 1
	 * 
	 * @param material new material used for the task
	 */
	public void addMaterial(String material) {
		if(material == null) {
			throw new IllegalArgumentException("Must provide a valid material");
		}
		if(material.isEmpty()) {
			throw new IllegalArgumentException("Must provide a non-empty material designation");
		}
		this.materials.add(material);
	}
	
	
}
