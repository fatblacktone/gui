/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fxgame;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
 

/**
 *
 * @author Fatblack
 */
public class FXGame extends Application {

    private static Timeline gameLoop;
    private ImageView backgroundImage1,backgroundImage2;

    /**
     * Builds and sets the game loop ready to be started.
     */
    protected final void buildAndSetGameLoop() {
        
        Math.random();

        final Duration oneFrameAmt = Duration.millis(1000 / 60);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
                new EventHandler() {
                    @Override
                    public void handle(Event t) {
                        //TODO update sprites
                        updateSprites();
                        //TODO check collisions
                        
                        //remove dead things
                    }
                }); // oneFrame

        // sets the game world's game loop (Timeline)
        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build().play();
    }

    /**
     * The sets the current game loop for this game world.
     *
     * @param gameLoop Timeline object of an animation running indefinitely
     * representing the game loop.
     */
    protected static void setGameLoop(Timeline gameLoop) {
        FXGame.gameLoop = gameLoop;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void updateSprites(){
        backgroundImage1.setLayoutX(backgroundImage1.getLayoutX()-1);
        backgroundImage2.setLayoutX(backgroundImage2.getLayoutX()-1);
        
        if(backgroundImage1.getLayoutX()<=-2000){
            backgroundImage1.setLayoutX(backgroundImage1.getLayoutX()+2000);
        }
        if(backgroundImage2.getLayoutX()<=-2000){
            backgroundImage2.setLayoutX(backgroundImage2.getLayoutX()+2000);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        
        Image image = null;
        try {
            image = new Image(new File("background.png").toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        backgroundImage1 = new ImageView();
        backgroundImage2 = new ImageView();
        backgroundImage2.setImage(image);
        backgroundImage2.setLayoutX(2000);
        backgroundImage1.setImage(image);
        //buildAndSetGameLoop();

        //Group root = new Group();
        ///Scene scene = new Scene(root, 1920, 1080, Color.BLACK);
        //primaryStage.setScene(scene);
        //HBox box = new HBox();
        //box.getChildren().add(backgroundImage1);
        //box.getChildren().add(backgroundImage2);
        //root.getChildren().add(box);
        Group root = new Group();
          Scene scene = new Scene(root, 800, 600, Color.BLACK);
          primaryStage.setScene(scene);
          
          Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
          new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
          new Stop(0, Color.web("#f8bd55")),
          new Stop(0.14, Color.web("#c0fe56")),
          new Stop(0.28, Color.web("#5dfbc1")),
          new Stop(0.43, Color.web("#64c2f8")),
          new Stop(0.57, Color.web("#be4af7")),
          new Stop(0.71, Color.web("#ed5fc2")),
          new Stop(0.85, Color.web("#ef504c")),
          new Stop(1, Color.web("#f2660f")),}));
          colors.widthProperty().bind(scene.widthProperty());
          colors.heightProperty().bind(scene.heightProperty());
          //root.getChildren().add(colors);
          
          Group circles = new Group();
          for (int i = 0; i < 30; i++) {
          Circle circle = new Circle(150, Color.web("white", 0.05));
          circle.setStrokeType(StrokeType.OUTSIDE);
          circle.setStroke(Color.web("white", 0.16));
          circle.setStrokeWidth(4);
          circles.getChildren().add(circle);
          }
          //root.getChildren().add(circles);
          circles.setEffect(new BoxBlur(10, 10, 3));
          
          Group blendModeGroup =
          new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
          Color.BLACK), circles), colors);
          colors.setBlendMode(BlendMode.OVERLAY);
          root.getChildren().add(blendModeGroup);
          
          Timeline timeline = new Timeline();
          for (Node circle : circles.getChildren()) {
          timeline.getKeyFrames().addAll(
          new KeyFrame(Duration.ZERO, // set start position at 0
         new KeyValue(circle.translateXProperty(), Math.random() * 800),
         new KeyValue(circle.translateYProperty(), Math.random() * 600)),
          new KeyFrame(new Duration(40000), // set end position at 40s
          new KeyValue(circle.translateXProperty(), Math.random() * 800),
          new KeyValue(circle.translateYProperty(), Math.random() * 600)));
          }
         
          // play 40s of animation
          timeline.play();

        primaryStage.show();
    }
}
