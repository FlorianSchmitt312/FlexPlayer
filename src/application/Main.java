package application;


import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
		
		
		BorderPane mainFrame = new BorderPane();
		HBox center = new HBox(10);
				
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("Dateien");
		Menu propertiesMenu =  new Menu("Einstellungen");
		Menu helpMenu = new Menu("Hilfe");
		
		MenuItem search = new MenuItem("Suchen");
		MenuItem windowSize = new MenuItem("Fenstergröße ändern");
		MenuItem help = new MenuItem("Weblink");
		
		Button skipBack = new Button ();
		Button play = new Button ();
		Button pause = new Button ();
		Button stop = new Button ();
		Button skipForward = new Button ();
		
		Image btnSkipBack = new Image("/img/skipback.png");
		Image btnPlay = new Image("/img/play.png");
		Image btnPause = new Image("/img/pause.png");
		Image btnStop = new Image("/img/stop2.png");
		Image btnSkipForward = new Image("/img/skipforward.png");
				
		ImageView viewBtnSkipBack = new ImageView (btnSkipBack);
		ImageView viewBtnPlay = new ImageView (btnPlay);
		ImageView viewBtnPause = new ImageView (btnPause);
		ImageView viewBtnStop = new ImageView (btnStop);
		ImageView viewBtnSkipForward = new ImageView (btnSkipForward);
		
		skipBack.setGraphic(viewBtnSkipBack);
		skipBack.setShape(new Circle());
		play.setGraphic(viewBtnPlay);
		play.setShape(new Circle());
		pause.setGraphic(viewBtnPause);
		pause.setShape(new Circle());
		stop.setGraphic(viewBtnStop);
		stop.setShape(new Circle());
		skipForward.setGraphic(viewBtnSkipForward);
		skipForward.setShape(new Circle());
					
		center.getChildren().addAll(skipBack,play,pause,stop,skipForward);
		center.setPadding(new Insets(20,20,20,20));
		
		center.setAlignment(Pos.CENTER);
		
		fileMenu.getItems().addAll(search);
		propertiesMenu.getItems().add(windowSize);
		helpMenu.getItems().add(help);
			
		menuBar.getMenus().addAll(fileMenu, propertiesMenu, helpMenu);
		menuBar.setPadding(new Insets(5,5,5,5));
		
		mainFrame.setTop(menuBar);
		mainFrame.setBottom(center);
		//mainGui.setPadding(new Insets(25,25,25,25));
		
		Scene mainScene = new Scene(mainFrame, 1000,600);
		mainScene.getStylesheets().add(getClass().getResource("/css/mainTheme.css").toString());	
		
		
		
		//---------------------------------- Event Handlers ---------------------------------------
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				primaryStage.setScene(mainScene);
				
			}		
		});
		
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				System.out.println("Play");
				
			}		
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				System.out.println("Stop");
				
			}		
		});
		
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				System.out.println("Pause");
				
			}		
		});
		
		skipBack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				System.out.println("Zurück");
				
			}		
		});
		
		skipForward.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				System.out.println("Vorwärts");
				
			}		
		});
				
		primaryStage.show();
	}	
}


