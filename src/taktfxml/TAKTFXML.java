/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktfxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jwgy5
 */
public class TAKTFXML extends Application
{
    private TAKTFXMLModel model;
    private double width;
    private double height;
    public static Stage rootStage;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        rootStage = stage;
        model = new TAKTFXMLModel();
        width = TAKTFXMLModel.getWidth();
        height = TAKTFXMLModel.getHeight();
        
        Parent root = FXMLLoader.load(getClass().getResource("TAKTFXMLView.fxml"));
        
        Scene scene = new Scene(root, width, height);
        
        stage.setScene(scene);
        stage.show();
        stage.toFront();
        stage.setFullScreen(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
