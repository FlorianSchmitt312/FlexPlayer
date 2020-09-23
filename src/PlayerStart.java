import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PlayerStart extends Application
{
	
	//Globale Variablen
	
	Button play;
		
	public static void  main (String args []) throws Exception 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FlexPlayer");
		
		play = new Button("Play");

		StackPane layout = new StackPane();
		layout.getChildren().add(play);
		
		Scene scene = new Scene(layout,300,300);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}


