package tm;


public class TMState {
    private int stateNumber;
    int id;

    private int[] transition;
    private char[] directions;

    private int[] write;

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
}