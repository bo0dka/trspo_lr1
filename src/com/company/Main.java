package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static long startTime = System.currentTimeMillis();
    public static void main(String[] args) {
        try {
            File inputFile = new File("graph.txt");
            Scanner scanner = new Scanner(inputFile);

            int numVertices = scanner.nextInt();
            ParallelGraph g = new ParallelGraph(numVertices);

            while (scanner.hasNextInt()) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                g.addEdge(v, w);
            }

            File outputFile = new File("output.txt");
            PrintWriter writer = new PrintWriter(outputFile);

            int numThreads = 4;
            writer.println("Connected Components:");
            g.connectedComponents(numThreads, writer);
            writer.close();

        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total execution time: " + totalTime + " milliseconds");
    }
}
