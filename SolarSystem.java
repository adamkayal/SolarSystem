import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;

public class SolarSystem extends Application {

    private double time = 0, width, height;
    Controler controler;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * configuer l'affichage de la fenetre du jeu
     */
    @Override
    public void start(Stage primaryStage) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        width = bounds.getWidth();
        height = bounds.getHeight(); 

        primaryStage.setTitle("Solar System"); 
        primaryStage.getIcons().add(new Image("images/earth.jpg"));
        primaryStage.setMaximized(true);
        primaryStage.sizeToScene();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        controler = new Controler(width,height);

        StackPane root = new StackPane();
        Scene scene = new Scene(root,width,height);

        Canvas canvas = new Canvas(width,height);
        root.getChildren().add(canvas);

        GraphicsContext context = canvas.getGraphicsContext2D();
        controler.draw(context);

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                if (lastTime == 0 || (now-lastTime)*1e-9 > 0.1) {
                    lastTime = now;
                    return;
                }

                double dt = (now - lastTime) * 1e-9;

                controler.update(dt);
                controler.draw(context);

                lastTime = now;
            }
        };

        timer.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}