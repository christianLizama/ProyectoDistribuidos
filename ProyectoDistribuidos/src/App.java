import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
    public static void main(String[] args){
        LecturaEscritura lecturaYescritura = new LecturaEscritura();
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Leyendo datos usando readLine
        //System.out.print("ingrese nombre del archivo: ");
        String nombreArchivo = "imgNueva.pgm";
        
        lecturaYescritura.leer(nombreArchivo);
        
        int[][] original = lecturaYescritura.getMatriz2d();
        int[][] copia = lecturaYescritura.getMatrizFinal();
        int[][] copia2 = lecturaYescritura.getMatrizFinal();
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
        
        
        boolean valido2=true;
        while (valido2) {

            System.out.println("Seleccione tipo algoritmo: ");
            System.out.println("1) Paralelo");
            System.out.println("2) Secuencial");
            System.out.print("Seleccione opcion: ");
            String opcionAlgoritmo = sc.nextLine();
            int opcionAlg=Integer.parseInt(opcionAlgoritmo);
            switch (opcionAlg) {
                case 1:
                    System.out.println("Has seleccionado el algoritmo paralelo\n");
                    
                    //Hilos dilatacion
                    MatrizFinal matriz = new MatrizFinal(original,copia);
                    valido2=false;
                    Boolean valido3=true;
                    while (valido3) {
                        System.out.println("Seleccione tecnica: ");
                        System.out.println("1) Dilatacion");
                        System.out.println("2) Erosion");
                        System.out.print("Seleccione opcion: ");
                        String opcionTecnica = sc.nextLine();
                        int opcionTec=Integer.parseInt(opcionTecnica);
                        switch (opcionTec) {
                            case 1:
                                valido3=false;
                                long inicio = System.currentTimeMillis();
                                Thread t1 = new Thread(new Hilo(matriz,0,((original.length/2)/2),1,opcionE,1,lecturaYescritura));
                                Thread t2 = new Thread(new Hilo(matriz,(((original.length/2)/2)-1),(original.length/2)+1,1,opcionE,2,lecturaYescritura));
                                Thread t3 = new Thread(new Hilo(matriz,(original.length/2), (original.length/2)+((original.length/2)/2)+1,1,opcionE,3,lecturaYescritura));
                                Thread t4 = new Thread(new Hilo(matriz,((original.length/2)+((original.length/2)/2)), original.length-1,1,opcionE,4,lecturaYescritura));
                                
                                t1.start();
                                t2.start();
                                t3.start();
                                t4.start();
                                
                
                                long fin = System.currentTimeMillis();
                                double tiempo = (double) ((fin - inicio));
                                System.out.println(tiempo +" milisegundos");
                                break;
                            case 2:
                                valido3=false;
                                long inicio2 = System.currentTimeMillis();
                                Thread t5 = new Thread(new Hilo(matriz,0,((original.length/2)/2),0,opcionE,1,lecturaYescritura));
                                Thread t6 = new Thread(new Hilo(matriz,(((original.length/2)/2)-1),(original.length/2)+1,0,opcionE,2,lecturaYescritura));
                                Thread t7 = new Thread(new Hilo(matriz,(original.length/2), (original.length/2)+((original.length/2)/2)+1,0,opcionE,3,lecturaYescritura));
                                Thread t8 = new Thread(new Hilo(matriz,((original.length/2)+((original.length/2)/2)), original.length-1,0,opcionE,4,lecturaYescritura));
                                
                                t5.start();
                                t6.start();
                                t7.start();
                                t8.start();
                                
                
                                long fin2 = System.currentTimeMillis();
                                double tiempo2 = (double) ((fin2 - inicio2));
                                System.out.println(tiempo2 +" milisegundos");
                                break;
                        
                            default:
                                break;
                        }
                    }
                    
                    valido2=false;
                    break;
                    
    
                case 2:
                    valido2=false;
                    // Leyendo datos usando readLine
                    System.out.println("Has seleccionado el algoritmo secuencial\n");
                    boolean valido4=true;
                    while (valido4) {
                        System.out.println("Seleccione tecnica: ");
                        System.out.println("1) Dilatacion");
                        System.out.println("2) Erosion");
                        System.out.print("Seleccione opcion: ");
                        String opcionTecnica = sc.nextLine();
                        int opcionTec=Integer.parseInt(opcionTecnica);
                        switch (opcionTec) {
                            case 1:
                                valido4=false;
                                long inicio2 = System.currentTimeMillis();
                                DilatacionErosion dilatacionErosion = new DilatacionErosion(lecturaYescritura.getMatriz2d(),opcionE);
                                String nombreArchivo3 = "dilatacionSecuencial.pgm";
                                
                                int [][] resultadoDilatacion = dilatacionErosion.dilatacion();
                                long fin2 = System.currentTimeMillis();
                                double tiempo2 = (double) ((fin2 - inicio2));
                                System.out.println(tiempo2 +" milisegundos");
                                lecturaYescritura.generarPgm(nombreArchivo3,  resultadoDilatacion);
                                System.out.println("Archivo dilatacion generado correctamente");
                                break;
                            case 2:
                                valido4=false;
                                long inicio3 = System.currentTimeMillis();
                                DilatacionErosion dilatacionErosion2 = new DilatacionErosion(lecturaYescritura.getMatriz2d(),opcionE);
                                String nombreArchivo4 = "erosionSecuencial.pgm";
                                
                                int [][] resultadoErosion = dilatacionErosion2.erosion();
                                long fin3 = System.currentTimeMillis();
                                double tiempo3 = (double) ((fin3 - inicio3));
                                System.out.println(tiempo3 +" milisegundos");
                                lecturaYescritura.generarPgm(nombreArchivo4,  resultadoErosion);
                                System.out.println("Archivo erosion generado correctamente");
                            break;
                        
                            default:
                            
                                break;
                        }
                    }
                    valido2=false;
                    break;
            
                default:
                    System.out.println("Opcion ingresada no es valida");
                    break;
            }
        }

            
        
        sc.close();
        
    }
}
