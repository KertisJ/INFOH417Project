import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException{
        String inputPath = "imdb/title.csv";
        int inputBufferSize = 10000;
        int outputBufferSize = 10000;
        int inputType = 1;
        int outputType = 1;
        int numberOfJump = 5;

        String outputPath = "imdb/combinedRW.csv";

        List<String> inputPaths = new ArrayList<>();
        inputPaths.add("imdb/comp_cast_type.csv");
        inputPaths.add("imdb/kind_type.csv");
        inputPaths.add("imdb/company_type.csv");
        //inputFilePaths.add("imdb/role_type.csv");
        //inputFilePaths.add("imdb/link_type.csv");
        //inputFilePaths.add("imdb/info_type.csv");
        //inputFilePaths.add(filePath);

        Experiment1 exp1 = new Experiment1();
        exp1.runExp1(1, inputPath, inputBufferSize);
        
        /**
        Experiment1 exp1 = new Experiment1();
        for (int iS = 1; iS < 5; iS++) {
            exp1.runExp1(iS, inputPath, inputBufferSize);  
        }
        */

        FileReader file = new FileReader(new File(inputPath));
        long fLen = new File(inputPath).length();
        file.close();
        System.out.println(fLen);
        
        /**
        Experiment2 exp2 = new Experiment2();
        for (int iS = 1; iS < 5; iS++) {
            exp2.runExp2(iS, inputPath, numberOfJump, inputBufferSize);  
        } 
        */

        /**
        Experiment3 exp3 = new Experiment3();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                exp3.runExp3(i, j, inputFilePaths, outputFilePath, inputBufferSize, outputBufferSize);  
            }
        }
        */
        Experiment3 exp3 = new Experiment3();
        exp3.runExp3(inputType, outputType, inputPaths, outputPath, inputBufferSize, outputBufferSize);  
    }
 }

 