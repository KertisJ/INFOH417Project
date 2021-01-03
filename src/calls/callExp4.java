package calls;
import experiments.Experiment4;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class callExp4 {
    public static void main(String[] args) throws IOException {
        String inputPath = "imdb/movie_link.csv";
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


        int k = 3;
        int M = 1500;
        int d = 10;
        Experiment4 exp4 = new Experiment4();
        exp4.runExp4(inputPath, k, M, d);

    }
}
