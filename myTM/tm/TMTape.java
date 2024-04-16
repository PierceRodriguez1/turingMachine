package tm;

import java.util.LinkedList;

public class TMTape {
    private LinkedList<Integer> tape;
    private int head;

    public TMTape() {
        tape = new LinkedList<>();
        head = 0;
    }

    public int read() {
        return tape.get(head);
    }

    public void write(int symbol) {
        tape.set(head, symbol);
    }

    public void moveLeft() {
        head--;
        if (head < 0) {
            tape.addFirst(0);           // Add a blank symbol to the left if needed
            head = 0;
        }
    }

    public void moveRight() {
        head++;
        if (head >= tape.size()) {
            tape.addLast(0);        // Add a blank symbol to the right if needed
        }
    }

    public void print() {
        for (int symbol : tape) {
            System.out.print(symbol);
        }
        System.out.println();
    }
}
