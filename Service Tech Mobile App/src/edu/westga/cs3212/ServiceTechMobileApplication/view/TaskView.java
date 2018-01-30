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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/** Codebehind for the TaskView view.
*
* @author CS3212
* @version Spring2018
*/
public class TaskView {
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button exitButton;
    @FXML private ComboBox<?> statusList;
    @FXML private ComboBox<?> stockMaterialList;
    @FXML private ListView<?> materialList;

    @FXML
    void exit(ActionEvent event) throws IOException {
    	System.out.println("return to Visit - not implemented");
    	Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.VISIT_VIEW));
		loader.load();
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
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert statusList != null : "fx:id=\"statusList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert materialList != null : "fx:id=\"materialList\" was not injected: check your FXML file 'TaskView.fxml'.";
        assert stockMaterialList != null : "fx:id=\"stockMaterialList\" was not injected: check your FXML file 'TaskView.fxml'.";

    }
}
