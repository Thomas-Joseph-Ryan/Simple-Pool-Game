package Assignment2;

import Assignment2.BallBuilders.Ball;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Pool {

    private final double height;
    private final double width;
    private BallManager ballManager;
    private Table table;
    private GraphicsContext gc;
    private GameWindow gameWindow;
    private boolean gameRunning;
    private boolean whiteBallInPocket;


    public Pool(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.table = new Table();
        this.height = table.getHeight();
        this.width = table.getWidth();
        this.ballManager = new BallManager(table.getFriction(), width, height, this);
        this.gameRunning = true;
        this.whiteBallInPocket = false;
        table.setBalls(ballManager.getBallArray());
    }

    void tick() {
        table.tick();
        ballManager.tick();
    }

    void draw() {

        table.draw();
        ballManager.draw();

    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isWhiteBallPocketed() {
        return whiteBallInPocket;
    }

    public void setWhiteBallInPocket(boolean b) {
        this.whiteBallInPocket = b;
    }

    public ArrayList<Ball> getBalls() {
        return ballManager.getBallArray();
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
        this.ballManager.setGc(gc);
        this.ballManager.setScene(this.gameWindow.getScene());
        this.table.setGc(gc);
    }

}
