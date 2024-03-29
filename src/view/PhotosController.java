//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Admin;

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
	protected static Scene open_album_scene;
	protected static OpenAlbumController open_album_controller;
	protected static Scene user_album_scene;
	protected static UserAlbumController user_album_controller;
	protected static Scene search_scene;
	protected static SearchController search_controller;
	
	/**
	 * The stage that we will be displaying everything on
	 */
	public static Stage stage;
	
	/**
	 * The name of the current user
	 */
	private transient static String current_user;
	private transient static String current_album;
	
	
	/**
	 * Instances of the models we'll need
	 */
	protected static Admin admin;
	
	/**
	 * The directory we are serialiazing to
	 */
	public static final String storeDir = "dat";
	
	/**
	 * Retrieves the serialized data of this application and sets up each page to be ready to use
	 * @param primaryStage
	 */
	public static void start(Stage primaryStage) {
		try {
			
			retrieve_serialized_data();
			
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
			//load open_album_scene
			FXMLLoader open_album_loader = new FXMLLoader();
			open_album_loader.setLocation(PhotosController.class.getResource("/view/OpenAlbum.fxml"));
			Parent open_album_root = open_album_loader.load();
			open_album_scene = new Scene(open_album_root);
			open_album_controller = open_album_loader.getController();
			//load user_album_scene
			FXMLLoader user_album_loader = new FXMLLoader();
			user_album_loader.setLocation(PhotosController.class.getResource("/view/UserAlbum.fxml"));
			Parent user_album_root = user_album_loader.load();
			user_album_scene = new Scene(user_album_root);
			user_album_controller = user_album_loader.getController();
			//load search_scene
			FXMLLoader search_loader = new FXMLLoader();
			search_loader.setLocation(PhotosController.class.getResource("/view/Search.fxml"));
			Parent search_root = search_loader.load();
			search_scene = new Scene(search_root);
			search_controller = search_loader.getController();
			
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
	
	/**
	 * Sets the current user
	 * @param name String name of the user to set to
	 */
	public static void set_user(String name) {
		if(admin.user_exists(name)) {
			current_user = name;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR, "Setting to nonexistent user", ButtonType.OK);
			alert.show();
			return;
		}
	}
	
	/**
	 * Saves the name of the current album we are viewing
	 * @param name of the album that we are viewing in open album screen
	 */
	public static void set_album(String name) {
		if(PhotosController.admin.getUserByName(current_user).album_exists(name)) {
			current_album = name;
		}
	
		else {
			Alert alert = new Alert(AlertType.ERROR, "Setting to nonexistent album", ButtonType.OK);
			alert.show();
			return;
		}
	}
	
	/**
	 * returns the current admin model that holds on to the list of users in this application
	 * @return Admin admin
	 */
	public static Admin get_admin() {
		return admin;
	}
	
	/**
	 * Retrieves the current user
	 * @return the name of the current user
	 */
	public static String get_user() {
		return current_user;
	}
	
	/**
	 * Retrieves the current album we are viewing
	 * @return the name of the current album
	 */
	public static String get_album() {
		return current_album;
	}
	
	/**
	 * Reads the serialized data back into the program
	 * @throws IOException if something is wrong with the file we are trying to access
	 */
	public static void retrieve_serialized_data() throws IOException, ClassNotFoundException{
		admin = (Admin) Admin.retrieve_serialized_data();
		if(admin == null) {
			Alert alert = new Alert(AlertType.ERROR, "Admin.retrieve_serialized_data did not execute properly.", ButtonType.OK);
			alert.show();
		}
	}
	
	/**
	 * Serializes all the models in the app
	 * @throws IOException if something goes wrong in the serialization
	 */
	public static void serialize() throws IOException {
		admin.serialize();
	}
	
}