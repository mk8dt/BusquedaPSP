package application;
import java.io.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

	public class Controlador {

		String nombre = "webs.txt";
		BufferedWriter bw = null;
		
		BufferedReader br = null;
		
	    @FXML
	    private AnchorPane apVentana;

	    @FXML
	    private Button btnExcel;

	    @FXML
	    private Button btnWord;

	    @FXML
	    private Button btnPower;

	    @FXML
	    private Button btnWeb;

	    @FXML
	    private TextField txtWeb;

	    @FXML
	    private TableView<StringProperty> tbHistorial;
	    
	    @FXML
	    private TableColumn<StringProperty,String> tbColumna;
	    
	    private Main main;
	    
	    
	    @FXML
	    void btnExcel(MouseEvent event) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "EXCEL");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    void btnWord(MouseEvent event) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "WINWORD");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    void btnPower(MouseEvent event) {
			
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "POWERPNT");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    void btnWeb(MouseEvent event) {
	    	
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ txtWeb.getText());
				
				escribirWeb();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    @FXML
	    private void initialize() {
	       
			 tbColumna.setCellValueFactory(
	                cellData -> cellData.getValue());

	        
			 tbHistorial.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
					try {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + (newValue.get()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});						
    	} 
		private void escribirWeb() {
			boolean esta = false;
	    	  try { 
				bw = new BufferedWriter(new FileWriter(nombre,true));
				for (int i = 0; i < main.getArrayWebs().size(); i++) {
					if (tbColumna.getCellData(i).equals(txtWeb.getText()) ) {
						esta = true;
					}
				}
				if (!esta) {
					bw.write(txtWeb.getText());
					bw.newLine();
					main.getArrayWebs().add(new SimpleStringProperty(txtWeb.getText()));
				}					

			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		public void inicializarTabla(Main main) {
			this.main = main;

	        
	        tbHistorial.setItems(main.getArrayWebs());
			
		}
		
	    
	}
	    

	

