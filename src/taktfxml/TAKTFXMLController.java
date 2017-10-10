/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Josh
 */
public class TAKTFXMLController implements Initializable
{
    private Timeline timeline;
    private KeyFrame keyframe;
    public static Stage settingsStage;
    
    @FXML 
    private GridPane root;
    @FXML
    private Label titleLbl;
    @FXML
    private Label dateTimeLbl;
    @FXML
    private Label taktTitleLbl;
    @FXML
    private Label taktTimeLbl;
    @FXML
    private VBox taktBox;
    @FXML
    private Label unitsTitleLbl;
    @FXML
    private Label unitsLbl;
    @FXML
    private HBox buttonsBox;
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button endButton;
    @FXML
    private Button completeUnitButton;
    @FXML
    private Button settingsButton;
    @FXML
    private VBox unitsBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setup();
    }
    
    public void setup()
    {
        TAKTFXMLModel.setSecondsRemaining(TAKTFXMLModel.getTaktTime());
        root.setPrefWidth(TAKTFXMLModel.getWidth());
        root.setPrefHeight(TAKTFXMLModel.getHeight());
        buttonsBox.toFront();
        
        updateTAKTTime(false);

        unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
        
        keyframe = new KeyFrame(Duration.seconds(1),
            (ActionEvent actionEvent) -> 
            {
                updateTAKTTime(true);
            });
        
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    
    public void updateTAKTTime(boolean bool)
    {
        unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
        
        if(bool == true)
            TAKTFXMLModel.decrementSecondsRemaining();

        if(TAKTFXMLModel.getSecondsRemaining() >= 0)
        {
            taktTimeLbl.setTextFill(Color.WHITE);
            taktTimeLbl.setText(String.format("+%02d", (int) TAKTFXMLModel.getSecondsRemaining() / 60) + ":" + String.format("%02d", (int) TAKTFXMLModel.getSecondsRemaining() % 60));
            taktTimeLbl.setPadding(new Insets(-50, 0, -80, 0));
        }else
        {
            taktTimeLbl.setTextFill(Color.RED);
            taktTimeLbl.setText(String.format("-%02d",(int)java.lang.Math.abs(TAKTFXMLModel.getSecondsRemaining() / 60)) + ":" + String.format("%02d", (int)java.lang.Math.abs(TAKTFXMLModel.getSecondsRemaining() % 60)));
            taktTimeLbl.setPadding(new Insets(-50, 55, -80, 55));
        }
        
        dateTimeLbl.setText(TAKTFXMLModel.getDateString());
    }
    
    public static void closeSettingsStage()
    {
        settingsStage.close();
    }
    
    @FXML
    public void handleStartButton(ActionEvent event)
    {
        unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
        timeline.play();
        
        if(TAKTFXMLModel.getIsPaused() != true && TAKTFXMLModel.getIsRunning() == false)
        {
            TAKTFXMLModel.updateOutputFile();
            TAKTFXMLModel.appendToLog(TAKTFXMLModel.getCurrentDate() + ",0,start," + TAKTFXMLModel.getCurrentTime() + "\n");
        }
        
        TAKTFXMLModel.setIsRunning(true);
        TAKTFXMLModel.setIsPaused(false);
    }
    
    @FXML
    public void handlePauseButton(ActionEvent event)
    {
        if(TAKTFXMLModel.getIsRunning() == true)
        {
            timeline.pause();
            TAKTFXMLModel.setIsRunning(false);
            TAKTFXMLModel.setIsPaused(true);
            pauseButton.setText("Resume");
        }else
        {
            if(TAKTFXMLModel.getIsPaused() == true)
                TAKTFXMLModel.setIsPaused(false);
            
            timeline.play();
            TAKTFXMLModel.setIsRunning(true);
            pauseButton.setText("Pause");
        }
    }
    
    @FXML
    public void handleEndButton(ActionEvent event)
    {
        if(timeline != null)
            timeline.stop();
        
        TAKTFXMLModel.setIsRunning(false);
        
        TAKTFXMLModel.appendToLog(TAKTFXMLModel.getCurrentDate() + ",0,end," + TAKTFXMLModel.getCurrentTime() + "\n");
        
        TAKTFXMLModel.saveProperties();
        
        updateTAKTTime(false);
    }
    
    @FXML
    public void handleCompleteUnitButton(ActionEvent event)
    {
        if(TAKTFXMLModel.getIsRunning() == true)
        {
            if(TAKTFXMLModel.getSecondsRemaining() != TAKTFXMLModel.getTaktTime())
            {                
                TAKTFXMLModel.appendToLog(TAKTFXMLModel.getCurrentDate()
                    + "," + TAKTFXMLModel.getUnits()
                    + "," + TAKTFXMLModel.getPartNumber()
                    + "," + TAKTFXMLModel.getCurrentTime() + "\n");
                TAKTFXMLModel.incrementUnits();
                unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
            }
            timeline.stop();
            TAKTFXMLModel.setSecondsRemaining(TAKTFXMLModel.getTaktTime());
            updateTAKTTime(false);
            timeline.play();
        }
    }
    
    @FXML
    public void handleSettingsButton(ActionEvent event) throws Exception
    {
        settingsStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("settingsView.fxml"));
        
        Scene scene = new Scene(root, 1080, 720);
        
        settingsStage.setScene(scene);
        settingsStage.show();
    }
}
