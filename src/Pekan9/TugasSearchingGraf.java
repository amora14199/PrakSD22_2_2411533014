package Pekan9;
//Sasya Zamora (2411533014)
//

import java.util.*;

public class TugasSearchingGraf {
    private Map<String, List<String>> graph;

    public TugasSearchingGraf() {
        graph = new HashMap<>();

        // Inisialisasi graf tak berarah
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F"));
        graph.put("D", Arrays.asList("B"));
        graph.put("E", Arrays.asList("B", "F"));
        graph.put("F", Arrays.asList("C", "E", "G"));
        graph.put("G", Arrays.asList("F"));
        // Tambahkan node kosong untuk yang belum punya tetangga
        graph.putIfAbsent("H", new ArrayList<>());
    }

    public void search(String startNode, String goalNode) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        Map<String, String> parent = new HashMap<>();
        int langkah = 1;

        stack.push(startNode);
        visited.add(startNode);

        System.out.println("Langkah " + langkah++ + ": Kunjungi " + startNode);

        while (!stack.isEmpty()) {
            String current = stack.peek();

            boolean foundNext = false;
            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);

                    System.out.println("Langkah " + langkah++ + ": Kunjungi " + neighbor);

                    if (neighbor.equals(goalNode)) {
                        System.out.println("Tujuan " + goalNode + " ditemukan.");
                        printRoute(parent, startNode, goalNode);
                        return;
                    }

                    foundNext = true;
                    break;
                }
            }

            if (!foundNext) {
                stack.pop();
            }
        }

        System.out.println("Node tujuan tidak ditemukan.");
    }

    private void printRoute(Map<String, String> parent, String start, String goal) {
        List<String> path = new ArrayList<>();
        String current = goal;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        System.out.println("Rute: " + String.join(" → ", path));
    }

    public static void main(String[] args) {
        TugasSearchingGraf graphSearch = new TugasSearchingGraf();
        
        System.out.println("Nama: [Nama Mahasiswa]");
        System.out.println("NIM: [NIM Mahasiswa]");
        System.out.println("Node awal: A");
        System.out.println("Node tujuan: G");
        System.out.println("Algoritma: DFS\n");

        graphSearch.search("A", "G");
    }
}