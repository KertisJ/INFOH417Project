import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException{
        String filePath = "imdb/info_type.csv";
        int bufferSize = 100;
        int InputType = 4;
        int numberOfJump = 100;
        
        // Sequential reading
        Experiment1 exp1 = new Experiment1();
        exp1.runExp1(InputType, filePath, bufferSize);
        
        // Random reading
        Experiment2 exp2 = new Experiment2();
        exp2.runExp2(InputType, filePath, numberOfJump, bufferSize);
    }
}
