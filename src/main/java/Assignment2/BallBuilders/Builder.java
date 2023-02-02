package Assignment2.BallBuilders;

import Assignment2.BallBehaviours.PocketBehaviour;

public interface Builder {

    void reset();

    void setColour(String colour);

    public void setPosition(double x, double y);

    public void setVelocity(double x, double y);

    public void setAcceleration();
    public void setRadius(double scale);

    void setPocketBehaviour(PocketBehaviour pB);
    void setMass(double mass);

    Ball build();

}
