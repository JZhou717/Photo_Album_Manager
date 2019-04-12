//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Admin;
import model.User;

/**
 * The main controller for this application
 *  @author Jake
 *
 */
public class PhotosController {
	
	/**
	 * Holds all the scenes and their Controllers
	 */
	protected static Scene login_scene;
	protected static LoginController login_controller;
	protected static Scene admin_scene;
	protected static AdminController admin_controller;
	
	//MORE SCENES
	
	public static Stage stage;
	
	public static void start(Stage primaryStage) {
		try {
			
			//start up Admin
			Admin.start();
			
			//load login_scene
			FXMLLoader login_loader = new FXMLLoader();
			login_loader.setLocation(PhotosController.class.getResource("/view/Login.fxml"));
			Parent login_root = login_loader.load();
			login_scene = new Scene(login_root);
			login_controller = login_loader.getController();
			//load admin_scene
			FXMLLoader admin_loader = new FXMLLoader();
			admin_loader.setLocation(PhotosController.class.getResource("/view/Admin.fxml"));
			Parent admin_root = admin_loader.load();
			admin_scene = new Scene(admin_root);
			admin_controller = admin_loader.getController();
			//set the stage
			primaryStage.setTitle("Photo Albums Manager");
			primaryStage.setResizable(false);
			stage = primaryStage;
			//show login scene
			stage.setScene(login_scene);
			stage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void serialize() {
		
	}
	
}