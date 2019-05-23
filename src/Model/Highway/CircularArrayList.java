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
        } else {
            newIndex = index;
        }

        return super.get(newIndex);
    }
}