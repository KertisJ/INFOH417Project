package calls;

import experiments.Experiment2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class callExp2 {

    public static void main(String[] args) throws IOException {

        int[] inputBufferSize = { 50, 100, 500, 100000 };
        List<Integer> numberOfJump = new ArrayList<>(Arrays.asList(1000, 10000));
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

        String outExp2 = "experiments_results/Experiment2_Result.txt";
        Integer maxJumps =  Collections.max(numberOfJump);
        System.out.println(maxJumps);
        

        try (OutputStream oex2 = new FileOutputStream(outExp2);
            Writer writer2 = new OutputStreamWriter(oex2, "UTF-8")) {
                writer2.write("\tResults of experiment 2 on all files\n");
                writer2.write("\t------------------------------------\n\n\n");
                Experiment2 exp2 = new Experiment2();
                writer2.write("Start of Experiment2 with our files.\n\n");
                for (int i = 0; i < inputPaths.size(); i++) {
                    writer2.write("\nFile " + inputPaths.get(i) + " to experiment -\n");
                    RandomAccessFile file = new RandomAccessFile(inputPaths.get(i), "r");
                    long fLen = file.length();
                    file.close();
                    List<Integer> positions = new ArrayList<Integer>();
                    Random rand = new Random();
                    for(int pp = 0; pp<maxJumps+1;pp++){
                        positions.add(rand.nextInt((int)fLen));
                    }
                    for (int iS = 1; iS < 5; iS++) {
                        for (int j = 0; j < numberOfJump.size(); j++) {
                            if (iS < 3) {
                                writer2.write(exp2.runExp2(iS, inputPaths.get(i), numberOfJump.get(j), positions));
                            } else {
                                for (int k = 0; k < inputBufferSize.length; k++) {
                                    writer2.write(exp2.runExp2(iS, inputPaths.get(i), numberOfJump.get(j), inputBufferSize[k], positions));
                                }
                            }
                        }
                    }
                }
            }
    }
}
