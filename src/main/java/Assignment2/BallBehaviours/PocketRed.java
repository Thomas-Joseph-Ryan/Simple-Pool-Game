package Assignment2.BallBehaviours;

public class PocketRed extends PocketBehaviour{
    @Override
    public void think() {
        ball.setInPlay(false);
    }
}
