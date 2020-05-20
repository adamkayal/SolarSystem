import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Model {

    private double width, height, fx, fy, g, r, dx, dy, maxDist;
    private Entity[] entities;

    public Model(double width, double height) {

        this.width = width;
        this.height = height;
        maxDist = (height/2);

        entities = new Entity[5];
        entities[0] = new Entity(new Image("images/sun.jpg"),50,50,width/2-25,height/2-25,0,0,1.9884e27);
        entities[1] = new Entity(new Image("images/mercury.jpg"),10,10,width/2-5+0.28*maxDist,height/2-5,0,0.448*maxDist,3.3011e20);
        entities[2] = new Entity(new Image("images/venus.jpg"),25,25,width/2-12.5+0.437*maxDist,height/2-12.5,0,0.4013*maxDist,4.8675e21);
        entities[3] = new Entity(new Image("images/earth.jpg"),25,25,width/2-12.5+0.61035*maxDist,height/2-12.5,0,0.3378*maxDist,5.97237e21);
        entities[4] = new Entity(new Image("images/mars.jpg"),15,15,width/2-7.5+maxDist,height/2-7.5,0,0.2534*maxDist,6.4171e20);
        //gravitational constant with appropriate unit change (px^3/(ton*month^2))
        g = 3.8943260448899256451377751858871e-29*maxDist*maxDist*maxDist;
    }

    public void calculateAcceleration(Entity[] entities) {
        for (int i = 0; i < entities.length - 1; i++) {
            for (int j = i+1; j < entities.length; j++) {
                    //distances
                    dx = entities[j].getX()-entities[i].getX();
                    dy = entities[j].getY()-entities[i].getY();

                    //radius
                    r = Math.sqrt(dx*dx+dy*dy);

                    //forces
                    fx = g*entities[i].getMass()*entities[j].getMass()*dx/(r*r*r);
                    fy = g*entities[i].getMass()*entities[j].getMass()*dy/(r*r*r);

                    //force contributions to each entity
                    entities[i].addForce(fx,fy);
                    entities[j].addForce(-fx,-fy); //third law of Newton
            }
        }
    }

    public void update(double dt) {
        calculateAcceleration(entities);

        for (Entity e : entities) {
            //updating the position of each entity using the physics motor
            e.update(dt);
            e.resetForce();
        }
    }

    public void draw(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.fillRect(0,0,this.width,this.height);

        for (Entity e : entities)
            e.draw(context); 
    }
}