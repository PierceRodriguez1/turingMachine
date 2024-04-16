package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TMSimulator {

    private static int states;
    private static int alph;

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java TMSimulator <inputFilePath>");
                System.exit(1);
            }

            String filePath = args[0];
            initializeSimulation(filePath);
        } catch (Exception e) {
            System.out.println("Error during simulation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void initializeSimulation(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to extract states and alphabets
                // For simplicity, assuming the first line contains states and the second contains alphabets
                if (states == 0) {
                    states = Integer.parseInt(line);
                    continue;
                }
                if (alph == 0) {
                    alph = Integer.parseInt(line);
                    break;
                }
            }

            // Setup TM machine with parsed values
            TM tm = new TM(states, alph);
            for (int i = 0; i < states - 1; i++){ //states(4) - 1 = {0, 1, 2, 3}
            for (int j = 0; j <= alph; j++){ //alph(1) = {0, 1} alphabet
                tm.addTransition(i, j, reader.readLine());
            }
        }

        tm.createTape(reader.readLine());

        reader.close();

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
}
