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
        int[] inputBufferSize = { 50, 100, 500, 1000 };


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
                System.out.println("\nRunning Experiment1 ...");
                Experiment1 exp1 = new Experiment1();
                writer1.write("Start of Experiment1 with our files.\n\n");
                for (int i = 0; i < inputPaths.size(); i++) {
                    writer1.write("\nFile " + inputPaths.get(i) + " to experiment -\n");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputPaths.get(i)), "UTF-8")); 
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
        System.out.println("Experiment1 completed.\nYou can find results in " + outExp1);
    }
}
