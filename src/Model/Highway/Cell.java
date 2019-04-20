package Model.Highway;

import Model.Vehicles.Vehicle;

//Na kratce moze znajdowac sie pojazd

public class Cell {

    public enum CellType {
        EXIT, ENTRY, NORMAL, DISABLED
    }

    public boolean occupied;
    public Vehicle vehicle;
    public CellType cellType;

    public Cell(){
        setOccupied(true);
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

}