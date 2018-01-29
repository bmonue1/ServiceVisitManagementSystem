package edu.westga.cs3212.ServiceTechMobileApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * TODO App entry point.
 * 
 * @author CS3212
 * @version Spring2018
 */
public class Main extends Application {
	public static final String MAIN_WINDOW_TITLE = "Visit Management";
	public static final String MAIN_WINDOW = "view/MainWindow.fxml";
	public static final String VISIT_VIEW = "view/VisitView.fxml";

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
		loader.setLocation(Main.class.getResource(Main.MAIN_WINDOW));
		loader.load();
		Scene scene = new Scene(loader.getRoot());
		primaryStage.setTitle(Main.MAIN_WINDOW_TITLE);
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
