package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import view.PhotosController;

/**
 * The application boots starting at this page. This application is a photo-album manager that supports multiple users where new users may be added through the admin system. Users can add photos to albums and search for photos with certain tags or within certain dates.
 * @author Tom, Jake
 *
 */
public class Photos extends Application {
	/**
	 * Bootup on start. Calls the main controller's {@link #PhotosController.start()} function
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