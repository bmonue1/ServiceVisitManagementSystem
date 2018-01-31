package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
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
    public void setVisitManager(VisitManager manager) {
    	this.manager = manager;
    	this.manager.getActiveTask().addListener(this);
    	this.stockMaterialList.setItems(this.manager.getAvailableMaterials());
    	this.statusList.setItems(this.manager.getTaskStatusOptions());
    	if(this.manager.getActiveTask().get() != null) {
    		this.updateDisplay(this.manager.getActiveTask().get());
    	}
    }

    private void updateDisplay(Task task) {
		this.description.setText(task.getDescription());
		this.materialList.getItems().clear();
		this.materialList.getItems().addAll(task.getMaterials());
	}

	@FXML
    void exit(ActionEvent event) throws IOException {
    	System.out.println("return to Visit - not implemented");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
		loader.load();
		((VisitView)loader.getController()).setVisitManager(this.manager);
		Scene visitView = new Scene(loader.getRoot());
		currentStage.setScene(visitView);
    }

    @FXML
    void statusSelected(ActionEvent event) {
    	System.out.println("status selected - not implemented");
    }

    @FXML
    void addMaterial(ActionEvent event) {
    	System.out.println("add material - not implemented");
    }

    @FXML
    void removeMaterial(MouseEvent event) {
    	System.out.println("remove material - not implemented");
    }

    @FXML
    void initialize() {
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert statusList != null : "fx:id=\"statusList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert materialList != null : "fx:id=\"materialList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert stockMaterialList != null : "fx:id=\"stockMaterialList\" was not injected: check your FXML file 'TaskView.fxml'.";

    }

	@Override
	public void changed(ObservableValue<? extends Task> activeTask, Task oldTask, Task newTask) {
		if(newTask != null) {
			this.updateDisplay(newTask);
		}
	}
}
