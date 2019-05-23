package Model.Highway;

import Model.Vehicles.Vehicle;

public class Cell {

    public enum CellType {
        EXIT, ENTRY, NORMAL, DISABLED
    }

    public static double measure = 7.5;
    public boolean occupied;
    public Vehicle vehicle;
    public CellType cellType;

    public Cell() {
        setOccupied(false);
    }

    public Cell(CellType type) {
        setOccupied(false);
        cellType = type;
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

    public void occupyCell(Vehicle vehicleToSet) {
        occupied = true;
        vehicle = vehicleToSet;
    }

    public void freeCell() {
        occupied = false;
        vehicle = null;
    }

}