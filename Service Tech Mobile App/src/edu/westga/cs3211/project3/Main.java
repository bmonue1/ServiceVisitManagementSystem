package edu.westga.cs3211.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * TODO App entry point.
 * 
 * @author CS3212
 * @version Spring2018
 */
public class Main extends Application {
	public static final String MAIN_WINDOW_TITLE = "Visit Management";
	public static final String MAIN_WINDOW = "view/MainWindow.fxml";

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
		Parent parent = loader.getRoot();
		Scene scene = new Scene(parent);
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
