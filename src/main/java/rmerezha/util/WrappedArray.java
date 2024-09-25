package rmerezha.util;

public class WrappedArray<T> {

    private T[] array;
    private final int size;

    public WrappedArray(int size) {
        this.size = size;
        array = (T[]) new Object[size];
    }

    public void add(T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = value;
                return;
            }
        }
    }

    public T get(int index) {
        return array[index];
    }

    public void remove() {
        for (int i = array.length - 1; i <= 0; i--) {
            if (array[i] == null) {
                array[i] = null;
                return;
            }
        }
    }
    public int size() {
        int size = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                size++;
                continue;
            }
            break;
        }
        return size;
    }

    public void clear() {
        array = (T[]) new Object[size];
    }
}
