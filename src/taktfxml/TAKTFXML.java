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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author jwgy5
 */
public class TAKTFXML extends Application
{
    private static Stage rootStage;
    private static Parent root;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        rootStage = stage;
        TAKTFXMLModel.importProperties();
        
        root = FXMLLoader.load(getClass().getResource("TAKTFXMLView.fxml"));
        
        Scene scene = new Scene(root, TAKTFXMLModel.getWidth(), TAKTFXMLModel.getHeight());
        
        stage.setScene(scene);
        stage.getIcons().add( new Image(TAKTFXML.class.getResourceAsStream( "icon.png" )));
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
    
    public static Parent getRoot(){return root;}
    public static void setRoot(Parent root){rootStage.getScene().setRoot(root);}
}
