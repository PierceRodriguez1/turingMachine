package tm;
import java.util.ArrayList;
import java.util.List;

/**
 * TM class represents a Turing Machine.
 * @author Pierce Rodriguez and Nolan Stetz
 */
public class TM {
    protected final List<Integer> actualTape = new ArrayList<>(); // The tape of the Turing Machine
    private final List<TMState> currState; // List of current states
    private final int numStates; // Total number of states in the Turing Machine
    private int currPos = 0; // Current position/state in the Turing Machine
    private int tapeIndex = 0; // Current index on the tape
    private boolean finished = false; // Flag indicating whether the TM simulation has finished

    /**
     * Constructor to initialize a Turing Machine with the given number of states and alphabet size.
     * @param states Number of states in the Turing Machine.
     * @param alph Alphabet size.
     */
    public TM(int states, int alph) {
        numStates = states;
        currState = new ArrayList<>(numStates);

        for (int i = 0; i < numStates; i++) {
            currState.add(new TMState(i, alph)); // Create a new TMState for each state
        }
    }

    /**
     * Adds a transition to the current state.
     * @param from Starting state.
     * @param on Input symbol index.
     * @param str String representing the transition (toState, writeValue, direction).
     */
    public void addTransition(int from, int on, String str) {
        String[] split = str.split(",");
        int to = Integer.parseInt(split[0]);
        int write = Integer.parseInt(split[1]);
        char move = split[2].charAt(0);
        currState.get(from).addTransitions(on, to, write, move); // Add the transition to the corresponding TMState
    }

    /**
     * Initializes the tape with the given input string.
     * @param readLine Input string representing the initial tape content.
     */
    public void makeTape(String readLine) {
        if (readLine != null && !readLine.isEmpty()) {
            readLine.chars().map(Character::getNumericValue).forEach(actualTape::add); // Convert each character to an integer and add it to the tape
        }
        System.out.println(actualTape); // Print the initial tape content
    }

    /**
     * Simulates the Turing Machine.
     */
    public void sim() {
        if (actualTape.isEmpty()) {
            actualTape.add(0); // Add a default value to the tape if it's empty
        }

        while (!finished && currPos != -1) {
            processTransition(); // Process the current transition
            if (currPos == numStates - 1) {
                finished = true;
                System.out.println("Machine terminated at currState " + currPos); // Print a message if the simulation has finished
            }
        }
        System.out.println("Final Tape Content: " + actualTape); // Print the final tape content
    }

    /**
     * Processes a transition based on the current state and input symbol.
     */
    private void processTransition() {
        int input = actualTape.get(tapeIndex); // Get the input symbol from the tape
        int writeValue = currState.get(currPos).getWrValue(input); // Get the value to write on the tape
        char direction = currState.get(currPos).getDirection(input); // Get the movement direction for the tape head
        int nextState = currState.get(currPos).getTrans(input); // Get the next state based on the input symbol
        actualTape.set(tapeIndex, writeValue); // Write the value on the tape
        currPos = nextState; // Update the current state
        moveTape(direction); // Move the tape head based on the direction
    }

    /**
     * Moves the tape head based on the given direction.
     * @param direction The direction to move the tape head ('L' for left, 'R' for right).
     */
    private void moveTape(char direction) {
        if (direction == 'L') {
            tapeIndex--; // Move the tape head to the left
            if (tapeIndex < 0) {
                actualTape.add(0, 0); // Add a default value (0) to the beginning of the tape if the tape head moves beyond the tape
                tapeIndex = 0; // Update the tape index to the beginning of the tape
            }
        } else if (direction == 'R') {
            tapeIndex++; // Move the tape head to the right
            if (tapeIndex >= actualTape.size()) {
                actualTape.add(0); // Add a default value (0) to the end of the tape if the tape head moves beyond the tape
            }
        }
    }
}

