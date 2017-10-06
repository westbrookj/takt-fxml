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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class SettingsController implements Initializable
{

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
    public void handleLabelButton(ActionEvent event)
    {
        
    }
    
    @FXML
    public void handleSaveButton(ActionEvent event)
    {
        System.out.println(taktTextField.getText() + " " + partNumTextField.getText() + " " + unitGoalTextField.getText());
        if(Double.parseDouble(taktTextField.getText()) != TAKTFXMLModel.getTaktTime())
        {
            TAKTFXMLModel.setTaktTime(Double.parseDouble(taktTextField.getText()));
        }
        if(Integer.parseInt(partNumTextField.getText()) != TAKTFXMLModel.getPartNumber())
        {
            TAKTFXMLModel.setPartNumber(Integer.parseInt(partNumTextField.getText()));
        }
        if(Integer.parseInt(unitGoalTextField.getText()) != TAKTFXMLModel.getUnitGoal())
        {
            TAKTFXMLModel.setUnitGoal(Integer.parseInt(unitGoalTextField.getText()));
        }
        
        System.out.println(TAKTFXMLModel.getTaktTime() + " " + TAKTFXMLModel.getPartNumber() + " " + TAKTFXMLModel.getUnitGoal());
        
        TAKTFXMLModel.saveProperties();
    }
    
    @FXML
    public void handleCloseButton(ActionEvent event)
    {
        TAKTFXMLController.closeSettingsStage();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
}
