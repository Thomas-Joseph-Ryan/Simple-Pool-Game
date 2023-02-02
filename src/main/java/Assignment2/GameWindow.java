package Assignment2;

import Assignment2.BallBuilders.Ball;
import com.sun.marlin.stats.Histogram;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class GameWindow {
    private final double FRAMEDURATION = 1.0/60;
    private GraphicsContext gc;
    private Scene scene;
    private Pool model;

    public GameWindow() {
        this.model = new Pool(this);

        Pane pane = new Pane();
        this.scene = new Scene(pane, model.getWidth(), model.getHeight());
        Canvas canvas = new Canvas(model.getWidth(), model.getHeight());
        gc = canvas.getGraphicsContext2D();
        pane.getChildren().add(canvas);

        startGame();
    }

    public void startGame() {
        this.model = new Pool(this);
        model.setGc(gc);

    }

    Scene getScene() {
        return this.scene;
    }

    void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(FRAMEDURATION * 1000),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        if (model.isWhiteBallPocketed()) {
            startGame();
        }
        gc.clearRect(0, 0, model.getWidth(), model.getHeight());
        if (model.isGameRunning()) {
            model.draw();
            model.tick();
        } else {
            gc.setFill(Paint.valueOf("BLACK"));
            gc.fillText("Win and Bye", model.getWidth()/2, model.getHeight()/2);
        }

    }


}
