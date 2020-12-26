import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException{
        String inputPath = "imdb/keyword.csv";
        int[] inputBufferSize = {50, 100, 500, 1000};
        int outputBufferSize = 100;
        int inputType = 1;
        int outputType = 1;
        int[] numberOfJump = {50, 100, 500, 1000};

        String outputPath = "imdb/combinedRW.csv";

        List<String> inputPaths = new ArrayList<>();
        inputPaths.add("imdb/comp_cast_type.csv");
        inputPaths.add("imdb/kind_type.csv");
        inputPaths.add("imdb/company_type.csv");
        inputPaths.add("imdb/role_type.csv");
        inputPaths.add("imdb/link_type.csv");
        inputPaths.add("imdb/info_type.csv");
        inputPaths.add("imdb/movie_link.csv");
        inputPaths.add("imdb/complete_cast.csv");
        inputPaths.add("imdb/keyword.csv");
        inputPaths.add("imdb/company_name.csv");
        inputPaths.add("imdb/movie_info_idx.csv");
        inputPaths.add("imdb/aka_title.csv");
        inputPaths.add("imdb/aka_name.csv");
        inputPaths.add("imdb/movie_companies.csv");
        inputPaths.add("imdb/movie_keyword.csv");
        inputPaths.add("imdb/title.csv");
        inputPaths.add("imdb/name.csv");
        inputPaths.add("imdb/person_info.csv");
        inputPaths.add("imdb/movie_info.csv");
        inputPaths.add("imdb/cast_info.csv");



        Experiment1 exp1 = new Experiment1();
        for (int i = 0; i < inputPaths.size(); i++) {
            System.out.println("File " + inputPaths.get(i) + " to experiment. \n ");
            for (int iS = 1; iS < 5 ; iS++) {
                if (iS < 3) {
                    exp1.runExp1(iS, inputPaths.get(i));   
                } else {
                    for (int k = 0; k < inputBufferSize.length; k++) {
                        exp1.runExp1(iS, inputPaths.get(i), inputBufferSize[k]);  
                    }
                }
                
            }
            System.out.println("\n");
        }
        

        FileReader file = new FileReader(new File(inputPath));
        long fLen = new File(inputPath).length();
        file.close();
        System.out.println("The real leght of the file is " + fLen);
        
        Experiment2 exp2 = new Experiment2();
        for (int i = 0; i < inputPaths.size(); i++) {
            System.out.println("File " + inputPaths.get(i) + " to experiment. \n ");
            for (int iS = 1; iS < 5; iS++) {
                for (int j = 0; j < numberOfJump.length; j++) {
                    if (iS < 3) {
                        exp2.runExp2(iS, inputPaths.get(i), numberOfJump[j]);   
                    } else {
                        for (int k = 0; k < inputBufferSize.length; k++) {
                            exp2.runExp2(iS, inputPaths.get(i), numberOfJump[j], inputBufferSize[k]);  
                        }
                    }
                }  
            }
            System.out.println("\n");
        } 

        /**
        Experiment3 exp3 = new Experiment3();
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                exp3.runExp3(i, j, inputFilePaths, outputFilePath, inputBufferSize, outputBufferSize);  
            }
        }
        

        Experiment3 exp3 = new Experiment3();
        exp3.runExp3(inputType, outputType, inputPaths, outputPath, inputBufferSize, outputBufferSize);  
        */
    }
 }

 