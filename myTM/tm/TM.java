package tm;

import java.util.LinkedList;


public class TM {
    private int numStates;
    private int to;
    private int write;
    private TMState tmState;

    private boolean done = false;

    LinkedList tape = new LinkedList<>();

    int head = 0;

    int current = head;
    int tapeIndex = 0;

    private int id = 0;
    private TMState state[];

    public TM(int states, int alph){
        numStates = states;
        state = new TMState[numStates];

        for (int i = 0; i < numStates; i++){
            state[i] = new TMState(i, alph);
        }
        //create new tape
        //create new states
    }


    public void addTransition(int from, int on, String str){
        String[] split = str.split(","); //split the string into segments
        to = Integer.parseInt(split[0]); //state to
        write = Integer.parseInt(split[1]); //write on state
        char move = split[2].charAt(0); //direction
        state[from].addTransitions(on,to,write,move);

        id++;
    }

    public void createTape(String readLine) {
        if (readLine != null && readLine.length() > 0){
            for (int i = 0; i < readLine.length(); i++){
                tape.add(Integer.parseInt(readLine.substring(i, i + 1)));
            }
        }
        System.out.println(tape);
    }

    public void simulate() {
        while (!done){
            if (tape.size() == 0 || tapeIndex < 0){
                if (tapeIndex == -1){
                    tape.push(0);
                    tapeIndex++;
                } else {
                    tape.add(tapeIndex, 0);
                }
            } else {
                Object input = read(tapeIndex);
               // tape.remove(input);
                tape.remove(tapeIndex);
                if (tapeIndex == tape.size()){
                    tape.add(state[current].getWriteValue(Integer.parseInt(input.toString())));
                } else {
                    write(tapeIndex, state[current].getWriteValue(Integer.parseInt(input.toString())));
                }
                if (state[current].getDirection(Integer.parseInt(input.toString())) == 'L') {
                    //System.out.println("LEFT");
                    current = state[current].getTransition(Integer.parseInt(input.toString()));
                   // System.out.println("Current: " + current);
                    tapeIndex--;
                    //System.out.println("Tape Index: " + tapeIndex);
                } else if (state[current].getDirection(Integer.parseInt(input.toString())) == 'R'){
                   // System.out.println("RIGHT");
                    current = state[current].getTransition(Integer.parseInt(input.toString()));
                    //System.out.println("Current: " + current);
                    tapeIndex++;
                    //System.out.println("Tape Index: " + tapeIndex);
                    if (tapeIndex == tape.size()){
                        tape.addLast(0);
                    }
                }
            }
            if (current == numStates - 1){
                done = true;
                System.out.println(current);
            }
            System.out.println(tape);
        }
    }

    public Object read(int data){
        return tape.get(data);
    }

    public void write (int index, int data){
        tape.add(index, data);
    }

}
