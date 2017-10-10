/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class SettingsController implements Initializable
{
    public static Stage fileEmailStage;

    @FXML
    private GridPane root;
    @FXML
    private Label titleLbl;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button fileEmailButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button closeButton;
    @FXML
    private VBox labelBox;
    @FXML
    private Label taktLbl;
    @FXML
    private Label partNumberLbl;
    @FXML
    private Label unitGoalLbl;
    @FXML
    private VBox textFieldBox;
    @FXML
    private TextField taktTextField;
    @FXML
    private TextField partNumTextField;
    @FXML
    private TextField unitGoalTextField;
    
    @FXML
    public void handleFileEmailButton(ActionEvent event) throws Exception
    {
        fileEmailStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fileEmailView.fxml"));
        
        Scene scene = new Scene(root, 1080, 720);
        
        fileEmailStage.setScene(scene);
        fileEmailStage.show();
    }
    
    @FXML
    public void handleSaveButton(ActionEvent event)
    {
        if(Double.parseDouble(taktTextField.getText()) != TAKTFXMLModel.getTaktTime() && !taktTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setTaktTime(Double.parseDouble(taktTextField.getText()) * 60);
        }
        if(Integer.parseInt(partNumTextField.getText()) != TAKTFXMLModel.getPartNumber() && !partNumTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setPartNumber(Integer.parseInt(partNumTextField.getText()));
        }
        if(Integer.parseInt(unitGoalTextField.getText()) != TAKTFXMLModel.getUnitGoal() && !unitGoalTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setUnitGoal(Integer.parseInt(unitGoalTextField.getText()));
        }
        
        TAKTFXMLModel.saveProperties();
    }
    
    @FXML
    public void handleCloseButton(ActionEvent event)
    {
        TAKTFXMLController.closeSettingsStage();
    }
    
    @FXML
    public void handleResetUnitsButton(ActionEvent event)
    {
        TAKTFXMLModel.setUnits(1);
        TAKTFXMLModel.saveProperties();
    }
    
    public static void closeFileEmailStage()
    {
        fileEmailStage.close();
        TAKTFXML.rootStage.toFront();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        taktTextField.setText(String.format("%f", TAKTFXMLModel.getTaktTime() / 60));
        partNumTextField.setText(String.format("%d", TAKTFXMLModel.getPartNumber()));
        unitGoalTextField.setText(String.format("%d", TAKTFXMLModel.getUnitGoal()));
    }    
    
}
