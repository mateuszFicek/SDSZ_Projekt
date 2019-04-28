package Model;
import Model.Highway.*;



public class Main {

    public static void main(String[] args) {
        Highway highway = new Highway(2);
        highway.setupHighway();

         for(int k = 0; k < 1000;k++){
             System.out.println(k + "   " + highway.highway[0].road[0].lane[k].getCellType() + " " + highway.highway[0].road[1].lane[k].getCellType()+ " " + highway.highway[0].road[2].lane[k].getCellType());
         }


    }
}
