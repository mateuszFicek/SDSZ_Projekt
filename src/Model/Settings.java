package Model;

public class Settings {

    public static boolean initialized = false;

    public String[] times = new String[]{"Noc", "Poranek", "Popołudnie"};
    private int time;
    private int carMaxVelocity;
    private int tirMaxVelocity;

    public Settings(){
        time = 2;
        carMaxVelocity = 5;
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
}
