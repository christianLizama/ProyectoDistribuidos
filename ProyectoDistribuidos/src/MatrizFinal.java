public class MatrizFinal {

    int matrizInicial[][];
    int MatrizFinal[][];

    public MatrizFinal(int matriz[][]) {
        this.matrizInicial = matriz;
    }

    public int[][] getMatriz() {
        return matrizInicial;
    }
    
    public void setMatrizFinal(int[][] matrizFinal) {
        MatrizFinal = matrizFinal;
    }

    public void setMatriz(int[][] matriz) {
        this.matrizInicial = matriz;
    }

    public void imprimirMatriz(int matriz[][]){
        for (int x=0; x < matriz.length; x++) {
            System.out.print("|");
            for (int y=0; y < matriz[x].length; y++) {
              System.out.print (matriz[x][y]);
              if (y!=matriz[x].length-1) System.out.print("\t");
            }
            System.out.println("|");
        }    
    }
}
