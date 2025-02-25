import java.util.*;
import logica.SquareRootTask;

public class SquareRootModulo {
    public static void main(String[] args) {
        int modulo = 59; 
        int numHilos = 4;
        Map<Integer, List<Integer>> raizCuadrada = new HashMap<>(); 
        Thread[] hilos = new Thread[numHilos];

        long startTime = System.currentTimeMillis(); // Captura el tiempo de inicio

        int rango = modulo / numHilos; 
        for (int i = 0; i < numHilos; i++) {
            int inicio = 1 + i * rango;
            int fin = (i == numHilos - 1) ? modulo - 1 : inicio + rango - 1; 
            hilos[i] = new Thread(new SquareRootTask(inicio, fin, modulo, raizCuadrada));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis(); // Captura el tiempo de finalización

        for (Map.Entry<Integer, List<Integer>> entry : raizCuadrada.entrySet()) { 
            System.out.println(entry.getKey() + " : " + entry.getValue() + " (mod " + modulo + ")");
        }

        long duration = endTime - startTime; // Calcula la duración
        System.out.println("Tiempo de ejecución: " + duration + " milisegundos");
    }
}