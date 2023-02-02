package Assignment2.ConfigReaders;

public class BallConfigReaderFactory extends ConfigReaderFactory {

    @Override
    protected ConfigReader createReader(String src) {

        return new BallConfigReader(src);
    }
}
