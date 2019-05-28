package View;

import Model.Vehicles.Vehicle;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class HighwayGrid {
    private Cell[][] cellMatrix;
    private int lanes;
    private int cells;

    public HighwayGrid(int lanes, int cells) {
        this.lanes = lanes;
        this.cells = cells;
        initializeCellMatrix(lanes, cells);

    }

    public void setCellMatrixColor(int x, int y, Color color) {
        cellMatrix[x][y] = new Cell(color, x, y);
    }

    public void setCellMatrixVehicleToObserve(int x, int y, Vehicle vehicle) {
        cellMatrix[x][y].setVehicle(vehicle);
    }


    public Cell[][] getCellMatrix() {
        return cellMatrix;
    }

    public void initializeCellMatrix(int x, int y) {
        Cell[][] cellsCreator = new Cell[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cellsCreator[i][j] = new Cell(Color.RED, i, j);
            }
        }

        this.cellMatrix = cellsCreator;
    }

    public Parent getGrid() {
        GridPane grid = new GridPane();
        grid.setPrefSize(cells * 3, lanes * 3);
        for (int i = 0; i < lanes; ++i) {
            for (int j = 0; j < cells; ++j) {
                grid.add(getCellMatrix()[i][j], j, i);
            }
        }

        return grid;
    }
}
