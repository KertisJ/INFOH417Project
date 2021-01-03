package calls;
import experiments.Experiment2;
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



public class callExp2 {

    public static void main(String[] args) throws IOException {

        String inputPath = "imdb/movie_link.csv";
        int[] inputBufferSize = { 50, 100, 500, 1000 };
        int[] outputBufferSize = { 50, 100, 500, 1000 };
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

        String outExp2 = "experiments_results/Experiment2_Result.txt";

        try (OutputStream oex2 = new FileOutputStream(outExp2);
            Writer writer2 = new OutputStreamWriter(oex2, "UTF-8")) {
                writer2.write("\tResults of experiment 2 on all files\n");
                writer2.write("\t------------------------------------\n\n\n");
                System.out.println("\nRunning Experiment2 ...");
                Experiment2 exp2 = new Experiment2();
                writer2.write("Start of Experiment2 with our files.\n\n");
                for (int i = 0; i < inputPaths.size(); i++) {
                    writer2.write("\nFile " + inputPaths.get(i) + " to experiment -\n");
                    for (int iS = 1; iS < 5; iS++) {
                        for (int j = 0; j < numberOfJump.length; j++) {
                            if (iS < 3) {
                                writer2.write(exp2.runExp2(iS, inputPaths.get(i), numberOfJump[j]));
                            } else {
                                for (int k = 0; k < inputBufferSize.length; k++) {
                                    writer2.write(exp2.runExp2(iS, inputPaths.get(i), numberOfJump[j], inputBufferSize[k]));
                                }
                            }
                        }
                    }
                }
            }
        System.out.println("Experiment2 completed.\nYou can find results in " + outExp2);
    }
}
