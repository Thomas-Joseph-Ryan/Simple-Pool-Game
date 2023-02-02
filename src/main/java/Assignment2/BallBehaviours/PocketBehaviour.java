package Assignment2.BallBehaviours;

import Assignment2.BallBuilders.Ball;

public abstract class PocketBehaviour {
    protected Ball ball;

    public abstract void think();

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
