
public class DilatacionErosion {
    
    public int[][] matriz;

    public DilatacionErosion(int[][] matriz) {
        this.matriz = matriz;
    }

    public int[][] erosion() {

        int fila=matriz.length;
        int colu=matriz[0].length;
        int[][] otra = new int[fila][colu];
        int i,j;

        for(i=1; i<fila-1; i++){
            for(j=1; j<colu-1; j++){
                int min =255;
                int k[]= new int[5];
                k[0] = matriz[i][j-1];
                k[1] = matriz[i-1][j];
                k[2] = matriz[i][j];
                k[3] = matriz[i][j+1];
                k[4] = matriz[i+1][j];
                int l;
                for(l=0;l<5;l++){
                    if(k[l]<min){
                        min = k[l];
                    }
                }
                otra[i][j]=min;
            }
        }
        return otra;
    }

    public int[][] dilatacion() {
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
                int max =0;
                int k[]= new int[5];
                k[0] = matriz[i][j-1];
                k[1] = matriz[i-1][j];
                k[2] = matriz[i][j];
                k[3] = matriz[i][j+1];
                k[4] = matriz[i+1][j];
                for(int l=0;l<5;l++){
                    if(k[l]>max){
                        max = k[l];
                    }
                }
                otra[i][j]=max;
            }
        }
        return otra;
    }
}
