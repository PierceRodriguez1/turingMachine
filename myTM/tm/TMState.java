package tm;
import java.util.HashMap;
import java.util.Map;

public class TMState {
    private int stateNumber;
   // private Map<Integer, Transition> transitions;

    int id;

    private int[] transition;
    private char[] directions;

    private int[] write;


//    public TMState(int stateNumber) {
//        this.stateNumber = stateNumber;
//        this.transitions = new HashMap<>();
//    }

    public TMState(int id, int length) {
        this.id = id;
        transition = new int[length + 1];
        directions = new char[length + 1];
        write = new int[length + 1];
    }

    public int getTransition(int stateIndex){
        return transition[stateIndex];
    }

    public char getDirection(int index) {
        return directions[index];
    }

    public int getWriteValue(int index) {
        int value = write[index];
        return value;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public void addTransitions(int on, int to, int data, char direction) {
        transition[on] = to;
        write[on] = data;
        directions[on] = direction;
    }

//    public void addTransition(int symbol, Transition transition) {
//        transitions.put(symbol, transition);
//    }

//    public Transition getTransition(int symbol) {
//        return transitions.get(symbol);
//    }
//
//
//
//    /**
//     * ZACK IMPORTANT NOTE
//     *
//     * This is a static class. I am not sure how much or if youve ever
//     * used this before. It is just another class that is within this
//     * class instead of making a whole new class. This also means that
//     * only TMState can use the functionality this Transition class.
//     */
//    public static class Transition {
//        private int nextState;
//        private int writeSymbol;
//        private Direction moveDirection;
//
//        public Transition(int nextState, int writeSymbol, Direction moveDirection) {
//            this.nextState = nextState;
//            this.writeSymbol = writeSymbol;
//            this.moveDirection = moveDirection;
//        }
//
//        public int getNextState() {
//            return nextState;
//        }
//
//        public int getWriteSymbol() {
//            return writeSymbol;
//        }
//
//        public Direction getMoveDirection() {
//            return moveDirection;
//        }
//    }
//
//    /**
//     * Same deal as the static cluss but just an enum
//     */
//    public static enum Direction {
//        LEFT, RIGHT
//    }
//}
}