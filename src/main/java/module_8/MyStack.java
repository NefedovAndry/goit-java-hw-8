package module_8;

/*Задание 4 - Stack#
Написать свой класс MyStack как аналог классу Stack, который работает по принципу LIFO (last-in-first-out).

Можно делать либо с помощью Node либо с помощью массива.

Методы

push(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
peek() возвращает первый элемент в стеке (LIFO)
pop() возвращает первый элемент в стеке и удаляет его из коллекции*/

public class MyStack extends MyArrayList {
    private int currentSize = 0;

    public MyStack() {
        super();
    }

    public boolean push(Object value) {
        currentSize++;
        return super.add(value);
    }

    @Override
    public Object remove(int index) {
        Object result = super.remove(index);
        currentSize--;
        return result;
    }

    @Override
    public void clear() {
        new MyStack();
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    public Object peek() {
        return super.get(currentSize - 1);
    }

    public Object poll() {
        Object result = super.remove(currentSize - 1);
        currentSize--;
        return result;
    }
}
