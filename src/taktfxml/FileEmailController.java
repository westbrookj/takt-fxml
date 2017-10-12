/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class FileEmailController implements Initializable
{
    private ArrayList<String> emailList;
    
    @FXML
    private GridPane root;
    @FXML
    private Label titleLbl;
    @FXML
    private Label outputFileLbl;
    @FXML
    private Label fileLocationLbl;
    @FXML
    private TextField outputFileTextField;
    @FXML
    private TextField fileLocationTextField;
    @FXML
    private Label emailTitleLbl;
    @FXML
    private TextField email0TextField;
    @FXML
    private TextField email1TextField;
    @FXML
    private TextField email2TextField;
    @FXML
    private TextField email3TextField;
    @FXML
    private TextField email4TextField;
    @FXML
    private TextField email5TextField;
    @FXML
    private TextField email6TextField;
    @FXML
    private TextField email7TextField;
    @FXML
    private TextField email8TextField;
    @FXML
    private TextField email9TextField;
    @FXML
    private TextField email10TextField;
    @FXML
    private TextField email11TextField;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button saveButton;
    @FXML
    private Button closeButton;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void handleSaveButton(ActionEvent event)
    {
        emailList = TAKTFXMLModel.getEmailList();
        
        if(!outputFileTextField.getText().equals(TAKTFXMLModel.getOutputFileName()) && !outputFileTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setOutputFileName(outputFileTextField.getText());
        }
        if(!fileLocationTextField.getText().equals(TAKTFXMLModel.getOutputLocation()) && !fileLocationTextField.getText().isEmpty())
        {
            TAKTFXMLModel.setOutputLocation(fileLocationTextField.getText());
        }
        if(!email0TextField.getText().equals(emailList.get(0)) && !email0TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(0, email0TextField.getText());
        }
        if(!email1TextField.getText().equals(emailList.get(1)) && !email1TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(1, email1TextField.getText());
        }
        if(!email2TextField.getText().equals(emailList.get(2)) && !email2TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(2, email2TextField.getText());
        }
        if(!email3TextField.getText().equals(emailList.get(3)) && !email3TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(3, email3TextField.getText());
        }
        if(!email4TextField.getText().equals(emailList.get(4)) && !email4TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(4, email4TextField.getText());
        }
        if(!email5TextField.getText().equals(emailList.get(5)) && !email5TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(5, email5TextField.getText());
        }
        if(!email6TextField.getText().equals(emailList.get(6)) && !email6TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(6, email6TextField.getText());
        }
        if(!email7TextField.getText().equals(emailList.get(7)) && !email7TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(7, email7TextField.getText());
        }
        if(!email8TextField.getText().equals(emailList.get(8)) && !email8TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(8, email8TextField.getText());
        }
        if(!email9TextField.getText().equals(emailList.get(9)) && !email9TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(9, email9TextField.getText());
        }
        if(!email10TextField.getText().equals(emailList.get(10)) && !email10TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(10, email10TextField.getText());
        }
        if(!email11TextField.getText().equals(emailList.get(11)) && !email11TextField.getText().isEmpty())
        {
            TAKTFXMLModel.setEmailList(11, email11TextField.getText());
        }
        
        TAKTFXMLModel.saveProperties();
    }
    
    @FXML
    public void handleCloseButton(ActionEvent event)
    {
        TAKTFXML.setRoot(TAKTFXMLController.settingsRoot);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        emailList = TAKTFXMLModel.getEmailList();
        outputFileTextField.setText(TAKTFXMLModel.getOutputFileName());
        fileLocationTextField.setText(TAKTFXMLModel.getOutputLocation());
        email0TextField.setText(emailList.get(0));
        email1TextField.setText(emailList.get(1));
        email2TextField.setText(emailList.get(2));
        email3TextField.setText(emailList.get(3));
        email4TextField.setText(emailList.get(4));
        email5TextField.setText(emailList.get(5));
        email6TextField.setText(emailList.get(6));
        email7TextField.setText(emailList.get(7));
        email8TextField.setText(emailList.get(8));
        email9TextField.setText(emailList.get(9));
        email10TextField.setText(emailList.get(10));
        email11TextField.setText(emailList.get(11));
    }    
    
}
