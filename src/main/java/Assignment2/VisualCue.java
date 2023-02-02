package Assignment2;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class VisualCue {

    private double x;
    private double y;
    private double width;
    private double height;
    private double angle;
    private GraphicsContext gc;

    public VisualCue(GraphicsContext gc) {
        this.width = 2;
        this.gc = gc;
//        gc.getCanvas().getScene()
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSizeAndRotation(double translateX, double translateY) {
        Point2D sizeScalar = new Point2D(translateX, translateY);
        double scale = sizeScalar.magnitude();
        this.height = 0.2 * scale;
        if (translateX == 0 && translateY == 0) {
            this.height = 0;
        }
        this.angle = Math.atan2(translateY, translateX) * (180/3.1415) + 90;

    }

    public void draw(double centreX, double centreY) {
        gc.save();
        gc.setFill(Paint.valueOf("BLACK"));
        gc.transform(new Affine(new Rotate(angle, centreX, centreY)));
        gc.fillRect(centreX, centreY, width, height);
        gc.restore();
    }
}
