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

	
/**
	 * The Class Controlador.
	 */
	public class Controlador {

		/** The nombre. */
		String nombre = "webs.txt";
		
		/** The bw. */
		BufferedWriter bw = null;
		
		/** The br. */
		BufferedReader br = null;
		
	    /** The ap ventana. */
    	@FXML
	    private AnchorPane apVentana;

	    /** The btn excel. */
    	@FXML
	    private Button btnExcel;

	    /** The btn word. */
    	@FXML
	    private Button btnWord;

	    /** The btn power. */
    	@FXML
	    private Button btnPower;

	    /** The btn web. */
    	@FXML
	    private Button btnWeb;

	    /** The txt web. */
    	@FXML
	    private TextField txtWeb;

	    /** The tb historial. */
    	@FXML
	    private TableView<StringProperty> tbHistorial;
	    
	    /** The tb columna. */
    	@FXML
	    private TableColumn<StringProperty,String> tbColumna;
	    
	    /** The main. */
    	private Main main;
	    
	    
	    /**
    	 * Boton excel.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void btnExcel(MouseEvent event) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "EXCEL");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Boton word.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void btnWord(MouseEvent event) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "WINWORD");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Boton power.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void btnPower(MouseEvent event) {
			
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ "POWERPNT");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Boton web.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void btnWeb(MouseEvent event) {
	    	
			try {
				Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+ txtWeb.getText());
				
				escribirWeb();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Initialize.
    	 */
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
		
		/**
		 * Escribir web.
		 */
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
		
		/**
		 * Inicializar tabla.
		 *
		 * @param main the main
		 */
		public void inicializarTabla(Main main) {
			this.main = main;

	        
	        tbHistorial.setItems(main.getArrayWebs());
			
		}
		
	    
	}
	    

	

