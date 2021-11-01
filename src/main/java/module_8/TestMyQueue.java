package module_8;

public class TestMyQueue {
    public static void main(String[] args) {
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
        System.out.println(myQueue);
        myQueue.remove(6);
        myQueue.remove(6);
        System.out.println(myQueue);
        System.out.println(((MyQueue) myQueue).peek());
        ((MyQueue) myQueue).poll();
        ((MyQueue) myQueue).poll();
        System.out.println(myQueue);
        System.out.println(((MyQueue) myQueue).peek());
        myQueue.clear();
        System.out.println(myQueue);
    }
}
