package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//comment code and this class should be good. add good javadocs and inline
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
            TM machine = new TM(states, alph);
            for (int i = 0; i < states - 1; i++) { //states(4) - 1 = {0, 1, 2, 3}
                for (int j = 0; j <= alph; j++) { //alph(1) = {0, 1} alphabet
                    machine.addTransition(i, j, reader.readLine());
                }
            }

            machine.makeTape(reader.readLine());

            reader.close();

            machine.sim();
            int sum = 0;
            int i = 0;
            String output = "";

            while (i < machine.actualTape.size()) {
                output += machine.actualTape.get(i);
                sum += Integer.parseInt(machine.actualTape.get(i).toString());
                i++;
            }
            System.out.println("output: " + output);
            System.out.println("output length: " + machine.actualTape.size());
            System.out.println("sum of symbols: " + sum);
        }
    }
}
