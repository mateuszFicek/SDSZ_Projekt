package Model.Highway;

import Model.Vehicles.Vehicle;

//Na kratce moze znajdowac sie pojazd

public class Cell {

    public enum CellType {
        EXIT, ENTRY, NORMAL, DISABLED
    }

    public static double Measure= 7.5;
    public boolean occupied;
    public Vehicle vehicle;
    public CellType cellType;

    public Cell(){
        setOccupied(false);
    }

    public boolean getOccupied() {
        return occupied;
    }

    void setOccupied(boolean cellState) {
        occupied = cellState;
    }

    public CellType getCellType() {
        return cellType;
    }

    void setCellType(CellType type) {
        cellType = type;
    }

    public void OccupyCell(Vehicle vehicleToSet)
    {
        occupied = true;
        vehicle = vehicleToSet;
    }

    public void FreeCell()
    {
        occupied = false;
        vehicle = null;
    }

}