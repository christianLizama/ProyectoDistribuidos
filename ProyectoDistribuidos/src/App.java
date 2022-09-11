import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void generarPgm(String nombreArchivo,int picWidth,int picHeight,int maxvalue, MatrizFinal matriz){
        try{
            File myFile = new File(nombreArchivo);
            String filePath = myFile.getCanonicalPath();
            //specify the name of the output..
            FileWriter fstream = new FileWriter(filePath);
            //we create a new BufferedWriter
            BufferedWriter out = new BufferedWriter(fstream);
            //we add the header, 128 128 is the width-height and 63 is the max value-1 of ur data
            out.write("P2\n# CREATOR: XV Version 3.10a  Rev: 12/29/94\n"+picWidth+" "+picHeight+"\n"+maxvalue+"\n");
            //2 loops to read the 2d array
            for(int i = 0 ; i<matriz.getMatrizFinal().length;i++)
               for(int j = 0 ; j<matriz.getMatrizFinal()[0].length;j++)
                   //we write in the output the value in the position ij of the array
                   out.write(matriz.getMatrizFinal()[i][j]+" ");
            //we close the bufferedwritter
            out.close();
            }
        catch (Exception e){
                System.err.println("Error : " + e.getMessage());
        }
    }
    public static void main(String[] args) throws Exception {
        File myFile = new File("imgNueva.pgm");
        String filePath = myFile.getCanonicalPath();

        //String filePath = "../src/imgNueva.pgm";
        FileInputStream fileInputStream= new FileInputStream(filePath);
        Scanner scan = new Scanner(fileInputStream);
        // Discard the magic number
        scan.nextLine();
        // Discard the comment line
        scan.nextLine();
        // Read pic width, height and max value
        int picWidth = scan.nextInt();
        int picHeight = scan.nextInt();
        int maxvalue = scan.nextInt();

        fileInputStream.close();
        scan.close();

        // Now parse the file as binary data
        fileInputStream = new FileInputStream(filePath);
        DataInputStream dis = new DataInputStream(fileInputStream);

        // look for 4 lines (i.e.: the header) and discard them
        int numnewlines = 4;
        while (numnewlines > 0) {
            char c;
            do {
                c = (char)(dis.readUnsignedByte());
            } while (c != '\n');
            numnewlines--;
        }

        // read the image data
        int[][] data2D = new int[picHeight][picWidth];
        for (int row = 0; row < picHeight; row++) {
            for (int col = 0; col < picWidth; col++) {
                data2D[row][col] = dis.readUnsignedByte();
                //System.out.print(data2D[row][col] + " ");
            }
            //System.out.println();
        }
        dis.close();

        
        int[][] original = data2D;

        int splitSize = original.length / 2;

        int[][] splitArrayPart1 = Arrays.copyOfRange(original,0, splitSize+1);
        int[][] splitArrayPart2 = Arrays.copyOfRange(original, splitSize-1, original.length);
        
       
        
        // int[][] original = {{5, 1,  2,  4},
        //                     {6, 7,  8,  9},
        //                     {1, 13, 66, 4},
        //                     {6, 77, 87, 9},
        //                     {4, 33, 12, 45}
        //                     };

        // int splitSize = original.length / 2;
        
        // int[][] splitArrayPart1 = Arrays.copyOfRange(original,0, splitSize+1);
        // int[][] splitArrayPart2 = Arrays.copyOfRange(original, splitSize-1, original.length);
   
        
        // System.out.println("parte 1");
        // for(int i=0;i<splitArrayPart1.length;i++){
        //     for (int j = 0; j < splitArrayPart1[0].length; j++) {
        //         System.out.print(splitArrayPart1[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("");
        // System.out.println("");

        // System.out.println("parte 2");
        // for(int i=0;i<splitArrayPart2.length;i++){
        //     for (int j = 0; j < splitArrayPart2[0].length; j++) {
        //         System.out.print(splitArrayPart2[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println("");
        // System.out.println("");
        // System.out.println("");
        // System.out.println("");

        //Hilos dilatacion
        MatrizFinal matriz = new MatrizFinal(original);
        Hilo miHilo = new Hilo(splitArrayPart1,matriz,1,1,0);
        Hilo miHilo2 = new Hilo(splitArrayPart2,matriz,0,1,0);
        //Hilos erosion
        MatrizFinal matriz2 = new MatrizFinal(original);
        Hilo miHilo3 = new Hilo(splitArrayPart1,matriz2,1,0,5);
        Hilo miHilo4 = new Hilo(splitArrayPart2,matriz2,0,0,5);
        try {
            miHilo.start();
            miHilo2.start();
            miHilo3.start();
            miHilo4.start();
            miHilo.join();
            miHilo2.join();
            miHilo3.join();
            miHilo4.join();
        } catch (InterruptedException ex) {
        }
        //matriz.imprimirMatriz(matriz.getMatrizFinal());
        generarPgm("dilatacion.pgm",picWidth,picHeight,maxvalue,matriz);
        generarPgm("erosion.pgm",picWidth,picHeight,maxvalue,matriz2);
        
    }
}
