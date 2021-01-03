package calls;
import experiments.Experiment1;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class callExp1 {
    public static void main(String[] args) throws IOException{
        String inputPath = "imdb/movie_link.csv";
        int[] inputBufferSize = {500, 1000, 100000, 5000000};
        int[] outputBufferSize = {500, 1000, 100000, 5000000};
        int[] numberOfJump = { 50, 100, 500, 1000 };
        int inBS = 100;
        int outBS = 100;
        int inputType = 1;
        int outputType = 3;
        String outputPath = "experiments_results/combinedRW";
        String outputP = "experiments_results/combinedRW_Test.csv";


        List<String> inputPaths = new ArrayList<>();
        // inputPaths.add("imdb/comp_cast_type.csv");
        // inputPaths.add("imdb/kind_type.csv");
        // inputPaths.add("imdb/company_type.csv");
        // inputPaths.add("imdb/role_type.csv");
        // inputPaths.add("imdb/link_type.csv");
        // inputPaths.add("imdb/info_type.csv");
        // inputPaths.add("imdb/movie_link.csv");
        // inputPaths.add("imdb/complete_cast.csv");
        inputPaths.add("imdb/keyword.csv");
        // inputPaths.add("imdb/company_name.csv");
        // inputPaths.add("imdb/movie_info_idx.csv");
        inputPaths.add("imdb/aka_title.csv");
        // inputPaths.add("imdb/aka_name.csv");
        // inputPaths.add("imdb/movie_companies.csv");
        // inputPaths.add("imdb/movie_keyword.csv");
        // inputPaths.add("imdb/title.csv");
        // inputPaths.add("imdb/name.csv");
        // inputPaths.add("imdb/person_info.csv");
        // inputPaths.add("imdb/movie_info.csv");
        // inputPaths.add("imdb/cast_info.csv");

        String outExp1 = "experiments_results/Experiment1_Result.txt";


        /** 
        * FileReader file = new FileReader(new File(inputPath)); long fLen = new
        * File(inputPath).length(); file.close(); System.out.println("The real leght of
        * the file is " + fLen);
        */ 
        
        try (OutputStream oex1 = new FileOutputStream(outExp1);
            Writer writer1 = new OutputStreamWriter(oex1, "UTF-8")) {
                writer1.write("\tResults of experiment 1 on all files\n");
                writer1.write("\t------------------------------------\n\n\n");
                System.out.println("Running Experiment1 ...");
                Experiment1 exp1 = new Experiment1();
                writer1.write("Start of Experiment1 with our files.\n\n");
                for (int i = 0; i < inputPaths.size(); i++) {
                    writer1.write("\nFile " + inputPaths.get(i) + " to experiment -\n");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPaths.get(i)), "UTF-8")); 
                    System.out.println("La taille de " + inputPaths.get(i) +  " est " + reader);
                    for (int iS = 2; iS < 5; iS++) {
                        if (iS < 3) {
                            writer1.write(exp1.runExp1(iS, inputPaths.get(i)));
                        } else {
                            for (int k = 0; k < inputBufferSize.length; k++) {
                                writer1.write(exp1.runExp1(iS, inputPaths.get(i), inputBufferSize[k]));
                            }
                        }
                    }
                }
            }
    }
}
