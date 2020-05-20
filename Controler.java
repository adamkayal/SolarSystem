import javafx.scene.canvas.GraphicsContext;

public class Controler {

    static Model model;

    public Controler(double width, double height) {
        model = new Model(width,height);
    }

    void draw(GraphicsContext context) {
        model.draw(context);
    }

    void update(double dt) {
        model.update(dt);
    }
}