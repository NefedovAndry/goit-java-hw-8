package module_8;

public class MyTest {
    public static void main(String[] args) {
        testingMyArrayList();
        testingMyLinkedList();
        testingMyQueue();
        testingMyStack();
        testingMeHashMap();
    }

    private static void testingMeHashMap() {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put("0", 0);
        myHashMap.put("1", 1);
        myHashMap.put("2", 2);
        myHashMap.put("3", 3);
        myHashMap.put("4", 4);
        myHashMap.put("5", 5);
        myHashMap.put("6", 6);
        myHashMap.put("7", 7);
        myHashMap.put("8", 8);
        myHashMap.put("9", 9);
        System.out.println(myHashMap);
        myHashMap.remove("6");
        myHashMap.remove("6");
        System.out.println(myHashMap);
        System.out.println(myHashMap.get("2"));
        myHashMap.put("9", 99);
        System.out.println(myHashMap);
        System.out.println(myHashMap.get("3"));
        myHashMap.clear();
        System.out.println(myHashMap);
    }

    private static void testingMyStack() {
        MyList myStack = new MyStack();
        ((MyStack) myStack).push(0);
        ((MyStack) myStack).push(1);
        ((MyStack) myStack).push(2);
        ((MyStack) myStack).push(3);
        ((MyStack) myStack).push(4);
        ((MyStack) myStack).push(5);
        ((MyStack) myStack).push(6);
        ((MyStack) myStack).push(7);
        ((MyStack) myStack).push(8);
        ((MyStack) myStack).push(9);
        System.out.println(myStack.toString());
        myStack.remove(6);
        myStack.remove(6);
        System.out.println(myStack.toString());
        System.out.println(((MyStack) myStack).peek());
        ((MyStack) myStack).poll();
        ((MyStack) myStack).poll();
        System.out.println(myStack.toString());
        System.out.println(((MyStack) myStack).peek());
        myStack.clear();
        System.out.println(myStack.toString());
    }

    private static void testingMyQueue() {
        MyList myQueue = new MyQueue();
        myQueue.add(0);
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        myQueue.add(5);
        myQueue.add(6);
        myQueue.add(7);
        myQueue.add(8);
        myQueue.add(9);
        System.out.println(myQueue.toString());
        myQueue.remove(6);
        myQueue.remove(6);
        System.out.println(myQueue.toString());
        System.out.println(((MyQueue) myQueue).peek());
        ((MyQueue) myQueue).poll();
        ((MyQueue) myQueue).poll();
        System.out.println(myQueue.toString());
        System.out.println(((MyQueue) myQueue).peek());
        myQueue.clear();
        System.out.println(myQueue.toString());
    }

    private static void testingMyLinkedList() {
        MyList myLinkedList = new MyLinkedList();
        myLinkedList.add(0);
        myLinkedList.add(1);
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.add(6);
        myLinkedList.add(7);
        myLinkedList.add(8);
        myLinkedList.add(9);
        System.out.println(myLinkedList.toString());
        myLinkedList.remove(6);
        myLinkedList.remove(6);
        System.out.println(myLinkedList.toString());
        System.out.println(myLinkedList.get(5));
        myLinkedList.remove(6);
        myLinkedList.remove(6);
        System.out.println(myLinkedList.toString());
        myLinkedList.clear();
        System.out.println(myLinkedList.toString());
    }

    private static void testingMyArrayList() {
        MyList myArrayList = new MyArrayList();
        myArrayList.add(0);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);
        myArrayList.add(7);
        myArrayList.add(8);
        myArrayList.add(9);
        System.out.println(myArrayList.toString());
        myArrayList.remove(6);
        myArrayList.remove(6);
        System.out.println(myArrayList.toString());
        System.out.println(myArrayList.get(5));
        myArrayList.remove(6);
        myArrayList.remove(6);
        System.out.println(myArrayList.toString());
        myArrayList.clear();
        System.out.println(myArrayList.toString());
    }
}
