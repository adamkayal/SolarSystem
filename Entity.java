import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Entity {

    private Image image;
    private double width, height;
    private double x, y;
    private double vx, vy;
    private double fx, fy;
    private double mass;

    public Entity(Image image, double width, double height, double x, double y, double vx, double vy, double mass) {
    	this.image = image;
    	this.width = width;
    	this.height = height;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
    }

    public double getMass() {
        return this.mass;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void addForce(double fx, double fy) {
        this.fx += fx;
        this.fy += fy;
    }

    public void resetForce() {
        this.fx = 0;
        this.fy = 0;
    }

    public void update(double dt) {
        this.vx += dt * this.fx / this.mass;
        this.vy += dt * this.fy / this.mass;
        this.x += dt * this.vx;
        this.y += dt * this.vy;
    }

    public void draw(GraphicsContext context) {
		context.drawImage(image, x, y, width, height);
    }

}
