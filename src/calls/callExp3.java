package calls;
import experiments.Experiment3;
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



public class callExp3 {
    public static void main(String[] args) throws IOException {

        int[] inputBufferSize = {1000, 10000, 100000};
        int[] outputBufferSize = {1000, 10000, 100000};
        String outputPath = "experiments_results/combinedRW";

        List<String> inputPaths = new ArrayList<>();
        // inputPaths.add("imdb/comp_cast_type.csv");
        // inputPaths.add("imdb/kind_type.csv");
        // inputPaths.add("imdb/company_type.csv");
        // inputPaths.add("imdb/role_type.csv");
        // inputPaths.add("imdb/link_type.csv");
        // inputPaths.add("imdb/info_type.csv");
        // inputPaths.add("imdb/movie_link.csv");
        // inputPaths.add("imdb/complete_cast.csv");
        // inputPaths.add("imdb/keyword.csv");
        // inputPaths.add("imdb/company_name.csv");
        // inputPaths.add("imdb/movie_info_idx.csv");
        // inputPaths.add("imdb/aka_title.csv");
        // inputPaths.add("imdb/aka_name.csv");
        // inputPaths.add("imdb/movie_companies.csv");
        // inputPaths.add("imdb/movie_keyword.csv");
        // inputPaths.add("imdb/title.csv");
        inputPaths.add("imdb/name.csv");
        inputPaths.add("imdb/person_info.csv");
        inputPaths.add("imdb/movie_info.csv");
        inputPaths.add("imdb/cast_info.csv");

        String outExp3 = "experiments_results/Experiment3_Result.txt";

        try (OutputStream oex3 = new FileOutputStream(outExp3);
                Writer writer3 = new OutputStreamWriter(oex3, "UTF-8")) {
            writer3.write("\tResults of experiment 3 on all files\n");
            writer3.write("\t------------------------------------\n\n\n");

            Experiment3 exp = new Experiment3();
            writer3.write("Start of Experiment3 with our files.\n\n");
            
            for (int j = 2; j < 4; j++) {
                    if (j < 3) {
                        for (int k = 0; k < inputBufferSize.length; k++) {
                        writer3.write(exp.runExp3(j, inputPaths,
                        outputPath + String.valueOf(3) + "_" + String.valueOf(j) + ".csv", inputBufferSize[k], 0));
                        }
                    }
                    else {
                        for (int k = 0; k < inputBufferSize.length; k++) {
                            for (int l = 0; l < outputBufferSize.length; l++) {
                                writer3.write(exp.runExp3(j, inputPaths,
                                outputPath + String.valueOf(3) + "_" + String.valueOf(j) + ".csv", inputBufferSize[k],
                                outputBufferSize[l]));
                            }
                        }
                    }
            }
            writer3.write('\n');
            }

        // Experiment3 exp3 = new Experiment3(); for (int i = 1; i < 5; i++) { for (int
        // j = 1; j < 5; j++) { exp3.runExp3(i, j, inputFilePaths, outputFilePath,
        // inputBufferSize, outputBufferSize); } }
        // Experiment3 exp = new Experiment3();
        // System.out.println(exp.runExp3(4, 1, inputPaths, outputP, inBS, outBS));
    }
}
