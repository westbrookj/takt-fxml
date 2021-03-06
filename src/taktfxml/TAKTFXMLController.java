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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    public static Parent settingsRoot;
    
    @FXML 
    private GridPane root;
    @FXML
    private  Label titleLbl;
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
    
    public Label getTaktTimeLbl(){return taktTimeLbl;}
    
    public Label getUnitsLbl(){return unitsLbl;}
    
    public Timeline getTimeline(){return timeline;}
    
    private void updateTAKTTime(boolean bool)
    {
        unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
        dateTimeLbl.setText(TAKTFXMLModel.getDateString());
        titleLbl.setText(TAKTFXMLModel.getPartNumber());
        
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
    }
    
    private void updatePauseButton()
    {
        if(TAKTFXMLModel.getIsPaused() == true)
        {
            pauseButton.setText("Resume");
        }else
        {
            pauseButton.setText("Pause");
        }
    }
    
    @FXML
    public void handleStartButton(ActionEvent event)
    {
        timeline.play();
        
        if(TAKTFXMLModel.getIsPaused() != true && TAKTFXMLModel.getIsRunning() == false)
        {
            TAKTFXMLModel.setUnits(0);
            TAKTFXMLModel.setSecondsRemaining(TAKTFXMLModel.getTaktTime());
            updateTAKTTime(false);
            
            TAKTFXMLModel.updateOutputFile();
            TAKTFXMLModel.appendToLog(TAKTFXMLModel.getCurrentDate() + ",0,start," + TAKTFXMLModel.getCurrentTime() + "\n");
        }
        
        TAKTFXMLModel.setIsRunning(true);
        TAKTFXMLModel.setIsPaused(false);
        updatePauseButton();
    }
    
    @FXML
    public void handlePauseButton(ActionEvent event)
    {
        if(TAKTFXMLModel.getIsRunning() == true)
        {
            timeline.pause();
            TAKTFXMLModel.setIsRunning(false);
            TAKTFXMLModel.setIsPaused(true);
            updatePauseButton();
        }else
        {
            if(TAKTFXMLModel.getIsPaused() == true)
                TAKTFXMLModel.setIsPaused(false);
            
            timeline.play();
            TAKTFXMLModel.setIsRunning(true);
            updatePauseButton();
        }
    }
    
    @FXML
    public void handleEndButton(ActionEvent event)
    {
        if(timeline != null)
            timeline.stop();
        
        TAKTFXMLModel.setIsRunning(false);
        TAKTFXMLModel.setIsPaused(false);
        updatePauseButton();
        
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
                TAKTFXMLModel.incrementUnits();
                TAKTFXMLModel.appendToLog(TAKTFXMLModel.getCurrentDate()
                    + "," + TAKTFXMLModel.getUnits()
                    + "," + TAKTFXMLModel.getPartNumber()
                    + "," + TAKTFXMLModel.getCurrentTime() + "\n");
                unitsLbl.setText(String.format("%02d", TAKTFXMLModel.getUnits()));
                if(TAKTFXMLModel.getUnits() == TAKTFXMLModel.getUnitGoal())
                {
                    SendEmail email = new SendEmail();
                    Thread thread = new Thread(email);
                    thread.start();
                }
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
        settingsRoot = FXMLLoader.load(getClass().getResource("settingsView.fxml"));
        
        TAKTFXML.setRoot(settingsRoot);
    }
}
