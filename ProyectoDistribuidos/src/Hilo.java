
public class Hilo extends Thread{

    int matriz[][];
    MatrizFinal matrizF;
    int parte;

    public Hilo(int matriz[][], MatrizFinal matrizF, int parte) {
        this.matriz = matriz;
        this.matrizF = matrizF;
        this.parte = parte;
    }

    public void erosion(){
        int fila=matriz.length;
        int colu=matriz[0].length;
        int[][] otra = new int[fila][colu];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                otra[i][j]=matriz[i][j];
            }
        }
        
        for(int i=1; i<fila-1; i++){
            for(int j=1; j<colu-1; j++){
                int min =255;
                int k[]= new int[5];
                k[0] = matriz[i][j-1];
                k[1] = matriz[i-1][j];
                k[2] = matriz[i][j];
                k[3] = matriz[i][j+1];
                k[4] = matriz[i+1][j];
                for(int l=0;l<5;l++){
                    if(k[l]<min){
                        min = k[l];
                    }
                }
                otra[i][j]=min;
            }
        }
        System.out.println("termine...");
        matrizF.mezclar(otra, parte);
    }

    public void dilatacion(){
        
    }

    public void run(){
        erosion();
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
        }
    }
}
