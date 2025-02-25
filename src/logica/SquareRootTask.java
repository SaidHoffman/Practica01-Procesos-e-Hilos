package logica;

import java.util.*; 

public class SquareRootTask implements Runnable {
    private int inicio, fin, modulo;
    private Map<Integer, List<Integer>> raizCuadrada;

    public SquareRootTask(int inicio, int fin, int modulo, Map<Integer, List<Integer>> raizCuadrada) {
        this.inicio = inicio;
        this.fin = fin;
        this.modulo = modulo;
        this.raizCuadrada = raizCuadrada;
    }

    @Override
    public void run() {
        for (int x = inicio; x <= fin; x++) {
            int residuo = (x * x) % modulo;
            synchronized (raizCuadrada) {
                raizCuadrada.computeIfAbsent(residuo, k -> new ArrayList<>()).add(x); 
            }
        }
    }
}
