package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import edu.westga.cs3212.ServiceTechMobileApplication.view_model.VisitManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Codebehind for the VisitView view.
*
* @author CS3212
* @version Spring 2018
*/
public class VisitView implements ChangeListener<ServiceVisit> {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private TextField completionTime;
    @FXML private Button updateStatusButton;
    @FXML private Button exitButton;
    @FXML private TextField address;
    @FXML private TextField actualStartTime;
    @FXML private TextField scheduledStartTime;
    @FXML private Button addTaskButton;
    @FXML private ListView<Task> taskList;
    @FXML private TextArea description;
    @FXML private TextField customer;
    
    private VisitManager manager;
    
    /** Sets the VisitManager to be used by this view
     * 
     * @precondition manager != null
     * @postcondition none
     * 
     * @param manager VisitManager to be used by this view
     */
    public void setVisitManager(VisitManager manager) {
    	this.manager = manager;
    	this.updateDisplay(this.manager.getActiveVisit().get());
    	this.manager.getActiveVisit().addListener(this);
    }
 
    /** Triggered when the state of the ActiveVisit has changed to update the displayed information and view state
     * 
     * @precondition none
     * @postcondition if newVisit != null: displayed information and view state are updated appropriately
     * 
     * @param activeVisit the observable value for the active visit
     * @param oldVisit the previous state of the active visit
     * @param newVisit the new state of the active visit
     */
	@Override
	public void changed(ObservableValue<? extends ServiceVisit> activeVisit, ServiceVisit oldVisit, ServiceVisit newVisit) {
		if(newVisit != null) {
			this.updateDisplay(newVisit);
		}
	}

	private void updateDisplay(ServiceVisit visit) {
		this.description.setText(visit.getDescription());
		this.customer.setText(visit.getCustomerName());
		this.address.setText(visit.getCustomerAddress());
		this.scheduledStartTime.setText(visit.getScheduledStartTime().toString());
		if(visit.getCompletionTime() != null) {
			this.actualStartTime.setText(visit.getActualStartTime().toString());
			this.completionTime.setText(visit.getCompletionTime().toString());
    		this.updateStatusButton.setText("");
    		this.updateStatusButton.setDisable(true);
		}
		else if(visit.getActualStartTime() != null) {
			this.actualStartTime.setText(visit.getActualStartTime().toString());
			this.updateStatusButton.setText("End");
		}
		this.taskList.getItems().clear();
		for (Task currentTask : visit.getTasks()) {
			this.taskList.getItems().add(currentTask);
		}
	}

	/** Open the Task view when a new task is selected
	 * 
	 * @precondition none
	 * @postcondition if selected task != null: view is switched to the TaskView
	 * 
	 * @param event details of the mouse event that selected a new task
	 * @throws IOException
	 */
    @FXML
    void taskSelected(MouseEvent event) throws IOException {
    	System.out.println("task selected - not implemented");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		this.openTaskView(currentStage);
    }

	private void openTaskView(Stage currentStage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.TASK_VIEW));
		loader.load();
		Scene visitView = new Scene(loader.getRoot());
		currentStage.setScene(visitView);
	}

    @FXML
    void updateStatus(ActionEvent event) {
    	this.manager.updateVisitStatus();
    }

    @FXML
    void addTask(ActionEvent event) throws IOException {
    	System.out.println("add task - not implemented");
    	//Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		//this.openTaskView(currentStage);
    }

    /** Return the visit list view
     * 
     * @precondition none
     * @postcondition view has switched to visit list view
     * 
     * @param event the event that triggered the view change
     * @throws IOException
     */
    @FXML
    void exit(ActionEvent event) throws IOException {
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_LIST_VIEW));
		loader.load();
		((VisitListView)loader.getController()).setVisitManager(this.manager);
		Scene visitView = new Scene(loader.getRoot());
		currentStage.setScene(visitView);
    }

    @FXML
    void initialize() {
        assert completionTime != null : "fx:id=\"completionTime\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert updateStatusButton != null : "fx:id=\"updateStatusButton\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert actualStartTime != null : "fx:id=\"actualStartTime\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert scheduledStartTime != null : "fx:id=\"scheduledStartTime\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert addTaskButton != null : "fx:id=\"addTaskButton\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert taskList != null : "fx:id=\"taskList\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'VisitView.fxml'.";
        assert customer != null : "fx:id=\"customer\" was not injected: check your FXML file 'VisitView.fxml'.";

    }
}
