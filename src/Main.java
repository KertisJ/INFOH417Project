import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException{
        String filePath = "imdb/movie_link.csv";
        int bufferSize = 1000;
        int inputType = 1;
        int numberOfJump = 1000;

        Experiment1 exp1 = new Experiment1();
        for (int i = 1; i < 5; i++) {
            exp1.runExp1(i, filePath, bufferSize);  
        }
        
        Experiment2 exp2 = new Experiment2();
        for (int i = 1; i < 5; i++) {
            exp2.runExp2(i, filePath, numberOfJump, bufferSize);  
        } 

    }
 }

 

 