public class PQElement {

    private String data;
    private double priority;

    public PQElement(String s, double d) {
        this.data = s;
        this.priority = d;
    }

    public String getData() {
        return data;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}
