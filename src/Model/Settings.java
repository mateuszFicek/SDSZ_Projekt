package Model;

public class Settings {

    public static boolean initialized = false;
    public static int carMaxUpperVelocity;

    public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    public static int carMaxVelocity;
    public static int tirMaxVelocity;
    public static int[] throughput = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    public Settings() {
        time = 2;
        carMaxVelocity = 3;
        carMaxUpperVelocity = 5;
        tirMaxVelocity = 3;
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
        Settings.carMaxVelocity = carMaxVelocity;
    }

    public int getTirMaxVelocity() {
        return tirMaxVelocity;
    }

    public void setTirMaxVelocity(int tirMaxVelocity) {
        Settings.tirMaxVelocity = tirMaxVelocity;
    }

    public int[] getThroughput() {
        return throughput;
    }

    public int getCarMaxUpperVelocity() {
        return carMaxUpperVelocity;
    }

    public void setCarMaxUpperVelocity(int carMaxUpperVelocity) {
        Settings.carMaxUpperVelocity = carMaxUpperVelocity;
    }
}
