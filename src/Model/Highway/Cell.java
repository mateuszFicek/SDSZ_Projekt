package Model.Highway;

import Model.Vehicles.Vehicle;

//Na kratce moze znajdowac sie pojazd

class Cell {

    public enum CellType {
        EXIT, ENTRY, NORMAL, DISABLED
    }

    protected boolean occupied;
    protected Vehicle vehicle;
    protected CellType cellType;

    boolean getOccupied() {
        return occupied;
    }

    void setOccupied(boolean cellState) {
        occupied = cellState;
    }

    CellType getCellType() {
        return cellType;
    }

    void setCellType(CellType type) {
        cellType = type;
    }

}