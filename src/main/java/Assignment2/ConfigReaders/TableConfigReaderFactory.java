package Assignment2.ConfigReaders;

public class TableConfigReaderFactory extends ConfigReaderFactory {

    @Override
    protected ConfigReader createReader(String src) {

        return new TableConfigReader(src);
    }
}
