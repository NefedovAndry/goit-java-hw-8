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
    private Object[] array;
    private int currentSize = 0;
    private int currentLength = START_SIZE;

    public MyArrayList() {
        array = new Object[START_SIZE];
    }

    public MyArrayList(int newArraySize) {
        currentLength = newArraySize < 1 ? 1 : newArraySize;
        array = new Object[currentLength];
    }

    public MyArrayList(Object[] incomingArray) {
        if (incomingArray != null) {
            currentLength = incomingArray.length + 1;
            currentSize = incomingArray.length;
            array = new Object[currentLength];
            System.arraycopy(incomingArray, 0, array, 0, incomingArray.length);
        } else {
            new MyArrayList();
        }
    }

    public boolean add(Object value) {
        try {
            if (currentSize >= currentLength) {
                grow();
            } else {
                array[currentSize - 1] = value;
                currentSize++;
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean add(int index, Object value) {
        try {
            if (currentSize >= currentLength) {
                grow();
            } else {
                System.arraycopy(array, index, array, index + 1, currentLength - 1 - index); // because the last element is always null
                array[index] = value;
                currentSize++;
                return true;
            }
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
        if (currentLength - index >= 0) System.arraycopy(array, index + 1, array, index, currentLength - index);
        currentSize--;
        if (currentLength % currentSize == currentSize - 1) {
            grow();
        }
        return buffer;
    }

    public Object get(int index) {
        return array[index];
    }

    public void clear() {
        new MyArrayList();
        currentSize = 0;
        currentLength = START_SIZE;
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
        System.arraycopy(newArray, 0, array, 0, currentSize);
        array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyArrayList { size=" + currentSize + "; ");
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
