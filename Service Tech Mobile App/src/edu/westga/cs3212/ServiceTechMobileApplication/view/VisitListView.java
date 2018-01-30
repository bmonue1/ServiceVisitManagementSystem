package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Codebehind for the MainWindow view.
*
* @author CS3212
* @version Spring 2018
*/
public class VisitListView {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button switchVisitsListButton;
    @FXML private Label visitsListHeader;
    @FXML private ListView<ServiceVisit> visitsList;

    @FXML
    void visitSelected(MouseEvent event) throws IOException {
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
		loader.load();
		((VisitView)loader.getController()).setServiceVisit(this.visitsList.getSelectionModel().getSelectedItem());
		Scene visitView = new Scene(loader.getRoot());
		currentStage.setScene(visitView);
    	
    }

    @FXML
    void switchVisitsList(ActionEvent event) {
    	System.out.println("switch visits list - not implemented");
    }

    @FXML
    void initialize() {
        assert switchVisitsListButton != null : "fx:id=\"switchVisitsListButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert visitsListHeader != null : "fx:id=\"visitsListHeader\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert visitsList != null : "fx:id=\"visitsList\" was not injected: check your FXML file 'MainWindow.fxml'.";

		this.loadServiceVisits();
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
        
		this.visitsList.getItems().add(visit1);
		this.visitsList.getItems().add(visit2);
		this.visitsList.getItems().add(visit3);
		this.visitsList.getItems().add(visit4);
	}
}
