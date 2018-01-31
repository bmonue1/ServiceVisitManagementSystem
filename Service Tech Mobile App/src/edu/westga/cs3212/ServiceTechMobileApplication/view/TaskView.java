package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import edu.westga.cs3212.ServiceTechMobileApplication.model.TaskStatus;
import edu.westga.cs3212.ServiceTechMobileApplication.view_model.VisitManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Codebehind for the TaskView view.
*
* @author CS3212
* @version Spring 2018
*/
public class TaskView implements ChangeListener<Task> {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button exitButton;
    @FXML private TextArea description;
    @FXML private ComboBox<TaskStatus> statusList;
    @FXML private ComboBox<String> stockMaterialList;
    @FXML private ListView<String> materialList;
    
    private VisitManager manager;
    
    /** Sets the VisitManager to be used by this view
     * 
     * @precondition manager != null
     * @postcondition none
     * 
     * @param manager VisitManager to be used by this view
     */
    public void setVisitManager(VisitManager visitManager) {
    	this.manager = visitManager;
    	this.manager.getActiveTask().addListener(this);
    	this.stockMaterialList.setItems(this.manager.getAvailableMaterials());
    	this.statusList.setItems(this.manager.getTaskStatusOptions());
    	if(this.manager.getActiveTask().get() != null) {
    		this.updateDisplay(this.manager.getActiveTask().get());
    	}
    	this.description.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
    	        manager.updateTaskDescription(newValue);
    	    }
    	});
    }

    private void updateDisplay(Task task) {
		this.description.setText(task.getDescription());
		this.materialList.getItems().clear();
		this.materialList.getItems().addAll(task.getMaterials());
		this.statusList.setValue(task.getStatus());
	}

    /** Return to the VisitView and add the task if it is temporary
     * 
     * @precondition none
     * @postcondition VisitView is loaded
     * 				  if active task is temporary and all details are valid:
     * 						task is added to active visit
     * 
     * @param event event that triggered this method
     * @throws IOException
     */
	@FXML
    void exit(ActionEvent event) throws IOException {
		if(this.manager.isTaskTemporary() && !this.addTask()) {
			return;
		}
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
		loader.load();
		((VisitView)loader.getController()).setVisitManager(this.manager);
		Scene visitView = new Scene(loader.getRoot());
		currentStage.setScene(visitView);
    }

    private boolean addTask() {
		String successMessage = this.manager.addTask(this.description.getText(), this.statusList.getValue(), this.materialList.getItems());
		if(!successMessage.equals(VisitManager.TASK_ADD_SUCCESS)) {
			String alertMessage = "The Task could not be added because," + System.lineSeparator() + 
								  successMessage + System.lineSeparator() + 
								  System.lineSeparator() + 
								  "Would you like to continue without adding the Task?";
			Alert alert = new Alert(AlertType.CONFIRMATION, alertMessage);
			Optional<ButtonType> result = alert.showAndWait();
			 if (result.isPresent() && result.get() == ButtonType.OK) {
			     return true;
			 }
			 else {
				 return false;
			 }
		}
		return true;
	}

    /** Update the active task to the selected status
     * 
     * @precondition none
     * @postcondition status of the active task has been updated appropriately
     * 
     * @param event event that triggered this method
     */
	@FXML
    void statusSelected(ActionEvent event) {
    	this.manager.updateTaskStatus(this.statusList.getValue());
    }

	/** Add the currently selected material to the active task
	 * 
	 * @precondition none
	 * @postcondition material is added to the active task
	 * 
	 * @param event event that triggered this method
	 */
    @FXML
    void addMaterial(ActionEvent event) {
    	this.manager.addMaterial(this.stockMaterialList.getValue());
    }

    /** Remove the selected material
     * 
     * @precondition none
     * @postcondition if no material is selected: 
     * 						none
     * 				  else:
     * 						selected material is removed
     * 
     * @param event event that triggered this method
     */
    @FXML
    void removeMaterial(MouseEvent event) {
    	String selectedMaterial = this.materialList.getSelectionModel().getSelectedItem();
		if(selectedMaterial != null) {
    		this.manager.removeMaterial(selectedMaterial);
    	}
    }

    @FXML
    void initialize() {
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert statusList != null : "fx:id=\"statusList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert materialList != null : "fx:id=\"materialList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert stockMaterialList != null : "fx:id=\"stockMaterialList\" was not injected: check your FXML file 'TaskView.fxml'.";

    }

    /** Update display when the active task is changed
     * 
     * @precondition none
     * @postcondition display is updated accordingly
     * 
     * @param activeTask the active task
     * @param oldTask previous state of the active task
     * @param newTask new state of the active task
     */
	@Override
	public void changed(ObservableValue<? extends Task> activeTask, Task oldTask, Task newTask) {
		if(newTask != null) {
			this.updateDisplay(newTask);
		}
	}
}
