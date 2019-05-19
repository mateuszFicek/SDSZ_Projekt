package Model;

public class Settings {

    public static boolean initialized = false;
    private int carMaxUpperVelocity;

    public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    private int carMaxVelocity;
    private int tirMaxVelocity;
    private int[] throughput = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    public Settings(){
        time = 2;
        carMaxVelocity = 3;
        carMaxUpperVelocity = 7;
        tirMaxVelocity = 4;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCarMaxVelocity() {
        return carMaxVelocity;
    }

    public void setCarMaxVelocity(int carMaxVelocity) {
        this.carMaxVelocity = carMaxVelocity;
    }

    public int getTirMaxVelocity() {
        return tirMaxVelocity;
    }

    public void setTirMaxVelocity(int tirMaxVelocity) {
        this.tirMaxVelocity = tirMaxVelocity;
    }

    public int[] getThroughput() {
        return throughput;
    }

    public void setThroughput(int[] throughput) {
        this.throughput = throughput;
    }

    public int getCarMaxUpperVelocity() {
        return carMaxUpperVelocity;
    }

    public void setCarMaxUpperVelocity(int carMaxUpperVelocity) {
        this.carMaxUpperVelocity = carMaxUpperVelocity;
    }
}
