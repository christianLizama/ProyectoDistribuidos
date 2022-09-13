import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    //funcion de generar un archivo pgm extraida de internet y modificada para su uso en el proyecto
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
    public static void imprimirMenu() {
        System.out.println("Elementos estructurantes:");
        System.out.println("     _                                       _   _ ");
        System.out.println("1) _|_|_    2)  _ _  3)  _ _ _   4)  _   5) |_|_|_|");
        System.out.println("  |_|_|_|      |_|_|    |_|_|_|     |_|      _|_|_ ");
        System.out.println("    |_|          |_|                |_|     |_| |_|");
        System.out.println();
    }
    public static void main(String[] args) throws Exception {
        LecturaEscritura lecturaYescritura = new LecturaEscritura();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Leyendo datos usando readLine
        System.out.print("ingrese nombre del archivo: ");
        String nombreArchivo = reader.readLine();
        lecturaYescritura.leer(nombreArchivo);
        
        int[][] original = lecturaYescritura.getMatriz2d();

        int splitSize = original.length / 2;

        int[][] splitArrayPart1 = Arrays.copyOfRange(original,0, splitSize+1);
        int[][] splitArrayPart2 = Arrays.copyOfRange(original, splitSize-1, original.length);
        Scanner sc = new Scanner(System.in);
        imprimirMenu();
        boolean valido=true;
        int opcionE=0;
    
        while(valido){
            System.out.print("Seleccione opcion: ");
            
            String opcionElementos = sc.nextLine();
            opcionE = Integer.parseInt(opcionElementos)-1;
            if(opcionE>-1 && opcionE<5){
                valido=false;
                
            }
            else{
                System.out.println("Opcion ingresada no es valida");
            }
        }
        System.out.println("Seleccione tipo algoritmo: ");
        System.out.println("1) Paralelo");
        System.out.println("2) Secuencial");
        System.out.print("Seleccione opcion: ");
        String opcionAlgoritmo = sc.nextLine();
        int opcionAlg=Integer.parseInt(opcionAlgoritmo);
        switch (opcionAlg) {
            case 1:
                System.out.println("Has seleccionado el algoritmo paralelo");
                long inicio = System.currentTimeMillis();
                //Hilos dilatacion
                MatrizFinal matriz = new MatrizFinal(original);
                Hilo miHilo = new Hilo(splitArrayPart1,matriz,1,1,opcionE);
                Hilo miHilo2 = new Hilo(splitArrayPart2,matriz,0,1,opcionE);
                //Hilos erosion
                MatrizFinal matriz2 = new MatrizFinal(original);
                Hilo miHilo3 = new Hilo(splitArrayPart1,matriz2,1,0,opcionE);
                Hilo miHilo4 = new Hilo(splitArrayPart2,matriz2,0,0,opcionE);

                miHilo.run();
                miHilo2.run();
                miHilo3.run();
                miHilo4.run();
                //miHilo.join();
                //miHilo2.join();
                // miHilo3.join();
                // miHilo4.join();

                //System.out.println(miHilo.alguienTermino+"  "+miHilo2.alguienTermino);
                // if(matriz.contar==2){
                generarPgm("dilatacionParalelo.pgm",lecturaYescritura.getPicWidth(),lecturaYescritura.getPicHeight(),lecturaYescritura.getMaxvalue(),matriz);
                generarPgm("erosionParalelo.pgm",lecturaYescritura.getPicWidth(),lecturaYescritura.getPicHeight(),lecturaYescritura.getMaxvalue(),matriz2);
                System.out.println("Archivos generados correctamente");
                long fin = System.currentTimeMillis();
                double tiempo = (double) ((fin - inicio));
                System.out.println(tiempo +" milisegundos");
                    //     break;
                    // }
                    
                break;
                

            case 2:
                // Leyendo datos usando readLine
                System.out.println("Has seleccionado el algoritmo secuencial");
                long inicio2 = System.currentTimeMillis();
                DilatacionErosion dilatacionErosion = new DilatacionErosion(lecturaYescritura.getMatriz2d(),opcionE);
                String nombreArchivo3 = "dilatacionSecuencial.pgm";
                String nombreArchivo4 = "erosionSecuencial.pgm";

                lecturaYescritura.generarPgm(nombreArchivo3,  dilatacionErosion.dilatacion());
                lecturaYescritura.generarPgm(nombreArchivo4,  dilatacionErosion.erosion());
                System.out.println("Archivos generados correctamente");
                long fin2 = System.currentTimeMillis();
                double tiempo2 = (double) ((fin2 - inicio2));
                System.out.println(tiempo2 +" milisegundos");
                break;
        
            default:
                System.out.println("Opcion ingresada no es valida");
                break;
        }
        sc.close();
        
    }
}
