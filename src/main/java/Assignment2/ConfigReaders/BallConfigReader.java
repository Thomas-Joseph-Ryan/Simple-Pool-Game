package Assignment2.ConfigReaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class BallConfigReader implements ConfigReader {

    public String src;
    public String colour;
    public double xPos;
    public double yPos;
    public double xVel;
    public double yVel;
    public double mass;

    private JSONArray jsonBallsArray;
    private int index;


    public BallConfigReader(String src) {
        this.src = src;
        this.index = 0;
    }

    @Override
    public boolean getInfo() {

        JSONParser parser = new JSONParser();

        try {
            Object object = parser.parse(new FileReader(src));

            JSONObject jsonObject = (JSONObject) object;

            JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

            jsonBallsArray = (JSONArray) jsonBalls.get("ball");

            if (index == jsonBallsArray.size()) {
                return false;
            }

            JSONObject jsonBall = (JSONObject) jsonBallsArray.get(index);

            colour = (String) jsonBall.get("colour");

            xPos = (Double) ((JSONObject) jsonBall.get("position")).get("x");
            yPos = (Double) ((JSONObject) jsonBall.get("position")).get("y");

            xVel = (Double) ((JSONObject) jsonBall.get("velocity")).get("x");
            yVel = (Double) ((JSONObject) jsonBall.get("velocity")).get("y");

            mass = (Double) jsonBall.get("mass");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
//        System.out.println("Ball colour: " + colour + ", x: " + xPos + ", mass: " + mass);
        index ++;
        return true;
    }
}
