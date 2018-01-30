package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
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
* @version Spring2018
*/
public class VisitView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField completionTime;

    @FXML
    private Button updateStatusButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField address;

    @FXML
    private TextField actualStartTime;

    @FXML
    private TextField scheduledStartTime;

    @FXML
    private Button addTaskButton;

    @FXML
    private ListView<?> taskList;

    @FXML
    private TextArea description;

    @FXML
    private TextField customer;

    @FXML
    private TextField status;

    @FXML
    void taskSelected(MouseEvent event) throws IOException {
    	System.out.println("task selected");
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
    	System.out.println("starting visit");
    }

    @FXML
    void addTask(ActionEvent event) throws IOException {
    	System.out.println("task selected");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		this.openTaskView(currentStage);
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
    	System.out.println("return to landing screen");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.MAIN_WINDOW));
		loader.load();
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
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'VisitView.fxml'.";

    }
}
