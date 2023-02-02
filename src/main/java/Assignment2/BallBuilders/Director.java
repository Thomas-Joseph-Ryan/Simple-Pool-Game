package Assignment2.BallBuilders;

import Assignment2.BallBehaviours.PocketBlue;
import Assignment2.BallBehaviours.PocketRed;
import Assignment2.BallBehaviours.PocketWhite;
import Assignment2.ConfigReaders.BallConfigReader;
import Assignment2.GameWindow;

import java.util.ArrayList;

public class Director {
    private Builder builder;

    private BallConfigReader ballConfigReader;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBallConfigReader(BallConfigReader ballConfigReader) {
        this.ballConfigReader = ballConfigReader;
    }

    public void primeBallBuilder(double ballScale, ArrayList<Ball> balls) {
        builder.setColour(ballConfigReader.colour);
        builder.setPosition(ballConfigReader.xPos, ballConfigReader.yPos);
        builder.setVelocity(ballConfigReader.xVel, ballConfigReader.yVel);
        builder.setAcceleration();
        builder.setMass(ballConfigReader.mass);
        builder.setRadius(ballScale);
        String colour = ballConfigReader.colour;
        if (colour.equalsIgnoreCase("white")) {
            builder.setPocketBehaviour(new PocketWhite());
        } else if (colour.equalsIgnoreCase("blue")) {
            builder.setPocketBehaviour(new PocketBlue(balls));
        } else if (colour.equalsIgnoreCase("red")) {
            builder.setPocketBehaviour(new PocketRed());
        }
    }
}
