import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tasklist {
    public static void main(String[] args) {
        String command = "tasklist /SVC /FO LIST";
        String[] cmdCommand = new String[]{"cmd","/C",command};
        ProcessBuilder processBuilder = new ProcessBuilder(cmdCommand);

        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
