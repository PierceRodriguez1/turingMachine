package tm;

import java.util.ArrayList;
import java.util.Collections;

public class TMState {
    //comment code and this class should be good. add good javadocs and inline
    protected int stateID;
    protected ArrayList<Integer> headWrite;
    private final ArrayList<Integer> stateTrans;
    private final ArrayList<Character> transDirections;

    public TMState(int id, int length) {
        this.stateID = id;
        this.stateTrans = new ArrayList<>(Collections.nCopies(length + 1, null));
        this.transDirections = new ArrayList<>(Collections.nCopies(length + 1, null));
        this.headWrite = new ArrayList<>(Collections.nCopies(length + 1, null));
    }

    public char getDirection(int input) {
        if (input < transDirections.size() && transDirections.get(input) != null) {
            return transDirections.get(input);
        }
        return 'N';  // 'N' for None or a similar default
    }

    public void addTransitions(int on, int to, int write, char move) {
        // Ensure the lists are large enough
        ensureCapacity(on);

        stateTrans.set(on, to);
        headWrite.set(on, write);
        transDirections.set(on, move);
    }

    public int getWrValue(int input) {
        if (input < headWrite.size() && headWrite.get(input) != null) {
            return headWrite.get(input);
        }
        return 0;  // Default write value, consider your machine's context
    }

    public int getTrans(int input) {
        if (input < stateTrans.size() && stateTrans.get(input) != null) {
            return stateTrans.get(input);
        }
        return -1;  // Indicate an undefined transition, which could be used to halt the TM
    }

    private void ensureCapacity(int index) {
        if (index >= stateTrans.size()) {
            stateTrans.ensureCapacity(index + 1);
            headWrite.ensureCapacity(index + 1);
            transDirections.ensureCapacity(index + 1);

            for (int i = stateTrans.size(); i <= index; i++) {
                stateTrans.add(null);
                headWrite.add(null);
                transDirections.add(null);
            }
        }
    }
}
