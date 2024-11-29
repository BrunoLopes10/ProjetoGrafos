package algorithms;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] files = {"NY.txt", "BAY.txt", "COL.txt"}; // Substitua pelos nomes corretos dos arquivos
        try (PrintWriter writer = new PrintWriter(new FileWriter("resultados.txt"))) {
            for (String file : files) {
                System.out.println("\nProcessando arquivo: " + file);
                writer.println("\nResultados para " + file + ":");

                try {
                    // Ler grafo do arquivo
                    Graph graph = readGraphFromFile(file);

                    int n = graph.getVertices();
                    int m = graph.getEdges().size();
                    System.out.println("Grafo: n = " + n + ", m = " + m);
                    writer.println("Grafo: n = " + n + ", m = " + m);

                    // **Caminho Mínimo (Dijkstra)**
                    System.out.println("Executando Caminho Mínimo (Dijkstra)...");
                    long startTime = System.nanoTime();
                    int dijkstraCost = Dijkstra.shortestPathCost(graph, 0, n - 1);
                    long dijkstraTime = System.nanoTime() - startTime;
                    writer.printf("Custo CM: %d, Tempo (s): %.5f\n", dijkstraCost, dijkstraTime / 1e9);

                    // **Árvore Geradora Mínima (Prim)**
                    System.out.println("Executando Árvore Geradora Mínima (Prim)...");
                    startTime = System.nanoTime();
                    int primCost = Prim.minimumSpanningTree(graph);
                    long primTime = System.nanoTime() - startTime;
                    writer.printf("Custo AGM: %d, Tempo (s): %.5f\n", primCost, primTime / 1e9);

                    // **Fluxo Máximo (Edmonds-Karp)**
                    System.out.println("Executando Fluxo Máximo (Edmonds-Karp)...");
                    int[][] capacityMatrix = convertToCapacityMatrix(graph);
                    startTime = System.nanoTime();
                    int maxFlow = MaxFlow.edmondsKarp(capacityMatrix, 0, n - 1);
                    long maxFlowTime = System.nanoTime() - startTime;
                    writer.printf("Fluxo Máximo: %d, Tempo (s): %.5f\n", maxFlow, maxFlowTime / 1e9);
                } catch (FileNotFoundException e) {
                    System.err.println("Erro: Arquivo " + file + " não encontrado.");
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro no arquivo " + file + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao gravar o arquivo de resultados: " + e.getMessage());
        }
    }

    private static Graph readGraphFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Graph graph = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("c")) {
                    continue; // Ignorar comentários
                }
                if (line.startsWith("p")) {
                    String[] parts = line.split(" ");
                    int vertices = Integer.parseInt(parts[2]);
                    graph = new Graph(vertices);
                }
                if (line.startsWith("a")) {
                    if (graph == null) {
                        throw new IllegalArgumentException("Definição de vértices ausente antes das arestas.");
                    }
                    String[] parts = line.split(" ");
                    int src = Integer.parseInt(parts[1]) - 1; // Ajustar para índice 0
                    int dest = Integer.parseInt(parts[2]) - 1;
                    int weight = Integer.parseInt(parts[3]);
                    graph.addEdge(src, dest, weight);
                }
            }

            if (graph == null) {
                throw new IllegalArgumentException("Formato inválido. Definição de vértices não encontrada.");
            }

            return graph;
        }
    }

    private static int[][] convertToCapacityMatrix(Graph graph) {
        int vertices = graph.getVertices();
        int[][] capacity = new int[vertices][vertices];

        for (Graph.Edge edge : graph.getEdges()) {
            capacity[edge.src][edge.dest] = edge.weight;
        }

        return capacity;
    }
}
