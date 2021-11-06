package module_8;

/*Задание 2 - LinkedList#
Написать свой класс MyLinkedList как аналог классу LinkedList.

Нельзя использовать массив. Каждый элемент должен быть отдельным объектом-посредником (Node - нода), который хранит
ссылку на предыдущий и следующий элемент коллекции (двухсвязный список).

Методы

add(Object value) добавляет элемент в конец
remove(int index) удаляет элемент под индексом
clear() очищает коллекцию
size() возвращает размер коллекции
get(int index) возвращает элемент под индексом*/

public class MyLinkedList implements MyList {

    private static class Node {
        Node previous;
        Object element;
        Node next;

        public Node(Node previous, Object element, Node next) {
            this.previous = previous;
            this.element = element;
            this.next = next;
        }

        public Node() {
            this.previous = null;
            this.element = null;
            this.next = null;
        }
    }

    private int size;
    private Node headNode = null;
    private Node tailNode = null;

    public MyLinkedList() {
        clear();
    }

    @Override
    public boolean add(Object value) {
        if (value != null) {
            if (size == 0) {
                Node newNode = new Node();
                headNode = newNode;
                tailNode = newNode;
            } else if (size == 1) {
                tailNode = new Node(headNode, value, null);
                headNode.next = tailNode;
            } else {
                tailNode = new Node(this.tailNode, value, null);
                tailNode.previous.next = tailNode;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object remove(int index) throws IllegalArgumentException {
        if (index + 1 > size || index < 0) {
            throw new IllegalArgumentException("Index \"" + index + "\" is out of this list bounds");
        }
        Object result;
        if (size == 1) {
            result = tailNode.element;
            clear();
        } else {
            Node removingNode = nodeSearching(index);
            result = removingNode.element;
            if (removingNode != headNode && removingNode != tailNode){
                removingNode.previous.next = removingNode.next;
                removingNode.next.previous = removingNode.previous;
            } else if (removingNode == headNode) {
                removingNode.next.previous = null;
                headNode = removingNode.next;
            } else {
                removingNode.previous.next = null;
                tailNode = removingNode.previous;
            }
            size--;
        }
        return result;
    }

    @Override
    public void clear() {
        headNode = new Node();
        tailNode = new Node();
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int index) {
        return nodeSearching(index).element;
    }

    private Node nodeSearching(int index) {
        return index + 1 > size / 2 ? fromTailNodeSearching(index) : fromHeadNodeSearching(index);
    }

    private Node fromTailNodeSearching(int index) throws IllegalArgumentException {
        if (index + 1 > size || index < 0) {
            throw new IllegalArgumentException("Index \"" + index + "\" is out of this list bounds");
        } else {
            Node searchingNode = tailNode;
            for (int i = size - 1; i > index; i--) {
                searchingNode = searchingNode.previous;
            }
            return searchingNode;
        }
    }

    private Node fromHeadNodeSearching(int index) throws IllegalArgumentException {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("Index \"" + index + "\" is out of this list bounds");
        } else {
            Node searchingNode = headNode;
            for (int i = 0; i < index; i++) {
                searchingNode = searchingNode.next;
            }
            return searchingNode;
        }
    }

    @Override
    public String toString() {
        Node bufferNode = headNode;
        StringBuilder result = new StringBuilder("MyLinkedList {size=" + size + "; ");
        for (int i = 0; i < size; i++) {
            if (bufferNode.element != null) {
                result.append(bufferNode.element);
                result.append(", ");
                bufferNode = bufferNode.next;
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }
}
