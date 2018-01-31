package edu.westga.cs3212.ServiceTechMobileApplication.view_model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Maintains the ServiceVisits
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class VisitManager implements ChangeListener<ServiceVisit>{
	private ObservableList<ServiceVisit> visits;
	private ObjectProperty<ServiceVisit> activeVisit;
	private ObjectProperty<Task> activeTask;
	
	/** Create a new VisitManager loading in the appropriate service visits
	 * 
	 * @precondition none
	 * @postcondition getActiveVisit() != null &&
	 * 				  getActiveTask() != null &&
	 * 				  getVisits() != null
	 * 
	 */
	public VisitManager() {
		this.visits = FXCollections.observableList(new ArrayList<ServiceVisit>());
		this.activeVisit = new SimpleObjectProperty<ServiceVisit>();
		this.activeTask = new SimpleObjectProperty<Task>();
		this.loadServiceVisits();
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
	
	private void loadServiceVisits() {
		//TODO remove these default visits when actual load process is implemented
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
        
		this.visits.add(visit1);
		this.visits.add(visit2);
		this.visits.add(visit3);
		this.visits.add(visit4);
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
			throw new IllegalStateException("A service visit must be selected before updating the status");
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

	/** Updates the active visit if a new visit is selected
	 * 
	 * @precondition none
	 * @postcondition if newVisit != null: getActiveVisit().get() == newVisit
	 * 
	 * @param selectedVisit property for the selected visit
	 * @param oldVisit prior state of the selected visit
	 * @param newVisit new state of the selected visit
	 */
	@Override
	public void changed(ObservableValue<? extends ServiceVisit> selectedVisit, ServiceVisit oldVisit, ServiceVisit newVisit) {
		if(newVisit != null) {
			this.activeVisit.set(newVisit);
		}
	}

}
