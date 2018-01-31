package edu.westga.cs3212.ServiceTechMobileApplication.view_model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import edu.westga.cs3212.ServiceTechMobileApplication.model.TaskStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Maintains the ServiceVisits
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class VisitManager {
	public static final String TASK_ADD_SUCCESS = "SUCCESS";
	
	private ObservableList<ServiceVisit> visits;
	private ObjectProperty<ServiceVisit> activeVisit;
	private ObjectProperty<Task> activeTask;
	private ObservableList<String> availableMaterials;
	private ObservableList<TaskStatus> taskStatusOptions;
	private ActiveVisitListener activeVisitListener;
	private ActiveTaskListener activeTaskListener;
	private boolean isTaskTemporary;
	
	/** Create a new VisitManager loading in the appropriate service visits and the available materials
	 * 
	 * @precondition none
	 * @postcondition getActiveVisit() != null &&
	 * 				  getActiveTask() != null &&
	 * 				  getVisits() != null &&
	 * 				  getAvailableMaterials() != null
	 * 
	 */
	public VisitManager() {
		this.isTaskTemporary = false;
		this.activeTaskListener = new ActiveTaskListener(this);
		this.activeVisitListener = new ActiveVisitListener(this);
		this.activeVisit = new SimpleObjectProperty<ServiceVisit>();
		this.activeTask = new SimpleObjectProperty<Task>();
		this.visits = FXCollections.observableList(this.loadServiceVisits());
		this.availableMaterials = FXCollections.observableList(this.loadAvailableMaterials());
		this.taskStatusOptions = FXCollections.observableList(this.loadTaskStatusOptions());
	}
	
	private List<TaskStatus> loadTaskStatusOptions() {
		List<TaskStatus> statusOptions = new ArrayList<TaskStatus>();
		statusOptions.add(TaskStatus.ASSIGNED);
		statusOptions.add(TaskStatus.COMPLETE);
		statusOptions.add(TaskStatus.INCOMPLETE);
		statusOptions.add(TaskStatus.UNNECESSARY);
		return statusOptions;
	}

	private List<ServiceVisit> loadServiceVisits() {
		//TODO remove these default visits when actual load process is implemented
		ArrayList<ServiceVisit> visits = new ArrayList<ServiceVisit>();
		
		LocalDateTime scheduleStartTime = LocalDateTime.now();
		ServiceVisit visit1 = new ServiceVisit("install service", "George", "3 Abbey Road, London NW8 9AY, England", scheduleStartTime);
        ServiceVisit visit2 = new ServiceVisit("install service", "Paul", "3 Abbey Road, London NW8 9AY, England", scheduleStartTime.plusDays(1));
        ServiceVisit visit3 = new ServiceVisit("install service", "John", "3 Abbey Road, London NW8 9AY, England", scheduleStartTime.plusDays(2));
        ServiceVisit visit4 = new ServiceVisit("install service", "Ringo", "3 Abbey Road, London NW8 9AY, England", scheduleStartTime.plusDays(3));

        Task task1 = new Task();
        task1.setDescription("setup 'the box'");
        task1.addMaterial("splitter");
        Task task2 = new Task();
        task2.setDescription("instruct customer on use of box");
		visit3.addTask(task1);
		visit3.addTask(task2);
		
        visit4.addTask(task1);
        
        visits.add(visit1);
		visits.add(visit2);
		visits.add(visit3);
		visits.add(visit4);
		
		return visits;
	}
	
	private List<String> loadAvailableMaterials() {
		//TODO remove the default materials when the proper load process is implemented
		ArrayList<String> availableMaterials = new ArrayList<String>();
		availableMaterials.add("splitter");
		availableMaterials.add("coax cable");
		return availableMaterials;
	}

	/** Return a change listener to update the active task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return change listener to update the active task
	 */
	public ActiveTaskListener getActiveTaskListener() {
		return this.activeTaskListener;
	}
	
	/** Return a change listener to update the active service visit
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return change listener to update the active service visit
	 */
	public ActiveVisitListener getActiveVisitListener() {
		return this.activeVisitListener;
	}
	
	/** Return the set of possible Task Statuses
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of possible Task Statuses
	 */
	public ObservableList<TaskStatus> getTaskStatusOptions() {
		return this.taskStatusOptions;
	}
	
	/** Return the set of available materials
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of available materials
	 */
	public ObservableList<String> getAvailableMaterials() {
		return this.availableMaterials;
	}

	/** Return the set of visits currently loaded
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of visits currently loaded
	 */
	public ObservableList<ServiceVisit> getVisits() {
		return this.visits;
	}
	
	/** Return the active visit
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the active visit
	 */
	public ObjectProperty<ServiceVisit> getActiveVisit() {
		return this.activeVisit;
	}	

	/** Return the active task
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the active task
	 */
	public ObjectProperty<Task> getActiveTask() {
		return this.activeTask;
	}
	
	/**Update the status of the activeVisit
	 * 
	 * @precondition getActiveVisit.get() != null
	 * @postcondition if getActiveVisit.get().getActualStartTime().pre == null: 
	 * 					getActiveVisit.get().getActualStartTime() == LocalDateTime.now()
	 * 				  if getActiveVisit.get().getCompletionTime().pre == null: 
	 * 					getActiveVisit.get().getCompletionTime() == LocalDateTime.now()
	 * 
	 */
	public void updateVisitStatus() {
		ServiceVisit visit = this.activeVisit.get();
		if(visit == null) {
			throw new IllegalStateException("Unable to update active visit status: no active visit has been selected");
		}
		this.activeVisit.set(null);
		if(visit != null) {
	    	if(visit.getActualStartTime() == null) {
	    		visit.setActualStartTime(LocalDateTime.now());
	    		this.activeVisit.set(visit);
	    	}
	    	else if(visit.getCompletionTime() == null) {
	    		visit.setCompletionTime(LocalDateTime.now());
	    		this.activeVisit.set(visit);
	    	}
		}
	}

	/** Update the status of the active task
	 * 
	 * @precondition none
	 * @postcondition if getActiveTask().get() == null:
	 * 						none
	 * 				  else:
	 * 						this.activeTask.get().getStatus() == status
	 * 
	 * @param status new status for the active task
	 */
	public void updateTaskStatus(TaskStatus status) {
		Task task = this.activeTask.get();
		if(task == null) {
			throw new IllegalStateException("Unable to update status of active task: No active task has been set");
		}
		this.activeTask.set(null);
		task.setStatus(status);
		this.activeTask.set(task);
	}


	/** Add the selected material to the active task
	 * 
	 * @precondition none
	 * @postcondition if getActiveTask().get() == null:
	 * 						none
	 * 				  else:
	 * 						this.activeTask.get().getMaterials().size()@pre == this.activeTask.get().getMaterials().size() + 1
	 * 
	 * @param material material to be added
	 */
	public void addMaterial(String material) {
		Task task = this.activeTask.get();
		if(task == null) {
			throw new IllegalStateException("Unable to update status of active task: No active task has been set");
		}
		this.activeTask.set(null);
		task.addMaterial(material);
		this.activeTask.set(task);
	}

	/** Remove the selected material from the active task
	 * 
	 * @precondition none
	 * @postcondition if getActiveTask().get() == null || !this.activeTask.get().getMaterials().contains(material):
	 * 						none
	 * 				  else:
	 * 						this.activeTask.get().getMaterials().size()@pre == this.activeTask.get().getMaterials().size() - 1
	 * 
	 * @param material material to be removed
	 */
	public void removeMaterial(String material) {
		Task task = this.activeTask.get();
		if(task == null) {
			throw new IllegalStateException("Unable to update status of active task: No active task has been set");
		}
		this.activeTask.set(null);
		task.removeMaterial(material);
		this.activeTask.set(task);
	}

	/** Add a new temporary task
	 * 
	 * @precondition none
	 * @postcondition getActiveTask.get() == new Task() &&
	 * 				  isTaskTemporary() == true
	 */
	public void addTemporaryTask() {
		this.isTaskTemporary = true;
		this.activeTask.set(new Task());
	}


	/** Set the active task
	 * 
	 * @precondition none
	 * @postcondition getActiveTask.get() == newTask &&
	 * 				  isTaskTemporary() == false
	 * 
	 * @param newTask new task to make active
	 */
	public void setActiveTask(Task newTask) {
		this.activeTask.set(newTask);
		this.isTaskTemporary = false;
		
	}

	/** Set the active visit
	 * 
	 * @precondition none
	 * @postcondition getActiveVisit.get() == newVisit
	 * 
	 * @param newVisit new visit to make active
	 */
	public void setActiveVisit(ServiceVisit newVisit) {
		this.activeVisit.set(newVisit);
	}
	
	/** Attempts to add a new task with the specified information
	 * 
	 * @precondition none
	 * @postcondition if getActiveTask().get() == null || getActiveVisit.get() == null :
	 * 						none
	 * 				  else:
	 * 						getActiveVisit.get().getTasks().contains(the new task)
	 * 
	 * @param description description of the new task
	 * @param status status of the new task
	 * @param materials list of materials for the new task
	 * 
	 * @return TASK_ADD_SUCCESS if task was added
	 * 		   message describing error if task was not added
	 */
	public String addTask(String description, TaskStatus status, List<String> materials) {
		String successMessage = "";
		ServiceVisit activeVisit = this.activeVisit.get();
		if (activeVisit == null) {
			successMessage = "No active Visit.";
		}
		else if (!this.isTaskTemporary) {
			successMessage = "Task is not temporary";
		}
		else {
			try {
				Task task = new Task();
				task.setDescription(description);
				task.setStatus(status);
				task.getMaterials().addAll(materials);
				activeVisit.addTask(task);
				successMessage = VisitManager.TASK_ADD_SUCCESS;
			}
			catch (IllegalArgumentException e) {
				successMessage = e.getMessage();
			}
		}
		return successMessage;
	}

	/** Check if the active task is temporary
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if the active task is temporary
	 * 		   false if the active task is not temporary
	 */
	public boolean isTaskTemporary() {
		return this.isTaskTemporary;
	}

	/** Update the description of the active task
	 * 
	 * @precondition none
	 * @postcondition if getActiveTask.get() != null: 
	 * 						getActiveTask.get().getDescription() == description
	 * 				  if getActiveTask.get() == null:
	 * 						none
	 * 
	 * @param description the new description for the active task
	 */
	public void updateTaskDescription(String description) {
		Task activeTask = this.activeTask.get();
		if(activeTask != null) {
			try {
				activeTask.setDescription(description);
				this.activeTask.set(null);
				this.activeTask.set(activeTask);
			}
			catch (IllegalArgumentException e) {
				//if the new description is not valid then keep the old one
			}
		}
	}

}
