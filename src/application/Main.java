package application;

import java.awt.Panel;
import java.io.File;
import java.nio.file.Paths;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{

	// ------ Global Variables -------
	
	// ---- Login Scene ----
	private VBox startScreen;
	private Text scenetitle;
	private Button button;
	private PauseTransition introPause;	
	private FadeTransition titleFade;
	private PauseTransition pt;
	private FadeTransition titleBtnFade;
	private SequentialTransition seqTran;
	private Scene scene;
	
	// ---- Main Frame ----
	private BorderPane mainFrame;
	private File f;	
	private Media pick;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	private BorderPane bottomPanel;
	private HBox mediaControl;
	private HBox volumeControl;	
	private Label placeholder;			
	private MenuBar menuBar;	
	private Menu fileMenu;
	private Menu propertiesMenu;
	private Menu helpMenu;
	private MenuItem search;
	private MenuItem windowSize;
	private MenuItem help;	
	private Button skipBack;
	private Button play;
	private Button pause;
	private Button stop;
	private Button skipForward;
	private Image btnSkipBack;
	private Image btnPlay;
	private Image btnPause;
	private Image btnStop;
	private Image btnSkipForward;			
	private ImageView viewBtnSkipBack;
	private ImageView viewBtnPlay;
	private ImageView viewBtnPause;
	private ImageView viewBtnStop;
	private ImageView viewBtnSkipForward;	
	private Slider volumeSlider;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("FlexPlayer");
		primaryStage.getIcons().add(new Image("/img/appIcon.png"));

		//--------------------------------- Login Scene --------------------------------
			
		startScreen = new VBox();
		startScreen.setAlignment(Pos.CENTER);
		startScreen.setOpacity(0.85);
		startScreen.setSpacing(15);
	
		scenetitle = new Text("Bereit für ein neues Hörerlebnis?");
		scenetitle.setFont(Font.font("Calibri", FontWeight.BLACK, 30));
		scenetitle.setFill(Color.BLACK);
					
		button = new Button("Aber sowas von absolut man!");
		button.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
						
		introPause = new PauseTransition(Duration.millis(1000));
		
		titleFade = new FadeTransition(Duration.millis(3500), scenetitle);
		titleFade.setFromValue(-5.0);
		titleFade.setToValue(1.0);
		
		pt = new PauseTransition(Duration.millis(200));
		
		titleBtnFade = new FadeTransition(Duration.millis(1000), button);
		titleBtnFade.setFromValue(0.0);
		titleBtnFade.setToValue(1.0);
				
		seqTran = new SequentialTransition(introPause,titleFade,pt,titleBtnFade);
		seqTran.play();
		
		startScreen.getChildren().add(scenetitle);
		startScreen.getChildren().add(button);
				
		scene = new Scene(startScreen, 1000,600);
		scene.getStylesheets().add(getClass().getResource("/css/startScreen.css").toString());
		primaryStage.setScene(scene);
	
		//----------------------------------- Main Scene --------------------------------------
		

		//--- Initialize the Media Control Buttons and set the Button Images
		f = new File("C:/Users/flosc/eclipse-workspace/FlexPlayerEnhanced/src/application/Sleepwalker.mp3");
		
		pick = new Media((f).toURI().toString());
		mediaPlayer = new MediaPlayer(pick);
		mediaView = new MediaView(mediaPlayer);
				
		mainFrame = new BorderPane();
		bottomPanel = new BorderPane();
		mediaControl = new HBox();
		volumeControl = new HBox();
		
		placeholder = new Label("Aktueller Titel");
		
		skipBack = new Button ();
		play = new Button ();
		pause = new Button ();
		stop = new Button ();
		skipForward = new Button ();
		
		btnSkipBack = new Image("/img/skipback.png");
		btnPlay = new Image("/img/play.png");
		btnPause = new Image("/img/pause.png");
		btnStop = new Image("/img/stop2.png");
		btnSkipForward = new Image("/img/skipforward.png");
				
		viewBtnSkipBack = new ImageView (btnSkipBack);
		viewBtnPlay = new ImageView (btnPlay);
		viewBtnPause = new ImageView (btnPause);
		viewBtnStop = new ImageView (btnStop);
		viewBtnSkipForward = new ImageView (btnSkipForward);
		
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
		
	
		
		//--- Add Functionality to the Media Control Buttons
		play.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				mediaPlayer.play();
			}
		});
		
		pause.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				mediaPlayer.pause();
			}
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				mediaPlayer.stop();
			}
		});
		
		
		
	
		//--- Initialize the VolumSlider and add Functionality
		volumeSlider = new Slider();
		
		volumeSlider.setValue(mediaPlayer.getVolume() *100);
		volumeSlider.valueProperty().addListener(new InvalidationListener() {		
			@Override
			public void invalidated(Observable arg0) {
				mediaPlayer.setVolume(volumeSlider.getValue() / 100);			
			}
		});
				
		//volumeSlider.setPrefWidth(100);
		volumeSlider.setMinWidth(50);
		volumeSlider.setPadding(new Insets(20,20,20,20));
		volumeControl.setStyle("-thumb-color: #ea5353;");
			
		
			
		//--- Add the Media Control Buttons to the HBoxes which are positioned in the MainFrame.
		mediaControl.getChildren().addAll(skipBack,play,pause,stop,skipForward);
		mediaControl.setPadding(new Insets(20,20,20,20));		
		mediaControl.setAlignment(Pos.CENTER);
		
		volumeControl.getChildren().add(volumeSlider);
		volumeControl.setAlignment(Pos.CENTER_LEFT);
		volumeControl.setPrefWidth(150);
		
		placeholder.setPrefWidth(150);
		placeholder.setPadding(new Insets(20,20,20,20));
		
		bottomPanel.setCenter(mediaControl);
		bottomPanel.setRight(volumeSlider);
		bottomPanel.setLeft(mediaView);
		BorderPane.setAlignment(volumeSlider, Pos.CENTER);
		BorderPane.setAlignment(mediaControl, Pos.CENTER);
		BorderPane.setAlignment(placeholder, Pos.CENTER);
		
		
		
		//--- Initialize the Menubar and its items
		menuBar = new MenuBar();
		
		fileMenu = new Menu("Dateien");
		propertiesMenu =  new Menu("Einstellungen");
		helpMenu = new Menu("Hilfe");
		
		search = new MenuItem("Suchen");
		windowSize = new MenuItem("Fenstergröße ändern");
		help = new MenuItem("Weblink");
		
		fileMenu.getItems().addAll(search);
		propertiesMenu.getItems().add(windowSize);
		helpMenu.getItems().add(help);
			
		menuBar.getMenus().addAll(fileMenu, propertiesMenu, helpMenu);
		menuBar.setPadding(new Insets(5,5,5,5));
		
		
		
		//--- Set up the MainFrame with the different Panes. Set up the MainFrame and initialize it.
		mainFrame.setTop(menuBar);
		mainFrame.setBottom(bottomPanel);
		
		Scene mainScene = new Scene(mainFrame, 1000,600);
		mainScene.getStylesheets().add(getClass().getResource("/css/mainTheme.css").toString());	
			
		
		
		//---------------------------------- Event Handlers ---------------------------------------
		
		
		//--- Switch from the Intro Scene to the MainFrame
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {				
				primaryStage.setScene(mainScene);
				
			}		
		});
			
			
		//--- Show the PrimaryStage
		primaryStage.show();
	}	
}


