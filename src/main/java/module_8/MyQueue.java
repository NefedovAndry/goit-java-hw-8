package module_8;

/*Задание 3 - Queue#
Написать свой класс MyQueue как аналог классу Queue, который будет работать по принципу FIFO (first-in-first-out).

Можно делать либо с помощью Node либо с помощью массива.

Методы

add(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
peek() возвращает первый элемент в очереди (FIFO)
poll() возвращает первый элемент в очереди и удаляет его из коллекции*/

public class MyQueue extends MyArrayList {

    private int currentSize = 0;

    public MyQueue() {
        super();
    }

    @Override
    public boolean add(Object value) {
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
        new MyQueue();
        currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    public Object peek() {
        return super.get(0);
    }

    public Object poll() {
        Object result = super.remove(0);
        currentSize--;
        return result;
    }
}
