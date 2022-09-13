public class MatrizFinal {

    int matrizInicial[][];
    int MatrizFinal[][];

    public MatrizFinal(int matriz[][]) {
        this.matrizInicial = matriz;
        MatrizFinal = new int[matrizInicial.length][matrizInicial[0].length];
        for (int i = 0; i < matrizInicial.length; i++) {
            for (int j = 0; j < matrizInicial[0].length; j++) {
                MatrizFinal[i][j]=matrizInicial[i][j];
            }
        }
    }

    public int[][] getMatriz() {
        return matrizInicial;
    }
    public int[][] getMatrizFinal() {
        return MatrizFinal;
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
    public synchronized void mezclar(int matrizHilo[][],int inicio, int termino){
        
        //imprimirMatriz(matrizHilo);
        //System.out.println("--------");
        //System.out.println(inicio +" " +termino);
        int k=0;
        int l=0;
        for (int i = inicio; i < termino; i++) {
            //System.out.println("pase" + i);
            for (int j = 1; j < MatrizFinal[0].length-1; j++) {
                MatrizFinal[i][j]=matrizHilo[k][l];
                l++;
            }
            l=0;
            k++;
        }
        
    }
}
