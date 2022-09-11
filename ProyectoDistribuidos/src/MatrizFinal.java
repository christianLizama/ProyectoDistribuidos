import java.util.Arrays;

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
    public void mezclar(int matrizHilo[][],int parte){
        //imprimirMatriz(matrizHilo);
        //System.out.println("--------");
        if(parte==1){
            //parte arriba
            for (int i = 1; i < MatrizFinal.length/2; i++) {
                for (int j = 0; j < MatrizFinal[0].length; j++) {
                    MatrizFinal[i][j]=matrizHilo[i][j];
                }
            }
        }
        else{
            //parte abajo
            //System.out.println("------");
            //System.out.println(Arrays.toString(MatrizFinal[(MatrizFinal.length/2)]));
            //System.out.println("------");
            for (int i = 0; i < matrizHilo.length-1; i++) {
                //System.out.println("xddd");
                for (int j = 0; j < matrizHilo[0].length; j++) {
                    MatrizFinal[i+MatrizFinal.length/2][j]=matrizHilo[i+1][j];
                }
            }
        }
    }
}
