package tm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * TMSimulator class represents a Turing Machine simulator.
 *
 * @author Pierce Rodriguez and Nolan Stetz
 */
public class TMSimulator {
    private static int states; // states in the Turing Machine
    private static int alph; // alphabet in the Turing Machine

    /**
     * Main method to execute the Turing Machine simulation.
     *
     * @param args Command-line arguments. Expects the path to the input file containing the Turing Machine configuration.
     */
    public static void main(String[] args) {
        try {
            // Check if the correct number of arguments is provided
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

    /**
     * Initializes the simulation with the given input file.
     *
     * @param filePath Path to the input file containing the Turing Machine configuration.
     * @throws IOException If an I/O error occurs while reading the input file.
     */
    private static void initializeSimulation(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to extract states and alphabets
                // the first line contains states and the second contains alphabets
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

            // Read transitions for each state and alphabet
            for (int i = 0; i < states - 1; i++) {
                for (int j = 0; j <= alph; j++) {
                    machine.addTransition(i, j, reader.readLine());
                }
            }

            // Set the initial tape configuration
            machine.makeTape(reader.readLine());

            reader.close();

            // Run the simulation
            machine.sim();

            // Print the output
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
