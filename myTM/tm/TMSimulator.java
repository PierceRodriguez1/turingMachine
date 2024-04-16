package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TMSimulator {

    static int states;             //Other classes will need this information
    static int alph;

    public static void main (String[] args) throws IOException {
        if (args.length != 1){ //must be a file that is passed via command line
            System.out.println("Must be Input file via command line!");
            System.exit(1);
        }

        BufferedReader buff = new BufferedReader(new FileReader(args[0]));


        states = Integer.parseInt(buff.readLine()); //first line, number of states
        alph = Integer.parseInt(buff.readLine()); //second line, alphabet starting from 1

        TM tm = new TM(states, alph); //creates a new turing machine passing in states and "alphabet"

        for (int i = 0; i < states - 1; i++){ //states(4) - 1 = {0, 1, 2, 3}
            for (int j = 0; j <= alph; j++){ //alph(1) = {0, 1} alphabet
                tm.addTransition(i, j, buff.readLine());
            }
        }

        tm.createTape(buff.readLine());

        buff.close();

        tm.simulate();
        int sum = 0;
        int i = 0;
        String output = "";

        while(i < tm.tape.size()){
            output += tm.tape.get(i);
            sum += Integer.parseInt(tm.tape.get(i).toString());
            i++;
        }
        System.out.println("Output: " + output);
        System.out.println("Output Length: " + tm.tape.size());
        System.out.println("Sum of Symbols: " + sum);
    }
}
