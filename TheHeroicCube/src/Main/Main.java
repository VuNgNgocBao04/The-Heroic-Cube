package Main;

import Audio.Theme;
import Controller.ControlEnvironment;
import Model.Game;
import View.Window;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Core: create root pane and scene
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        
        // Background: load image resource (use a leading slash if in the resources root)
        Image backgroundImage = new Image(getClass().getResource("dia_nguc_15249936691011158642384.png").toExternalForm());
        BackgroundImage bImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,   // no horizontal repeat
                BackgroundRepeat.NO_REPEAT,   // no vertical repeat
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        root.setBackground(new Background(bImg));
        
        // Theme: load and play background music (using resource URL)
        Theme gameMusic = new Theme("FinalBoss.mp3");
        gameMusic.play();
        
        
        // Initialize model, view, and controller
        Game model = new Game();
        Window view = new Window(root, model);
        ControlEnvironment controller = new ControlEnvironment(model, view, scene);
        
        // Set up stage and show
        primaryStage.setTitle("The Heroic");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Start the game loop
        controller.startGameLoop();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
