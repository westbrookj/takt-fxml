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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class SettingsController implements Initializable
{
    private Parent fileEmailRoot;
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
    private Button resetButton;
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
        fileEmailRoot = FXMLLoader.load(getClass().getResource("fileEmailView.fxml"));
        
        TAKTFXML.setRoot(fileEmailRoot);
    }
    
    @FXML
    public void handleSaveButton(ActionEvent event) throws Exception
    {
        if(Double.parseDouble(taktTextField.getText()) != TAKTFXMLModel.getTaktTime() && !taktTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setTaktTime(Double.parseDouble(taktTextField.getText()) * 60);
        }
        if(!partNumTextField.getText().equals(TAKTFXMLModel.getPartNumber()) && !partNumTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setPartNumber(partNumTextField.getText());
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
        TAKTFXML.setRoot(TAKTFXML.getRoot());
    }
    
    @FXML
    public void handleResetButton(ActionEvent event) throws Exception
    {
        TAKTFXMLModel.setUnits(1);
        TAKTFXMLModel.setSecondsRemaining(TAKTFXMLModel.getTaktTime());
        TAKTFXMLModel.saveProperties();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SettingsController.class.getResource("TAKTFXMLView.fxml"));
        Parent root = loader.load();

        TAKTFXMLController controller = loader.getController();

        if(controller.getTimeline() != null)
            controller.getTimeline().stop();
        
        TAKTFXMLModel.setIsRunning(false);
        
        controller.getUnitsLbl().setText(String.format("%02d", TAKTFXMLModel.getUnits()));
        
        controller.getTaktTimeLbl().setTextFill(Color.WHITE);
        controller.getTaktTimeLbl().setText(String.format("+%02d", (int) TAKTFXMLModel.getSecondsRemaining() / 60) + ":" + String.format("%02d", (int) TAKTFXMLModel.getSecondsRemaining() % 60));
        controller.getTaktTimeLbl().setPadding(new Insets(-50, 0, -80, 0));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        taktTextField.setText(String.format("%f", TAKTFXMLModel.getTaktTime() / 60));
        partNumTextField.setText(TAKTFXMLModel.getPartNumber());
        unitGoalTextField.setText(String.format("%d", TAKTFXMLModel.getUnitGoal()));
    }
}
