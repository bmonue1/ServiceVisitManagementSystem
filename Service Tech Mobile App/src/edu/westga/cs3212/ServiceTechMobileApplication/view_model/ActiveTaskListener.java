package edu.westga.cs3212.ServiceTechMobileApplication.view_model;

import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class ActiveTaskListener implements ChangeListener<Task>{
	
private VisitManager manager;
	
	public ActiveTaskListener(VisitManager manager) {
		this.manager = manager;
	}
	


	/** Updates the active task if a new task is selected
	 * 
	 * @precondition none
	 * @postcondition if newTask != null: getActiveTask().get() == newVisit
	 * 
	 * @param selectedTask property for the selected visit
	 * @param oldTask prior state of the selected visit
	 * @param newTask new state of the selected visit
	 */
	@Override
	public void changed(ObservableValue<? extends Task> selectedTask, Task oldTask, Task newTask) {
		if(newTask != null) {
			this.manager.getActiveTask().set(newTask);
		}
	}

}
