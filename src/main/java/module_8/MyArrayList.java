package module_8;

/*Задание 1 - ArrayList#
Написать свой класс MyArrayList как аналог классу ArrayList.

Можно использовать 1 массив для хранения данных.

Методы

add(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
get(int index) возвращает элемент под индексом*/

public class MyArrayList implements MyList {

    private static final int START_SIZE = 10;
    private static final int MAX_SIZE = Integer.MAX_VALUE;
    protected Object[] array;
    private int currentSize;
    protected int currentLength;

    public MyArrayList() {
        clear();
    }

    public boolean add(Object value) {
        try {
            if (currentSize >= currentLength - 1) {
                grow();
            }
            array[currentSize] = value;
            currentSize++;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Object remove(int index) {
        if (index < 0 || currentSize <= 0) {
            return null;
        }
        Object buffer = array[index];
        if (currentLength - index >= 0) System.arraycopy(array, index + 1, array, index, currentLength - index - 1);
        currentSize--;
        if (currentSize <= currentLength / 2) {
            grow();
        }
        return buffer;
    }

    public Object get(int index) {
        return array[index];
    }

    public void clear() {
        this.array = new Object[START_SIZE];
        this.currentLength = START_SIZE;
        this.currentSize = 0;
    }

    public int size() {
        return currentSize;
    }

    private void grow() throws IllegalArgumentException {
        if (currentSize >= MAX_SIZE) {
            throw new IllegalArgumentException("Sorry, list too big.");
        }
        currentLength = currentSize + currentSize / 2 + 1;                  // +1 - for avoid increasing problems with currentLength = 1
        if (currentLength < currentSize) {                                  // if we passed MAX_VALUE of int
            currentLength = MAX_SIZE;
        }
        Object[] newArray = new Object[currentLength];
        System.arraycopy(array, 0, newArray, 0, currentSize);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyArrayList {size=" + currentSize + "; length=" + currentLength + "; ");
        for (int i = 0; i < currentSize; i++) {
            result.append(array[i].toString());
            result.append(", ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
