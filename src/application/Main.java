package application;

import java.io.File;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	private File choosenFile;
	public  MediaView mediaView, choosenFileView;
	private BorderPane bottomPanel;
	private HBox volumeControl, mediaControl;
	private VBox timeControl, songLabel;
	private Label songTitle, speaker, searchAlert;
	private MenuBar menuBar;	
	private Menu fileMenu, propertiesMenu, helpMenu;
	private MenuItem search, windowSize, help;
	private Button skipBack, play, pause, stop, skipForward;
	private Image btnSkipBack, btnPlay,btnPause, btnStop, btnSkipForward, speakerImage;
	private ImageView viewBtnSkipBack,viewBtnPlay, viewBtnPause, viewBtnStop, viewBtnSkipForward, speakerImageView;
	private Slider volumeSlider, timeBar;
	private FileChooser fileChooser;
	private Media searchedFile;
	private MediaPlayer chosenFilePlayer;
	private String title; 

	//--- Setters and Getters
	public void setFile (File choosenFile) {
		this.choosenFile = choosenFile;
	}
	
	public void setSongTitle (String title) {
		this.songTitle.setText(title);
	}
	
	public void setSearchAlert (String title) {
		this.searchAlert.setText(title);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("FlexPlayer");
		primaryStage.getIcons().add(new Image("/img/appIcon.png"));

		//--------------------------------- Intro Scene --------------------------------
			
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
		//primaryStage.setScene(scene);
	
		//----------------------------------- Main Scene --------------------------------------
		

		//--- Initialize the Media Control Buttons and set the Button Images
				
		mainFrame = new BorderPane();
		bottomPanel = new BorderPane();
		mediaControl = new HBox();
		volumeControl = new HBox();
		timeControl = new VBox();
		songLabel = new VBox();
		
		songTitle = new Label("");
		speaker = new Label ();
		
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
		
		

		//--- Initialize the VolumSlider and add speaker icon which is binded to the slider position
		volumeSlider = new Slider();
		volumeSlider.setMaxWidth(100);
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {		
			@Override
			public void changed( ObservableValue<? extends Number> observableValue, 
	                   Number oldValue, 
	                   Number newValue) 
			{
				oldValue = volumeSlider.getMax();
				newValue = volumeSlider.getValue();
				
				
				if (volumeSlider.getValue() >= volumeSlider.getMax()/2) {
					speakerImage = new Image ("/img/speaker_100.png");
					speakerImageView = new ImageView(speakerImage);
					speaker.setGraphic(speakerImageView);
					speaker.setPadding(new Insets(0,10,0,0));
				}
				else if (volumeSlider.getValue() < volumeSlider.getMax()/2 && volumeSlider.getValue() > volumeSlider.getMax()/100) {
					speakerImage = new Image ("/img/speaker_50.png");
					speakerImageView = new ImageView(speakerImage);
					speaker.setGraphic(speakerImageView);
					speaker.setPadding(new Insets(0,10,0,0));
				}
				else if (volumeSlider.getValue() < volumeSlider.getMax()/100) {
					speakerImage = new Image ("/img/speaker_0.png");
					speakerImageView = new ImageView(speakerImage);
					speaker.setGraphic(speakerImageView);	
					speaker.setPadding(new Insets(0,10,0,0));
				}
			}
		});
		
		speakerImage = new Image ("/img/speaker_50.png");
		speakerImageView = new ImageView(speakerImage);
		speaker.setGraphic(speakerImageView);
		speaker.setPadding(new Insets(0,10,0,0));
		//volumeSlider.setPrefWidth(100);
		
		
			
		//--- TimeBar
		
		timeBar = new Slider();
		timeBar.setPadding(new Insets(20,20,20,20));
		
		
		
		//--- SongTitle
		songTitle.setTextFill(Color.WHITE);
		searchAlert = new Label ("Wählen sie über Dateien einen Titel aus");
		searchAlert.setFont(Font.font("Calibri", FontWeight.BLACK, 20));
	
		
		
		//--- FileChooser
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Ressource File");
		fileChooser.setInitialDirectory(new File("C:/Users/flosc/Music/Musik"));

		
						
		//--- Add the Media Control Buttons to the HBoxes which are positioned in the MainFrame BorderPane.
		//--- Add the the Speaker Label and the Volume Slider to the bottom right HBox's.
		//--- Add the Song Title to the left bottom VBox..
		//--- Add the Time Bar to the Bottom of the Scene.
		mediaControl.getChildren().addAll(skipBack,play,pause,stop,skipForward);
		mediaControl.setPadding(new Insets(20,20,20,20));
		mediaControl.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));
		mediaControl.setAlignment(Pos.CENTER);
		mediaControl.setMinWidth(600);
		
		volumeControl.getChildren().addAll(speaker, volumeSlider);
		volumeControl.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));
		volumeControl.setPadding(new Insets(45,20,45,0));
		volumeControl.setAlignment(Pos.CENTER_LEFT);		
		
		timeControl.getChildren().add(timeBar);
		
		songLabel.getChildren().add(songTitle);
		songLabel.setPadding(new Insets(0,0,0,20));
		
		songTitle.setPrefWidth(150);
		songTitle.setPadding(new Insets(40,20,40,20));
		songTitle.setBackground(new Background(new BackgroundFill(Color.web("#282828"), CornerRadii.EMPTY, Insets.EMPTY)));
			
		bottomPanel.setCenter(mediaControl);
		bottomPanel.setRight(volumeControl);
		bottomPanel.setLeft(songLabel);
		bottomPanel.setBottom(timeControl);
			
		BorderPane.setAlignment(volumeSlider, Pos.CENTER);
		BorderPane.setAlignment(mediaControl, Pos.CENTER);
		BorderPane.setAlignment(songLabel, Pos.CENTER_RIGHT);
		BorderPane.setAlignment(timeControl, Pos.CENTER);
		
		
		
		//--- Initialize the Menubar and its items
		menuBar = new MenuBar();
		
		fileMenu = new Menu("Dateien");
		propertiesMenu =  new Menu("Einstellungen");
		helpMenu = new Menu("Hilfe");
		
		search = new MenuItem("Suchen");
		windowSize = new MenuItem("Fenster sperren");
		help = new MenuItem("Weblink");
		
		fileMenu.getItems().addAll(search);
		propertiesMenu.getItems().add(windowSize);
		helpMenu.getItems().add(help);
			
		menuBar.getMenus().addAll(fileMenu, propertiesMenu, helpMenu);
		menuBar.setPadding(new Insets(5,5,5,5));
		

		
		//--- Add functionality to MenuItems
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				fileChooser.setTitle("Datei auswählen");
				fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audiodatei","*.wav","*.mp3"));
				
				choosenFile = fileChooser.showOpenDialog(primaryStage);
											
				if(choosenFile != null) {
					
					if(chosenFilePlayer != null) {
					   chosenFilePlayer.stop();
					   chosenFilePlayer.dispose();
				    }
					
					searchedFile = new Media((choosenFile).toURI().toString());
					chosenFilePlayer = new MediaPlayer(searchedFile);
					
					title = choosenFile.getName();
					setSongTitle(title);	
					
					setSearchAlert("");
														
					chosenFilePlayer.setOnReady(() -> {
																				
							play.setOnAction(new EventHandler<ActionEvent>() {
			
								@Override
								public void handle(ActionEvent e) {
									chosenFilePlayer.play();						
								}
							});
							
							pause.setOnAction(new EventHandler<ActionEvent>() {
			
								@Override
								public void handle(ActionEvent f) {
									chosenFilePlayer.pause();
								}
							});
							
							stop.setOnAction(new EventHandler<ActionEvent>() {
			
								@Override
								public void handle(ActionEvent g) {
									chosenFilePlayer.stop();
								}
							});
											
							volumeSlider.setValue(chosenFilePlayer.getVolume() *50);
			
							volumeSlider.valueProperty().addListener(new InvalidationListener() {		
								@Override
								public void invalidated(Observable arg0) {
									chosenFilePlayer.setVolume(volumeSlider.getValue() / 100);			
								}
							});
										
							System.out.println("File:" +searchedFile.getDuration().toSeconds());
							System.out.println("MP: " +chosenFilePlayer.getTotalDuration().toSeconds());
							
							timeBar.maxProperty().set(searchedFile.getDuration().toSeconds());
							
							System.out.println("Thumb: " +timeBar.getMax());
																
					
							/*InvalidationListener sliderChangeListener =( o-> {
							    Duration seekTo = Duration.seconds(timeBar.getValue());
							    chosenFilePlayer.seek(seekTo);
							});
							timeBar.valueProperty().addListener(sliderChangeListener);
							
							chosenFilePlayer.currentTimeProperty().addListener(l-> {
							    
								timeBar.valueProperty().removeListener(sliderChangeListener);
						    			   
							    timeBar.setValue(chosenFilePlayer.getCurrentTime().toSeconds());  
							    System.out.println(timeBar.getValue());
							   
							    timeBar.valueProperty().addListener(sliderChangeListener);
							});*/
							
							chosenFilePlayer.currentTimeProperty().addListener(new InvalidationListener() {
							    public void invalidated(Observable ov) {
							    	timeBar.setValue(chosenFilePlayer.getCurrentTime().toSeconds()); 
							    }
							});
							
							timeBar.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

								@Override
								public void handle(MouseEvent mouseEvent) {
									Duration seekTo = Duration.seconds(timeBar.getValue());
								    chosenFilePlayer.seek(seekTo);
								    System.out.println(seekTo.toSeconds());
								}
							});
										
					});				
				}
				
				else {
					setSongTitle("Titel auswählen!");			
				}
			}
		});
		
		

		windowSize.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				if(windowSize.getText().equals("Fenster sperren")) {
					primaryStage.setResizable(false);
					windowSize.setText("Fenster freigeben");
				}
				else if (windowSize.getText().equals("Fenster freigeben")) {
					primaryStage.setResizable(true);
					windowSize.setText("Fenster sperren");
				}			
			}
		});		
		
		
		
		//--- Set up the MainFrame with the different Panes. Set up the MainFrame and initialize it.
		mainFrame.setTop(menuBar);
		mainFrame.setBottom(bottomPanel);
		mainFrame.setCenter(searchAlert);
		
		Scene mainScene = new Scene(mainFrame, 1000,600);
		mainScene.getStylesheets().add(getClass().getResource("/css/mainTheme.css").toString());	
			
		
		
		//---------------------------------- Scene Switch ---------------------------------------
		
		
		//--- Switch from the Intro Scene to the MainFrame
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {	
				primaryStage.setScene(mainScene);			
			}		
		});
			
			
		//--- Show the PrimaryStage
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}	
}


