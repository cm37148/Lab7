package lab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameFx extends Application{

	private Game console = new Game();
	private int numEnemies = -1;
	
	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene s = new Scene(new Pane(), 600, 600);
		s.setRoot(welcome(s));
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public Pane welcome(Scene s) {
		Text welcomeTxt = new Text("Welcome to the Dungeon!");
		Text goalTxt = new Text("\"Your goal is to escape the dungeon by getting to the stairs.\nThe stairs are located at (6,6).");
		ImageView welcomeImage = new ImageView("https://vignette3.wikia.nocookie.net/creepypasta/images/7/7a/Welcome_to_the_game.jpg/revision/latest?cb=20130824062648");
		welcomeImage.setFitHeight(300);
		welcomeImage.setFitWidth(300);
		Button nextBtn = new Button("NEXT");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				s.setRoot(setupPC(s));
			}
		});
		VBox welcomePane = new VBox(20);
		welcomePane.setAlignment(Pos.CENTER);
		welcomePane.getChildren().addAll(welcomeTxt, welcomeImage, goalTxt, nextBtn);
		return welcomePane;
	}
	public Pane setupPC(Scene s) {
		s.getWindow().setHeight(200);
		Text setupPCtxt = new Text("What is your character's name? (Input name then press enter): ");
		TextField setupPCtf = new TextField();
		Button nextBtn = new Button("NEXT");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				console.pc = new PlayableCharacter(setupPCtf.getText());
				s.setRoot(setupEnemies(s));
			}
		});
		HBox setupPCPane = new HBox(20);
		setupPCPane.getChildren().addAll(setupPCtxt, setupPCtf, nextBtn);
		return setupPCPane;
	}
	public Pane setupEnemies(Scene s) {
		Text setupECount = new Text("How many enemies are there in this dungeon? (Recommended: 3)");
		TextField setupECountTf = new TextField();
		Button nextBtn = new Button("NEXT");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				numEnemies = Integer.parseInt(setupECountTf.getText());
				s.setRoot(setupEnemies2(s));
			}
		});
		HBox setupPane2 = new HBox(20);
		setupPane2.getChildren().addAll(setupECount, setupECountTf, nextBtn);
		return setupPane2;
	}
	public Pane setupEnemies2(Scene s) {
		Text enemyNameTxt = new Text(String.format("What is Enemy %d's name?\n", console.enemies.size()));
		TextField enemyName = new TextField();
		Button nextBtn = new Button("NEXT");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				console.createEachEnemy(enemyName.getText());
				if(console.enemies.size() < numEnemies) {
					s.setRoot(setupEnemies2(s));
				}
			}
		});
		HBox setupPane3 = new HBox(20);
		setupPane3.getChildren().addAll(enemyNameTxt, enemyName, nextBtn);
		return setupPane3;
	}
	}

