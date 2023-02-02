package Assignment2.ConfigReaders;

public abstract class ConfigReaderFactory {
    public ConfigReader useReader(String src) {
        ConfigReader reader = createReader(src);

        return reader;
    }

    protected abstract ConfigReader createReader(String src);
}
