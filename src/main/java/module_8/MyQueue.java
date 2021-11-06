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

public class MyQueue {

    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private int size = 0;
    private Node headNode;
    private Node tailNode;

    private static class Node {
        final Object value;
        Node next;

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(Object value) {
            this.value = value;
            this.next = null;
        }

        public Node() {
            this.value = null;
            this.next = null;
        }
    }

    public MyQueue() {
        clear();
    }

    public boolean add(Object value) {
        if (size >= MAX_SIZE) {
            throw new IllegalArgumentException("Sorry, queue is too big.");
        }
        if (value != null) {
            Node newNode = new Node(value);
            if (size == 0) {
                headNode = newNode;
            } else {
                tailNode.next = newNode;
            }
            tailNode = newNode;
            size++;
            return true;
        } else {
            throw new NullPointerException("Trying to add null-value");
        }
    }

    public Object remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        Object result;
        Node bufferNode = headNode;
        if (index == 1) {
            result = bufferNode.value;
            clear();
        } else {
            for (int i = 0; i < index - 1; i++) {
                bufferNode = bufferNode.next;
            }
            result = bufferNode.next.value;
            bufferNode.next = bufferNode.next.next;
            size--;
        }
        return result;
    }

    public void clear() {
        headNode = new Node();
        tailNode = new Node();
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object peek() {
        return headNode.value;
    }

    public Object poll() {
        if (size == 0) {
            return null;
        }
        Object result = headNode.value;
        headNode = headNode.next;
        size--;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("MyQueue {size=" + size + "; ");
        Node bufferNode = headNode;
        for (int i = 0; i < size; i++) {
            result.append(bufferNode.value);
            result.append(", ");
            bufferNode = bufferNode.next;
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
