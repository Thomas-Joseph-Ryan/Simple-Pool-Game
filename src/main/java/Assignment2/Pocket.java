package Assignment2;

import Assignment2.BallBuilders.Ball;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Pocket {

    protected Paint colour;
    protected double xTopLeft;
    protected double yTopLeft;
    protected double xCentre;
    protected double yCentre;
    protected double diameter;
    protected double radius;
    protected GraphicsContext gc;

    public Pocket(double xTopLeft, double yTopLeft, GraphicsContext gc, double diameter) {

        colour = Paint.valueOf("BLACK");
        this.diameter = diameter;
        this.radius = diameter/2;
        this.xTopLeft = xTopLeft - radius;
        this.yTopLeft = yTopLeft - radius;
        this.xCentre = xTopLeft;
        this.yCentre = yTopLeft;
        this.gc = gc;

    }

    public void draw() {

        gc.setFill(colour);

        gc.fillOval(xTopLeft, yTopLeft, diameter, diameter);

    }

    public void tick(ArrayList<Ball> balls) {

        for (Ball ball : balls) {
            Point2D pos = ball.getPosition();
            if (isPointInCircle(xCentre, yCentre, this.radius, pos.getX(), pos.getY())) {
                ball.applyPocketBehaviour();
            }

        }
    }

    /*
    Method taken from https://stackoverflow.com/questions/481144/equation-for-testing-if-a-point-is-inside-a-circle
    Answer by William Morrison
     */

    boolean isInRectangle(double centerX, double centerY, double radius,
                          double x, double y)
    {
        return x >= centerX - radius && x <= centerX + radius &&
                y >= centerY - radius && y <= centerY + radius;
    }

    //test if coordinate (x, y) is within a radius from coordinate (center_x, center_y)
    public boolean isPointInCircle(double centerX, double centerY,
                                   double radius, double x, double y)
    {
        if(isInRectangle(centerX, centerY, radius, x, y))
        {
            double dx = centerX - x;
            double dy = centerY - y;
            dx *= dx;
            dy *= dy;
            double distanceSquared = dx + dy;
            double radiusSquared = radius * radius;
            return distanceSquared <= radiusSquared;
        }
        return false;
    }

}
