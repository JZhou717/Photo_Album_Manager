package application;
	
import view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Photos extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Stage loginStage = primaryStage;
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			//loader.setLocation(getClass().getResource("/view/Login.fxml"));
			
			loader.setLocation(getClass().getResource("/view/Admin.fxml"));
			
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			
			//Getting the controller for the login screen
			/*
			LoginController logController = loader.getController();
			logController.start();
			*/
			
			AdminController admController = loader.getController();
			admController.start();
			
			loginStage.setScene(scene);
			loginStage.setTitle("Photo Albums Application");
			loginStage.setResizable(false);
			loginStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
