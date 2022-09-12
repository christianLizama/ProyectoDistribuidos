import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        


        LecturaEscritura lecturaYescritura = new LecturaEscritura();
        
        

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Leyendo datos usando readLine
        System.out.print("ingrese nombre del archivo: ");
        String nombreArchivo = reader.readLine();
        lecturaYescritura.leer(nombreArchivo);
        lecturaYescritura.getMatriz2d();

        DilatacionErosion dilatacionErosion = new DilatacionErosion(lecturaYescritura.getMatriz2d());
        String nombreArchivo1 = "dilatacion.pgm";
        String nombreArchivo2 = "Erosion.pgm";

        lecturaYescritura.generarPgm(nombreArchivo1,  dilatacionErosion.dilatacion());
        lecturaYescritura.generarPgm(nombreArchivo2,  dilatacionErosion.erosion());


    }
}
