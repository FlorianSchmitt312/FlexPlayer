package Application;
import java.awt.font.LayoutPath;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PlayerStart extends Application
{
	
	/*public static void  main (String args []) throws Exception 
	{
		launch(args);
	}*/
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FlexPlayer");

		//---- Login Grid -----------
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25,25,25,25));
		grid.setStyle("-fx-backround-color: black");
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		grid.add(scenetitle,0,0,2,1);
		
		Label userName = new Label("User Name:");
		grid.add(userName, 0,1);
		
		TextField userTextField = new TextField();
		grid.add(userTextField, 1,1);
		
		Label password = new Label("Password:");
		grid.add(password, 0,2);
		
		PasswordField passWordBox = new PasswordField();
		grid.add(passWordBox, 1, 2);
		
		Button button = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(button);
		grid.add(hbBtn, 1, 4);
		
		final Text actionTarget = new Text();
		grid.add(actionTarget, 1, 6);
		
		//---- Routing to next page ----
		
		BorderPane mainGui = new BorderPane();
		HBox center = new HBox(10);
		Text test = new Text("MainGui");
		center.getChildren().add(test);
		mainGui.setCenter(center);
		
		mainGui.setPadding(new Insets(25,25,25,25));
				
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				Scene mainScene = new Scene(mainGui, 1000,600);
				primaryStage.setScene(mainScene);
			}
		
		});
		
		
		Scene scene = new Scene(grid, 1000,600);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
}


