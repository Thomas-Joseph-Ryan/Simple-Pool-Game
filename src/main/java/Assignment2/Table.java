package Assignment2;

import Assignment2.BallBuilders.Ball;
import Assignment2.ConfigReaders.ConfigReader;
import Assignment2.ConfigReaders.ConfigReaderFactory;
import Assignment2.ConfigReaders.TableConfigReader;
import Assignment2.ConfigReaders.TableConfigReaderFactory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Table {

    private Paint colour;
    private double width;
    private double height;
    private double friction;
    private GraphicsContext gc;
    private ArrayList<Ball> balls;

    private double cornerRadius = 62;

    private double middleRadius = 42;

    private ArrayList<Pocket> pockets = new ArrayList<>();
    private TableConfigReader tableReader;

    public Table() {
        ConfigReaderFactory tableReaderFactory = new TableConfigReaderFactory();
        this.tableReader = (TableConfigReader) tableReaderFactory.useReader("src/main/resources/config.json");
        tableReader.getInfo();
        colour = Paint.valueOf(tableReader.getColour().toUpperCase());
        width = tableReader.getX();
        height = tableReader.getY();
        friction = tableReader.getFriction();
    }

    public void tick() {
        for (Pocket pocket : pockets) {
            pocket.tick(balls);
        }
    }

    public void draw() {
        gc.setFill(colour);
        gc.fillRect(0, 0, width, height);

        for (Pocket pocket : pockets) {
            pocket.draw();
        }
    }

    public Paint getColour() {
        return colour;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getFriction() {
        return friction;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
        createPockets();
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public void createPockets() {
        double scale = width + height;
        cornerRadius = cornerRadius + scale/50;
        middleRadius = middleRadius + scale/50;
        //Corners
        pockets.add(new Pocket(0, 0, gc, cornerRadius));
        pockets.add(new Pocket(width, 0, gc, cornerRadius));
        pockets.add(new Pocket(0, height, gc, cornerRadius));
        pockets.add(new Pocket(width, height, gc, cornerRadius));

        //Middle
        pockets.add(new Pocket(width/2, height, gc, middleRadius));
        pockets.add(new Pocket(width/2, 0, gc, middleRadius));
    };
}
