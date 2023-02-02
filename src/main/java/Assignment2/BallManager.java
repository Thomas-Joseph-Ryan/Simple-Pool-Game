package Assignment2;

import Assignment2.BallBuilders.Ball;
import Assignment2.BallBuilders.BallBuilder;
import Assignment2.BallBuilders.Builder;
import Assignment2.BallBuilders.Director;
import Assignment2.ConfigReaders.BallConfigReader;
import Assignment2.ConfigReaders.BallConfigReaderFactory;
import Assignment2.ConfigReaders.ConfigReader;
import Assignment2.ConfigReaders.ConfigReaderFactory;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.util.ArrayList;

public class BallManager {

    private ArrayList<Ball> ballArray = new ArrayList<>();
    private GraphicsContext gc;
    private Scene scene;
    private Ball whiteBall;
    private double friction;
    private double width;
    private double height;
    private HitBallController hitBallController;
    private Pool gameManager;

    public BallManager(double friction, double width, double height, Pool gameManager) {
        ConfigReaderFactory ballReaderFactory = new BallConfigReaderFactory();
        ConfigReader ballReader = ballReaderFactory.useReader("src/main/resources/config.json");

        Builder ballBuilder = new BallBuilder();
        Director director = new Director(ballBuilder);
        director.setBallConfigReader((BallConfigReader) ballReader);

        double ballScale = width + height;

        int i = 0;

        while (ballReader.getInfo()) {
            director.primeBallBuilder(ballScale, ballArray);
            ballArray.add(ballBuilder.build());
            if (ballArray.get(i).getColourString().equalsIgnoreCase("white")) {
                whiteBall = ballArray.get(i);
            }
            i++;
        }
        this.friction = friction;
        this.width = width;
        this.height = height;
        this.gameManager = gameManager;
    }

    public void tick() {
        int counter = 0;

        for (Ball ball : ballArray) {
            if (!ball.isInPlay()) {
                if (ball.equals(whiteBall)) {
                    gameManager.setWhiteBallInPocket(true);
                }
                counter ++;
                continue;
            }
            ball.tick(friction);
            Point2D pos = ball.getPosition();
            Point2D vel = ball.getVelocity();

            //Check for table boundaries
            if (pos.getX() + ball.getRadius() > width) {
                ball.setPosition(width - ball.getRadius(), pos.getY());
                ball.setVelocity(vel.getX() * -1, vel.getY());

            }
            if (pos.getX() - ball.getRadius() < 0) {
                ball.setPosition(0 + ball.getRadius(), pos.getY());
                ball.setVelocity(vel.getX() * -1, vel.getY());

            }
            if (pos.getY() + ball.getRadius() > height) {
                ball.setPosition(pos.getX(), height - ball.getRadius());
                ball.setVelocity(vel.getX(), vel.getY() * -1);

            }
            if (pos.getY() - ball.getRadius() < 0) {
                ball.setPosition(pos.getX(), 0 + ball.getRadius());
                ball.setVelocity(vel.getX(), vel.getY() * -1);
            }

            for (Ball ballB: ballArray) {
                if (checkCollision(ball, ballB)) {
                    Pair<Point2D, Point2D> pair = calculateCollision(ball.getPosition(),
                            ball.getVelocity(), ball.getMass(),
                            ballB.getPosition(), ballB.getVelocity(),
                            ballB.getMass());
//                    System.out.println(pair.getKey().toString());
//                    Point2D ballA = ball.getVelocity().add(pair.getKey());
                    ball.setVelocity(pair.getKey().getX(), pair.getKey().getY());
//                    Point2D ballBv = ballB.getVelocity().add(pair.getValue());
                    ballB.setVelocity(pair.getValue().getX(), pair.getValue().getY());
                }
            }
        }

        if (counter == ballArray.size() - 1 && whiteBall.isInPlay()) {
            gameManager.setGameRunning(false);
        }

    }

    public void draw() {

        for (Ball ball: this.ballArray) {
            if (!ball.isInPlay()) {
                continue;
            }
            Point2D pos = ball.getPosition();
            gc.setFill(ball.getColour());
            gc.fillOval(pos.getX() - ball.getRadius(),
                    pos.getY() - ball.getRadius(),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2);
        }
        hitBallController.draw();
    }

    private boolean checkCollision(Ball ballA, Ball ballB) {
        if (ballA == ballB) {
            return false;
        }

        Point2D posA = ballA.getPosition();
        Point2D posB = ballB.getPosition();

        return Math.abs(posA.getX() - posB.getX()) < ballA.getRadius() + ballB.getRadius() &&
                Math.abs(posA.getY() - posB.getY()) < ballA.getRadius() + ballB.getRadius();
    }

    //Using collision function from assignment spec
    public static Pair<Point2D, Point2D> calculateCollision(Point2D positionA, Point2D velocityA, double massA, Point2D positionB, Point2D velocityB, double massB) {

        // Find the angle of the collision - basically where is ball B relative to ball A. We aren't concerned with
        // distance here, so we reduce it to unit (1) size with normalize() - this allows for arbitrary radii
        Point2D collisionVector = positionA.subtract(positionB);
        collisionVector = collisionVector.normalize();

        // Here we determine how 'direct' or 'glancing' the collision was for each ball
        double vA = collisionVector.dotProduct(velocityA);
        double vB = collisionVector.dotProduct(velocityB);

        // If you don't detect the collision at just the right time, balls might collide again before they leave
        // each others' collision detection area, and bounce twice.
        // This stops these secondary collisions by detecting
        // whether a ball has already begun moving away from its pair, and returns the original velocities
        if (vB <= 0 && vA >= 0) {
            return new Pair<>(velocityA, velocityB);
        }

        // This is the optimisation function described in the gamasutra link. Rather than handling the full quadratic
        // (which as we have discovered allowed for sneaky typos)
        // this is a much simpler - and faster - way of obtaining the same results.
        double optimizedP = (2.0 * (vA - vB)) / (massA + massB);

        // Now we apply that calculated function to the pair of balls to obtain their final velocities
        Point2D velAPrime = velocityA.subtract(collisionVector.multiply(optimizedP).multiply(massB));
        Point2D velBPrime = velocityB.add(collisionVector.multiply(optimizedP).multiply(massA));

        return new Pair<>(velAPrime, velBPrime);
    }

    public ArrayList<Ball> getBallArray() {
        return ballArray;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        initialiseCueStickMouseRegister();
    }

    private void initialiseCueStickMouseRegister() {
        this.hitBallController = new HitBallController(gc);
        hitBallController.setWhiteBall(whiteBall);

        this.scene.setOnMousePressed(event -> {
            hitBallController.setAnchorX(event.getSceneX());
            hitBallController.setAnchorY(event.getSceneY());
        });

        this.scene.setOnMouseDragged(event -> {
            hitBallController.setTranslateX(event.getSceneX());
            hitBallController.setTranslateY(event.getSceneY());
            hitBallController.updateVisualCue(event.getSceneX(), event.getSceneY());

        });

        this.scene.setOnMouseReleased(event -> {
            hitBallController.displayMove();
            hitBallController.updateVisualCue(whiteBall.getPosition().getX(), whiteBall.getPosition().getY());
        });
    }
}
