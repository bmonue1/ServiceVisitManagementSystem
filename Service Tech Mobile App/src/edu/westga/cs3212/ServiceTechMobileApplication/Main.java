package edu.westga.cs3212.ServiceTechMobileApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * TODO App entry point.
 * 
 * @author CS3212
 * @version Spring 2018
 */
public class Main extends Application {
	public static final String WINDOW_TITLE = "Visit Management";
	public static final String VISIT_LIST_VIEW = "view/VisitListView.fxml";
	public static final String VISIT_VIEW = "view/VisitView.fxml";
	public static final String TASK_VIEW = "view/TaskView.fxml";
	public static final String LOGIN_VIEW = "view/LoginView.fxml";

	/**
	 * JavaFX entry point.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param primaryStage
	 *            the main window hook
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.LOGIN_VIEW));
		loader.load();
		Scene scene = new Scene(loader.getRoot());
		primaryStage.setTitle(Main.WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Java entry point
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}
}
