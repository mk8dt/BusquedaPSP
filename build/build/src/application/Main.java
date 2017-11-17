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
public class Main extends Application {
	
	private AnchorPane root;
	
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
	private ObservableList<StringProperty> arrayWebs = FXCollections.observableArrayList();
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
	public ObservableList<StringProperty> getArrayWebs() {
		return arrayWebs;
	}
	public void setarrayWebs(ObservableList<StringProperty> arrayWebs) {
		this.arrayWebs = arrayWebs;
	}
	public static void main(String[] args) {
		launch(args);
		
	}

	
	
}
