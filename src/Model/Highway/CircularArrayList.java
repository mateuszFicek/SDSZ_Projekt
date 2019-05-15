package Model.Highway;

import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E> {
    private static final long serialVersionUID = 1L;
    public CircularArrayList() {
        super();
    }

    public CircularArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public E get(int index) {
        int newIndex;
        if (index < 0) {
            newIndex = size() + index;
        } else if (index >= size()) {
            newIndex = index - size();
        }
        else {
            newIndex = index;
        }

        return super.get(newIndex);
    }

    public static void main(String[] args) {

        CircularArrayList<Cell> pas = new CircularArrayList<>();
        CircularArrayList<Cell> pas2 = new CircularArrayList<>();
        for(int i = 0; i<20;i++){
            pas.set(i,new Cell());
            pas2.set(i,new Cell());
        }
        ArrayList<CircularArrayList<Cell>> listaPasow = new ArrayList<>();
        listaPasow.add(pas);
        listaPasow.add(pas2);

        for(int j = 0; j<30;j++)
            System.out.println(j + "-------" + listaPasow.get(1).get(j));
    }

}