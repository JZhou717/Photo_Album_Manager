package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.PhotosController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Photos extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			PhotosController.start(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}