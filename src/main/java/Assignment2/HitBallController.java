package Assignment2;

import Assignment2.BallBuilders.Ball;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class HitBallController {

    private double anchorX;
    private double anchorY;

    private double translateX;
    private double translateY;

    private Ball whiteBall;

    private GraphicsContext gc;
    private VisualCue cue;

    private boolean mouseTouchedBall;

    public HitBallController(GraphicsContext gc) {
        this.gc = gc;
        this.cue = new VisualCue(gc);
        this.mouseTouchedBall = true;
    }

    public void displayMove() {
        if (checkIfInWhiteBall()) {

            Point2D centerBall = whiteBall.getPosition();
            Point2D draggedPosition = new Point2D(translateX, translateY);

            Point2D vector = centerBall.subtract(draggedPosition);

//            System.out.println(vector.toString());

            whiteBall.applyForce(vector.multiply(0.1));
            this.setMouseTouchedBall(false);

            translateX = 0;
            translateY = 0;
        }
    }

    public boolean checkIfInWhiteBall() {
        Point2D pos = whiteBall.getPosition();
        if (pos.getX() - whiteBall.getRadius() < anchorX && anchorX < pos.getX() + whiteBall.getRadius()) {
            if (pos.getY() - whiteBall.getRadius() < anchorY && anchorY < pos.getY() + whiteBall.getRadius()) {
                this.setMouseTouchedBall(true);
                return true;
            }
        }
        return false;
    }

    public void updateVisualCue(double translateX, double translateY) {
        if (checkIfInWhiteBall()) {
            cue.setX(whiteBall.getPosition().getX());
            cue.setY(whiteBall.getPosition().getY());
            cue.setSizeAndRotation(translateX - whiteBall.getPosition().getX()
                    , translateY - whiteBall.getPosition().getY());
        }
    }

    public void setMouseTouchedBall(boolean mouseTouchedBall) {
        this.mouseTouchedBall = mouseTouchedBall;
    }

    public boolean hasMouseTouchedBall() {
        return mouseTouchedBall;
    }

    public void draw() {
        cue.draw(whiteBall.getPosition().getX(), whiteBall.getPosition().getY());
    }

    public void setWhiteBall(Ball whiteBall) {
        this.whiteBall = whiteBall;
    }

    public void setAnchorX(double anchorX) {
        this.anchorX = anchorX;
    }

    public void setAnchorY(double anchorY) {
        this.anchorY = anchorY;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getAnchorX() {
        return anchorX;
    }

    public double getAnchorY() {
        return anchorY;
    }

}
