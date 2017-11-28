package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


/**
 * @author mario
 * @version 1.0
 */

 
/**
 * The Class Main.
 */
public class Main extends Application {
	
	/** The root. */
	private AnchorPane root;
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			
			loader.setLocation(Main.class.getResource("Ventana.fxml"));
	        root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	         
			primaryStage.setScene(scene);
			primaryStage.setTitle("");
			primaryStage.show();
			
			Controlador controller = loader.getController();
			
			leerWebs();
			
            controller.inicializarTabla(this);
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** The array webs. */
	private ObservableList<StringProperty> arrayWebs = FXCollections.observableArrayList();
	
	/**
	 * Leer webs.
	 */
	public void leerWebs() {
		
		String webs = "webs.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(webs));
			
			StringProperty linea = null;
			while((linea = new SimpleStringProperty(br.readLine())).getValue() !=  null){
				arrayWebs.add(linea);
		      }
		} catch (FileNotFoundException e) {
		
		} catch (IOException e) {
			System.out.println("Error IO: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Gets the array webs.
	 *
	 * @return the array webs
	 */
	public ObservableList<StringProperty> getArrayWebs() {
		return arrayWebs;
	}
	
	/**
	 * Sets the array webs.
	 *
	 * @param arrayWebs the new array webs
	 */
	public void setarrayWebs(ObservableList<StringProperty> arrayWebs) {
		this.arrayWebs = arrayWebs;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	
	public static void main(String[] args) {
		launch(args);
		
	}

	
	
}
