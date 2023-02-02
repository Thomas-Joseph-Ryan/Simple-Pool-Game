package Assignment2.BallBuilders;


import Assignment2.BallBehaviours.PocketBehaviour;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.Objects;


public class Ball {

    private Paint colour;
    private String colourString;
    private Point2D position;
    private Point2D velocity;
    private Point2D acceleration;
    private double mass;
    private double radius = 12;
    private boolean inPlay = true;
    private PocketBehaviour pB;
    private final double MINRADIUS = 4;

    public void tick(double frictionCoefficient) {

        //Applying changes.

        //Creating friction force
        //Copying velocity
        Point2D friction = new Point2D(velocity.getX(), velocity.getY());
        friction = friction.normalize();
        friction = friction.multiply(-1 * frictionCoefficient * 0.25);
        applyForce(friction);

        velocity = velocity.add(acceleration);
        if (velocity.magnitude() < 1) {
            velocity = velocity.multiply(0);
            acceleration = acceleration.multiply(0);
        }
        position = position.add(velocity);
        acceleration = acceleration.multiply(0);


    }

    public void applyForce(Point2D force) {
//        System.out.println("Friction X: " + forceX + "Friction Y: " + forceY);
        acceleration = acceleration.add(force.multiply(1/mass));
//        System.out.println("Acc X: " + xAcc + "Acc Y: " + yAcc);
    }

    public void applyPocketBehaviour() {
        this.pB.think();
    }


    public void setColour(String colour) {
        this.colour = Paint.valueOf(colour.toUpperCase());
        this.colourString = colour;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public Point2D getAcceleration() {
        return acceleration;
    }

    public void setPosition(double x, double y) {
        position = new Point2D(x, y);
    }

    public void setVelocity(double x, double y) {
        velocity = new Point2D(x, y);
    }

    public void setAcceleration(double x, double y) {
        acceleration = new Point2D(x, y);
    }
    public void setPocketBehaviour(PocketBehaviour pB) {
        this.pB = pB;
        this.pB.setBall(this);
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    public void setRadius(double scale) {
        double rad = MINRADIUS + scale/100;
        if (rad < 4) {
            this.radius = 4;
        } else {
            this.radius = rad;
        }
    }

    public Paint getColour() {
        return colour;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() { return radius; }

    public String getColourString() {
        return colourString;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean bool) {
        this.inPlay = bool;
    }
}
