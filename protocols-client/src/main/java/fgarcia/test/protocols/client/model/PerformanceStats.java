package fgarcia.test.protocols.client.model;

public class PerformanceStats {
    private int id;
    private long start;
    private long end;
    private long size;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long duration) {
        this.end = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return end - start;
    }
}
