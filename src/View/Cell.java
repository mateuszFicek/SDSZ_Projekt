package View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    private final int width = 7;
    private final int height = 7;
    private final int x;
    private final int y;
    private Color color;

    public void setColor(Color color){
        this.color = color;
        this.setFill(color);
    }

    public Color getColor() {
        return color;
    }

    public Cell(Color c, int x, int y){
        this.color = c;
        this.x = x;
        this.y = y;

        setFill(c);
        setHeight(this.height);
        setWidth(this.width);
        relocate(x*this.width, y*this.height);
    }
}
