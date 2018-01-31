package edu.westga.cs3212.ServiceTechMobileApplication.view_model;

import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class ActiveVisitListener implements ChangeListener<ServiceVisit>{
	
	private VisitManager manager;
	
	public ActiveVisitListener(VisitManager manager) {
		this.manager = manager;
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
			this.manager.setActiveVisit(newVisit);
		}
	}

}
