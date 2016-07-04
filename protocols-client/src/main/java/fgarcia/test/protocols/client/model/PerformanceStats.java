package fgarcia.test.protocols.client.model;

/**
 * Performance statistic container.
 */
public class PerformanceStats {
    private int id;
    private long startServer;
    private long endServer;
    private long startClient;
    private long endClient;
    private long size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStartServer() {
        return startServer;
    }

    public void setStartServer(long startServer) {
        this.startServer = startServer;
    }

    public long getEndServer() {
        return endServer;
    }

    public void setEndServer(long endServer) {
        this.endServer = endServer;
    }

    public long getStartClient() {
        return startClient;
    }

    public void setStartClient(long startClient) {
        this.startClient = startClient;
    }

    public long getEndClient() {
        return endClient;
    }

    public void setEndClient(long endClient) {
        this.endClient = endClient;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getServerDuration() {
        return endServer - startServer;
    }

    public long getClientDuration() {
        return endClient - startClient;
    }
}
