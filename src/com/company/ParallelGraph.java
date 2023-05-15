package com.company;

import java.io.PrintWriter;
import java.util.LinkedList;

public class ParallelGraph {
    private int V;
    private LinkedList<Integer>[] adjList;

    ParallelGraph(int v) {
        V = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adjList[v].add(w);
        adjList[w].add(v);
    }

    void printVisitedVerticles(int v, boolean[] visited, PrintWriter out) {
        visited[v] = true;
        out.print(v + " ");

        for (int n : adjList[v]) {
            if (!visited[n]) {
                printVisitedVerticles(n, visited, out);
            }
        }
    }

    void connectedComponents(int numThreads, PrintWriter out) throws InterruptedException {
        boolean[] visited = new boolean[V];
        Thread[] threads = new Thread[numThreads];

        int start = 0;
        int end = V / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                for (int v = start + threadIndex * end; v < start + (threadIndex + 1) * end; v++) {
                    if (!visited[v]) {
                        printVisitedVerticles(v, visited, out);
                        out.println();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
