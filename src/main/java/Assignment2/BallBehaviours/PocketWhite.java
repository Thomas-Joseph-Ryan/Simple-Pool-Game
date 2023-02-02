package Assignment2.BallBehaviours;

public class PocketWhite extends PocketBehaviour{
    @Override
    public void think() {
        ball.setInPlay(false);
    }
}
