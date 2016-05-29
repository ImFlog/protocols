package fgarcia.test.protocols.client.services;

import fgarcia.test.protocols.client.model.PerformanceStats;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PerformanceService {

    Map<Integer, PerformanceStats> counterList = new HashMap<>();

    private void cleanCounter() {
        counterList.clear();
    }

    public void startCounter(int counterId) {
        PerformanceStats stat = new PerformanceStats();
        stat.setStart(System.currentTimeMillis());
        stat.setId(counterId);
        counterList.put(counterId, stat);
    }

    public void setSize(int counterId, long size) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setSize(size);
    }

    public void endCounter(int counterId) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setEnd(System.currentTimeMillis());
    }

    /**
     * Export results to a csv file for analysis
     */
    public void exportResults(String filename) throws IOException {
        FileWriter writer;
        File file = new File(filename + ".csv");
        // creates the file
        file.createNewFile();
        writer = new FileWriter(file);

        // Write CSV
        for (Map.Entry<Integer, PerformanceStats> entry : counterList.entrySet()) {
            writer.write(String.valueOf(entry.getKey()));
            writer.write(",");
            writer.write(String.valueOf(entry.getValue().getDuration()));
            writer.write(",");
            writer.write(String.valueOf(entry.getValue().getSize()));
            writer.write("\r\n");
        }

        writer.flush();
        writer.close();
        cleanCounter();
    }
}
