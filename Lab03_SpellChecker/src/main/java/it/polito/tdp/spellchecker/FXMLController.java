/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;



import it.polito.tdp.spellchecker.model.Model;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Model model;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> tendina;

    @FXML
    private TextArea txtUtente;

    @FXML
    private Button btnControllo;

    @FXML
    private TextArea txtSbagliate;

    @FXML
    private Label labelErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label labelTempo;

    @FXML
    void handleClear(ActionEvent event) {

    	txtUtente.clear();
    	txtSbagliate.clear();
    	labelTempo.setText(null);
    	labelErrori.setText(null);
    	
    }

    @FXML
    void handleControllo(ActionEvent event) {
    	long t1=System.currentTimeMillis();
    	String lingua=tendina.getValue();
    	String fraseUtente=txtUtente.getText();
    	
    	if(fraseUtente.isEmpty()) {
    		txtSbagliate.setText("Inserire almeno un parola");
    		return;
    	}
    	
    	if(lingua==null) {
    		txtSbagliate.setText("Per piacere, selezionare una lingua");
    		return;
    	}
    	this.model.caricaDizionario(lingua);
    	
    	//String result=this.model.controllaParole(fraseUtente);
    	//String result=this.model.controllaParoleLineareArray(fraseUtente);
    	String result=this.model.controlloParoleLineareLinked(fraseUtente);
    	//String result=this.model.controlloParolaDicotomicaArray(fraseUtente);
    //String result=this.model.controlloParolaDicotomicaLinked(fraseUtente);
    //	String result=this.model.controllaParoleContainsArray(fraseUtente);
    //	String result=this.model.controllaParoleContainsLinked(fraseUtente);
    	long t2=System.currentTimeMillis();
    	if(result==null) {
    		txtSbagliate.setText("Non sono stati rilevati errori");
    		labelErrori.setText("The text contains: 0 errori");
    	}
    	else {
    		txtSbagliate.setText(result);
    	
    	labelErrori.setText("The text contains: "+ result.split("\n").length +" errori");
    	}
    	labelTempo.setText("Spell check completed in "+Long.toString(t2-t1)+" ms");
    	
    }

    @FXML
    void initialize() {
        assert tendina != null : "fx:id=\"tendina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtUtente != null : "fx:id=\"txtUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnControllo != null : "fx:id=\"btnControllo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSbagliate != null : "fx:id=\"txtSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelTempo != null : "fx:id=\"labelTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    
    public void setModel(Model model) {
    	this.model=model;
    	String lingue[]= {"English", "Italian"};
    	tendina.getItems().addAll(lingue);
    }
    
}


