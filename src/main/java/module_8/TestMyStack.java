package module_8;

public class TestMyStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        myStack.push(9);
        System.out.println(myStack);
        myStack.pop();
        myStack.remove(6);
        myStack.remove(6);
        System.out.println(myStack);
        System.out.println(myStack.peek());
        myStack.pop();
        myStack.pop();
        System.out.println(myStack);
        System.out.println(myStack.peek());
        myStack.clear();
        System.out.println(myStack);
    }
}
