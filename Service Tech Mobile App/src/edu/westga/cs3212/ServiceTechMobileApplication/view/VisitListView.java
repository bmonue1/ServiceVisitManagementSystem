package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
import edu.westga.cs3212.ServiceTechMobileApplication.model.ServiceVisit;
import edu.westga.cs3212.ServiceTechMobileApplication.model.Task;
import edu.westga.cs3212.ServiceTechMobileApplication.view_model.VisitManager;
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
    	this.visitsList.setItems(this.manager.getVisits());
    	this.visitsList.selectionModelProperty().getValue().selectedItemProperty().addListener(this.manager);
    	
    	//.addListener(this.manager);
    }

    /** Switch to the VisitView if a visit has been selected
     * 
     * @precondition none
     * @postcondition if visit selected != null: switch to the VisitView
     * 
     * @param event mouse event that selected the new visit
     * @throws IOException
     */
    @FXML
    void visitSelected(MouseEvent event) throws IOException {
    	if(this.visitsList.getSelectionModel().getSelectedItem() != null) {
	    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
			loader.load();
			((VisitView)loader.getController()).setVisitManager(this.manager);
			Scene visitView = new Scene(loader.getRoot());
			currentStage.setScene(visitView);
    	}
    	
    }

    /**Switch the active list of ServiceVisits between upcoming and completed
     * 
     * @precondition none
     * @postcondition if prior active list was the upcoming visits: active list is the completed visits
     * 				  if prior active list was the completed visits: active list is the upcoming visits
     * 
     * @param event
     */
    @FXML
    void switchVisitsList(ActionEvent event) {
    	System.out.println("switch visits list - not implemented");
    }

    @FXML
    void initialize() {
        assert switchVisitsListButton != null : "fx:id=\"switchVisitsListButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert visitsListHeader != null : "fx:id=\"visitsListHeader\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert visitsList != null : "fx:id=\"visitsList\" was not injected: check your FXML file 'MainWindow.fxml'.";
    }

}
