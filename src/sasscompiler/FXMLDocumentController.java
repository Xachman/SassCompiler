/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sasscompiler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import java.io.File;

/**
 *
 * @author ziron_000
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField textField;
    
    private TextField sassField;
    private TextField cssField;

    private Scene scene;
    final FileChooser fileChooser = new FileChooser();
            
    public FXMLDocumentController() {
        
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        SassWatcher sassWatcher = new SassWatcher("C:\\Users\\ziron_000\\Documents\\repositories\\windowcleaning\\sass\\sass.scss","C:\\Users\\ziron_000\\Documents\\repositories\\windowcleaning\\css\\sass.css");
        StartThread one;
        one = new StartThread(sassWatcher);
        one.start();
    }
    
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        scene = SassCompiler.getScene();
        System.out.println("Add Sass File Initiated");
        
        sassField = (TextField) scene.lookup("#sassFile");
        cssField = (TextField) scene.lookup("#compileFile");
        SassWatcher sassWatcher = new SassWatcher(sassField.getText(),cssField.getText());
        Thread one = new Thread() {
            public void run() {
                while(true) {
                // do stuff with input
                //System.out.println("No change was detected. ");
                try {
                    this.sleep(1000);                 //1000 milliseconds is one second.
                 } catch(InterruptedException ex) {
                    this.currentThread().interrupt();
                 }
                if (sassWatcher.checkFileDates()){
                  System.out.println("A change was detected. ");
                 // ((Label) scene.lookup("#label")).setText("A change was detected. ");
                  sassWatcher.compile();
                }
             }
            }  
        };
        one.start();
        ((Label) label.lookup("#label")).setText("Watching sass: "+sassField.getText()+" %nOutput: "+ cssField.getText());

    }
    
    @FXML
    private void handleBrowseFileAction(ActionEvent event) {
        scene = SassCompiler.getScene();
        Button btn = (Button) event.getSource();
        TextField field;
        if ( btn.getId().equals("searchSass") ) {
            field = (TextField) scene.lookup("#sassFile");
        } else {
            field = (TextField) scene.lookup("#compileFile");
        }
        
        File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                field.setText(file.getAbsolutePath()); 
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
