package Assignment2;

import Assignment2.BallBuilders.Ball;
import Assignment2.BallBuilders.BallBuilder;
import Assignment2.BallBuilders.Builder;
import Assignment2.BallBuilders.Director;
import Assignment2.ConfigReaders.*;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameWindow window = new GameWindow();
        window.run();

        primaryStage.setTitle("Strategy Balls");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
