import java.io.IOException;
import java.util.Scanner;

public class Apartado1 {
    public static void main(String[] args) {
        //Hay que entrar al bat y cambiar su ruta en caso de cambiar de directorio
        String path = "E:\\Programacion\\PGV\\src\\UT1\\ComunicacionDeProcesos\\Hijos\\Apartado1\\src\\execute.bat";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", path);
            processBuilder.start();
            //Es lo mismo que:
//            Process process = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe
//            D:\\Programacion\\PGV\\ToUpperCase\\src\\music.mp3");

            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            System.out.println(text.toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}