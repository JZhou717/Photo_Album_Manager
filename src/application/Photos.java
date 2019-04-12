package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import view.PhotosController;


public class Photos extends Application {
	/**
	 * Bootup on start
	 */
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
	
	/**
	 * Serialize data on stop
	 * @throws IOException if something went wrong in serializing
	 */
	@Override
	public void stop() throws IOException{
		PhotosController.serialize();
	}
}