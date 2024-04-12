package fr.hetic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataReader {
    Map<String, List<String>> readData() throws IOException;
}