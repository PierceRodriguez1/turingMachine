package tm;

import java.util.ArrayList;
import java.util.Collections;

public class TMState {
    protected int id;
    protected ArrayList<Integer> writes;
    private final ArrayList<Integer> transitions;
    private final ArrayList<Character> directions;

    public TMState(int id, int length) {
        this.id = id;
        this.transitions = new ArrayList<>(Collections.nCopies(length + 1, null));
        this.directions = new ArrayList<>(Collections.nCopies(length + 1, null));
        this.writes = new ArrayList<>(Collections.nCopies(length + 1, null));
    }

    public Object getDirection(int input) {
        return directions.get(input);
    }

    public void addTransitions(int on, int to, int write, char move) {
        // Ensure the lists are large enough
        ensureCapacity(on);

        transitions.set(on, to);
        writes.set(on, write);
        directions.set(on, move);
    }

    public int getWriteValue(int i) {
        return writes.get(i);
    }

    public int getTransition(int i) {
        return transitions.get(i);
    }

    private void ensureCapacity(int index) {
        if (index >= transitions.size()) {
            transitions.ensureCapacity(index + 1);
            writes.ensureCapacity(index + 1);
            directions.ensureCapacity(index + 1);

            for (int i = transitions.size(); i <= index; i++) {
                transitions.add(null);
                writes.add(null);
                directions.add(null);
            }
        }
    }
}
