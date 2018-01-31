package edu.westga.cs3212.ServiceTechMobileApplication.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs3212.ServiceTechMobileApplication.Main;
import edu.westga.cs3212.ServiceTechMobileApplication.view_model.VisitManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Codebehind for the LoginView view
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class LoginView {
    @FXML private ResourceBundle resources;
    @FXML private URL 			 location;
    
    @FXML private Button 		 loginButton;
    
    @FXML private TextField 	 username;
    @FXML private TextField 	 password;
    
    private VisitManager manager;

    /** Login the current user and switch to VisitListView
     * 
     * @precondition none
     * @postcondition VisitListView is loaded && ServiceVisits for user are loaded
     * 
     * @param event event that triggered this method
     * @throws IOException
     */
    @FXML
    void login(ActionEvent event) throws IOException {
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
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'LoginView.fxml'.";

        this.manager = new VisitManager();
    }
}
