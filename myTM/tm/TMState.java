package tm;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TMState class represents a state in a Turing Machine.
 *
 * @author Pierce Rodriguez and Nolan Stetz
 */
public class TMState {
    protected int stateID; // ID of the state
    protected ArrayList<Integer> headWrite; // List of values to write on the tape head
    private final ArrayList<Integer> stateTrans; // List of next states
    private final ArrayList<Character> transDirections; // List of movement directions for the tape head

    /**
     * Constructor to initialize a TMState.
     *
     * @param id     The ID of the state.
     * @param length Length of the tape alphabet.
     */
    public TMState(int id, int length) {
        this.stateID = id;
        this.stateTrans = new ArrayList<>(Collections.nCopies(length + 1, null)); // Initialize with null values
        this.transDirections = new ArrayList<>(Collections.nCopies(length + 1, null)); // Initialize with null values
        this.headWrite = new ArrayList<>(Collections.nCopies(length + 1, null)); // Initialize with null values
    }

    /**
     * Get the movement direction for the tape head.
     *
     * @param input The input symbol index.
     * @return The movement direction ('L' for left, 'R' for right, 'N' for none).
     */
    public char getDirection(int input) {
        if (input < transDirections.size() && transDirections.get(input) != null) {
            return transDirections.get(input);
        }
        return 'N'; // 'N' for None or a similar default
    }

    /**
     * Add transition for the state.
     *
     * @param on    The input symbol.
     * @param to    The next state.
     * @param write The value to write on the tape.
     * @param move  The movement direction of the tape head.
     */
    public void addTransitions(int on, int to, int write, char move) {
        // Ensure the lists are large enough
        ensureCapacity(on);

        stateTrans.set(on, to);
        headWrite.set(on, write);
        transDirections.set(on, move);
    }

    /**
     * Get the value to write on the tape head.
     *
     * @param input The input symbol index.
     * @return The value to write.
     */
    public int getWrValue(int input) {
        if (input < headWrite.size() && headWrite.get(input) != null) {
            return headWrite.get(input);
        }
        return 0; // Default write value
    }

    /**
     * Get the next state for the given input symbol.
     *
     * @param input The input symbol index.
     * @return The next state ID.
     */
    public int getTrans(int input) {
        if (input < stateTrans.size() && stateTrans.get(input) != null) {
            return stateTrans.get(input);
        }
        return -1; //undefined transition
    }

    /**
     * Ensure the lists have enough capacity for the given index.
     *
     * @param index The index to ensure capacity for.
     */
    private void ensureCapacity(int index) {
        // Check if the index is greater than or equal to the current size of the lists
        if (index >= stateTrans.size()) {
            // Increase the capacity of the stateTrans, headwrite, and transDirections list to accommodate the index
            stateTrans.ensureCapacity(index + 1);
            headWrite.ensureCapacity(index + 1);
            transDirections.ensureCapacity(index + 1);
            // Fill the lists with null values from the current size to the desired index
            for (int i = stateTrans.size(); i <= index; i++) {
                stateTrans.add(null);
                headWrite.add(null);
                transDirections.add(null);
            }
        }
    }
}
