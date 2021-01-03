package calls;
import experiments.Experiment4;
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



public class callExp4 {
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

        String outExp4 = "experiments_results/Experiment4_Result.txt";

        int k = 3;
        int M = 1500;
        int d = 10;
        Experiment4 exp4 = new Experiment4();
        exp4.runExp4(inputPath, k, M, d);

    }
}
