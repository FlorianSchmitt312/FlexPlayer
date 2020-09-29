package application;

import java.awt.font.LayoutPath;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application
{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("FlexPlayer");
		//primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.getIcons().add(new Image("/img/appIcon.png"));

		//--------------------------------- Login Scene --------------------------------
			
		VBox startScreen = new VBox();
		startScreen.setAlignment(Pos.CENTER);
		startScreen.setOpacity(0.85);
		startScreen.setSpacing(15);
	
		Text scenetitle = new Text("Bereit für ein neues Hörerlebnis?");
		scenetitle.setFont(Font.font("Calibri", FontWeight.BLACK, 30));
		scenetitle.setFill(Color.BLACK);
					
		Button button = new Button("Aber sowas von absolut man!");
		button.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
				
		PauseTransition introPause = new PauseTransition(Duration.millis(1000));
		
		FadeTransition titleFade = new FadeTransition(Duration.millis(3500), scenetitle);
		titleFade.setFromValue(-5.0);
		titleFade.setToValue(1.0);
		
		PauseTransition pt = new PauseTransition(Duration.millis(200));
		
		FadeTransition titleBtnFade = new FadeTransition(Duration.millis(1000), button);
		titleBtnFade.setFromValue(0.0);
		titleBtnFade.setToValue(1.0);
				
		SequentialTransition seqTran = new SequentialTransition(introPause,titleFade,pt,titleBtnFade);
		seqTran.play();
		
		startScreen.getChildren().add(scenetitle);
		startScreen.getChildren().add(button);
				
		Scene scene = new Scene(startScreen, 1000,600);
		scene.getStylesheets().add(getClass().getResource("/css/startScreen.css").toString());
		primaryStage.setScene(scene);
	
		//----------------------------------- Main Scene --------------------------------------
		
		
		BorderPane mainGui = new BorderPane();
		HBox center = new HBox(10);
		Button back = new Button("Back");
		Text test = new Text("MainGui");
		center.getChildren().add(test);
		center.getChildren().add(back);
		mainGui.setCenter(center);
		mainGui.setPadding(new Insets(25,25,25,25));
		

		Scene mainScene = new Scene(mainGui, 1000,600);
		mainScene.getStylesheets().add(getClass().getResource("/css/mainTheme.css").toString());	
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				primaryStage.setScene(mainScene);
				
			}		
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent f) {			
				primaryStage.setScene(scene);				
			}		
		});
			
		primaryStage.show();
	}	
}


