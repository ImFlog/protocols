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

    public void startServerCounter(int counterId) {
        PerformanceStats stat = new PerformanceStats();
        stat.setStartServer(System.currentTimeMillis());
        stat.setId(counterId);
        counterList.put(counterId, stat);
    }

    public void endServerCounter(int counterId) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setEndServer(System.currentTimeMillis());
    }

    public void startClientCounter(int counterId) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setStartClient(System.currentTimeMillis());
    }

    public void endClientCounter(int counterId) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setEndClient(System.currentTimeMillis());
    }

    public void setSize(int counterId, long size) {
        PerformanceStats stat = counterList.get(counterId);
        stat.setSize(size);
    }

    /**
     * Export results to a csv file for analysis
     */
    public void exportResults(String filename) throws IOException {
        FileWriter writer;
        File file = new File(filename + ".csv");
        // creates the file
        if (file.createNewFile()) {
            writer = new FileWriter(file);
            writer.write("N°,Server duration,Client duration,Size\r\n");

            // Write CSV
            for (Map.Entry<Integer, PerformanceStats> entry : counterList.entrySet()) {
                writer.write(String.valueOf(entry.getKey()));
                writer.write(",");
                writer.write(String.valueOf(entry.getValue().getServerDuration()));
                writer.write(",");
                writer.write(String.valueOf(entry.getValue().getClientDuration()));
                writer.write(",");
                writer.write(String.valueOf(entry.getValue().getSize()));
                writer.write("\r\n");
            }

            writer.flush();
            writer.close();
        }
        cleanCounter();
    }
}
