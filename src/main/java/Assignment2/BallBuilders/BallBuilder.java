package Assignment2.BallBuilders;

import Assignment2.BallBehaviours.PocketBehaviour;

public class BallBuilder implements Builder {
    private Ball ball;

    public BallBuilder() {
        reset();
    }

    @Override
    public void reset() {
        this.ball = new Ball();
    }

    @Override
    public void setColour(String colour) {
        ball.setColour(colour);
    }

    @Override
    public void setPosition(double x, double y) {
        ball.setPosition(x, y);
    }

    @Override
    public void setVelocity(double x, double y) {
        ball.setVelocity(x, y);
    }

    @Override
    public void setAcceleration() {
        ball.setAcceleration(0, 0);
    }

    @Override
    public void setPocketBehaviour(PocketBehaviour pB) {
        ball.setPocketBehaviour(pB);
    }

    @Override
    public void setRadius(double scale) {
        ball.setRadius(scale);
    }


    @Override
    public void setMass(double mass) {
        ball.setMass(mass);
    }

    public Ball build() {
        Ball b = this.ball;
        reset();
        return b;
    }
}
