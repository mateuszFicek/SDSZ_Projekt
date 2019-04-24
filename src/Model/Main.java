package Model;
import Model.Highway.*;



public class Main {

    public static void main(String[] args) {
        Highway highway = new Highway(2);
        System.out.println(highway.highway[0].road[0].lane[870].getCellType());
        System.out.println(highway.highway[0].road[1].lane[70].getCellType());
    }
}
