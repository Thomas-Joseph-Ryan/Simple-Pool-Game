package Assignment2.ConfigReaders;

import Assignment2.ConfigReaders.ConfigReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class TableConfigReader implements ConfigReader {

    String src;
    String colour;
    double friction;
    long x;
    long y;

    public TableConfigReader(String src) {
        this.src = src;

    }
    @Override
    public boolean getInfo() {
        //This is heavily inspired by the Example ConfigReader found on the Assignment Spec.
        JSONParser parser = new JSONParser();
        try {
            Object object = parser.parse(new FileReader(src));

            JSONObject jsonObject = (JSONObject) object;

            JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

            colour = (String) jsonTable.get("colour");

            x = (Long) ((JSONObject) jsonTable.get("size")).get("x");

            y = (Long) ((JSONObject) jsonTable.get("size")).get("y");

            friction = (Double) jsonTable.get("friction");


        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
        //System.out.println("Table colour: " + colour + ", x: " + x + ", y: " + y + ", friction: " + friction);

        return true;
    }

    public String getColour() {
        return colour;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public double getFriction() {
        return friction;
    }
}
