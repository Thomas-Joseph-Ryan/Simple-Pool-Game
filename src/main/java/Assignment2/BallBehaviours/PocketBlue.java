package Assignment2.BallBehaviours;

import Assignment2.BallBuilders.Ball;

import java.util.ArrayList;

public class PocketBlue extends PocketBehaviour{

    private double ogX;
    private double ogY;
    private int lives = 1;
    private ArrayList<Ball> balls;

    public PocketBlue(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    @Override
    public void setBall(Ball ball) {
        this.ball = ball;
        this.ogX = ball.getPosition().getX();
        this.ogY = ball.getPosition().getY();
    }
    @Override
    public void think() {
        if (lives > 0) {
            if (checkSpawnCollision()) {
                ball.setInPlay(false);
                return;
            }
            ball.setPosition(ogX, ogY);
            ball.setVelocity(0, 0);
            lives--;
        } else {
            ball.setInPlay(false);
        }
//        System.out.println("Blue ball in pocket");
    }

    public boolean checkSpawnCollision() {
        for (Ball otherBall : balls) {
            if (otherBall.equals(this.ball)) {
                continue;
            }

            ArrayList<Boolean> checkResults = new ArrayList<Boolean>();

            double otherBallX = otherBall.getPosition().getX();
            double otherBallY = otherBall.getPosition().getY();
            double otherBallRadius = otherBall.getRadius();



            //Checking if points on the blue ball are in any of the other circles

            //Checking if center of og blue ball position is in another ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX, ogY));

            //Checking bottom of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX, ogY + ball.getRadius()));

            //Checking top of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX, ogY - ball.getRadius()));

            //Checking left of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX - ball.getRadius(), ogY));

            //Checking right of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX + ball.getRadius(), ogY));

            //Checking bottom left of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX - ball.getRadius(), ogY + ball.getRadius()));

            //Checking top left of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX - ball.getRadius(), ogY - ball.getRadius()));

            //Checking bottom right of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX + ball.getRadius(), ogY + ball.getRadius()));

            //Checking top right of blue ball
            checkResults.add(isPointInCircle(otherBallX, otherBallY,
                    otherBallRadius, ogX + ball.getRadius(), ogY - ball.getRadius()));


            for (Boolean bool : checkResults) {
                if (bool) {
                    return true;
                }
            }
        }
        return false;
    }


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
