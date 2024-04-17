package tm;

import java.util.ArrayList;
import java.util.List;
//comment code and this class should be good. add good javadocs and inline
public class TM {
    protected final List<Integer> actualTape = new ArrayList<>();
    private final List<TMState> currState;
    private final int numStates;
    private int currPos = 0;
    private int tapeIndex = 0;
    private boolean finished = false;

    public TM(int states, int alph) {
        numStates = states;
        currState = new ArrayList<>(numStates);

        for (int i = 0; i < numStates; i++) {
            currState.add(new TMState(i, alph));
        }
    }

    public void addTransition(int from, int on, String str) {
        String[] split = str.split(",");
        int to = Integer.parseInt(split[0]);
        int write = Integer.parseInt(split[1]);
        char move = split[2].charAt(0);
        currState.get(from).addTransitions(on, to, write, move);
    }

    public void makeTape(String readLine) {
        if (readLine != null && !readLine.isEmpty()) {
            readLine.chars().map(Character::getNumericValue).forEach(actualTape::add);
        }
        System.out.println(actualTape);
    }

    public void sim() {
        if (actualTape.isEmpty()) {
            actualTape.add(0);
        }

        while (!finished && currPos != -1) {
            processTransition();

            if (currPos == numStates - 1) {
                finished = true;
                System.out.println("Machine terminated at currState " + currPos);
            }
        }
        System.out.println("Final Tape Content: " + actualTape);
    }

    private void processTransition() {
        int input = actualTape.get(tapeIndex);
        int writeValue = currState.get(currPos).getWrValue(input);
        char direction = currState.get(currPos).getDirection(input);
        int nextState = currState.get(currPos).getTrans(input);

        actualTape.set(tapeIndex, writeValue);

        currPos = nextState;
        moveTape(direction);
    }

    private void moveTape(char direction) {
        if (direction == 'L') {
            tapeIndex--;
            if (tapeIndex < 0) {
                actualTape.add(0, 0);
                tapeIndex = 0;
            }
        } else if (direction == 'R') {
            tapeIndex++;
            if (tapeIndex >= actualTape.size()) {
                actualTape.add(0);
            }
        }
    }
}

