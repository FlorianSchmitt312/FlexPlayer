package Application;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launch {
	
	public static void  main (String args []) throws Exception 
	{
		Application.launch(PlayerStart.class, args);
	}

}
