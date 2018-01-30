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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Codebehind for the MainWindow view.
*
* @author CS3212
* @version Spring 2018
*/
public class MainWindow {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button switchVisitsListButton;
    @FXML private Label visitsListHeader;
    @FXML private ListView<?> visitsList;

    @FXML
    void visitSelected(MouseEvent event) throws IOException {
    	System.out.println("visit selected - not implemented");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
		loader.load();
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
    }
}
