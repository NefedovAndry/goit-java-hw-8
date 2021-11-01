package module_8;

public class TestMyStack {
    public static void main(String[] args) {
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
        System.out.println(myStack);
        myStack.remove(6);
        myStack.remove(6);
        System.out.println(myStack);
        System.out.println(((MyStack) myStack).peek());
        ((MyStack) myStack).poll();
        ((MyStack) myStack).poll();
        System.out.println(myStack);
        System.out.println(((MyStack) myStack).peek());
        myStack.clear();
        System.out.println(myStack);
    }
}
