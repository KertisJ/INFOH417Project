import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Main {
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
        inputPaths.add("imdb/comp_cast_type.csv");
        inputPaths.add("imdb/kind_type.csv");
        inputPaths.add("imdb/company_type.csv");
        inputPaths.add("imdb/role_type.csv");
        inputPaths.add("imdb/link_type.csv");
        inputPaths.add("imdb/info_type.csv");
        inputPaths.add("imdb/movie_link.csv");
        // inputPaths.add("imdb/complete_cast.csv");
        // inputPaths.add("imdb/keyword.csv");
        // inputPaths.add("imdb/company_name.csv");
        // inputPaths.add("imdb/movie_info_idx.csv");
        // inputPaths.add("imdb/aka_title.csv");
        // inputPaths.add("imdb/aka_name.csv");
        // inputPaths.add("imdb/movie_companies.csv");
        // inputPaths.add("imdb/movie_keyword.csv");
        // inputPaths.add("imdb/title.csv");
        // inputPaths.add("imdb/name.csv");
        // inputPaths.add("imdb/person_info.csv");
        // inputPaths.add("imdb/movie_info.csv");
        // inputPaths.add("imdb/cast_info.csv");

        String outExp1 = "experiments_results/Experiment1_Result.txt";
        String outExp2 = "experiments_results/Experiment2_Result.txt";
        String outExp3 = "experiments_results/Experiment3_Result.txt";
        String outExp4 = "experiments_results/Experiment4_Result.txt";

        try (OutputStream oex1 = new FileOutputStream(outExp1);
                Writer writer1 = new OutputStreamWriter(oex1, "UTF-8")) {
            writer1.write("\tResults of experiment 1 on all files\n");
            writer1.write("\t------------------------------------\n\n\n");
            System.out.println("Running Experiment1 ...");

            Experiment1 exp1 = new Experiment1();
            writer1.write("Start of Experiment1 with our files.\n\n");
            for (int i = 0; i < inputPaths.size(); i++) {
                writer1.write("\nFile " + inputPaths.get(i) + " to experiment -\n");
                for (int iS = 1; iS < 5; iS++) {
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

        System.out.println("Experiment1 completed. \nRunning Experiment2 ...");

        try (OutputStream oex2 = new FileOutputStream(outExp2);
                Writer writer2 = new OutputStreamWriter(oex2, "UTF-8")) {
            writer2.write("\tResults of experiment 2 on all files\n");
            writer2.write("\t------------------------------------\n\n\n");
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
        System.out.println("Experiment2 completed. \nRunning Experiment3 ...");

        /**
         * Experiment1 exp1 = new Experiment1(); for (int i = 0; i < inputPaths.size();
         * i++) { System.out.println("File " + inputPaths.get(i) + " to experiment. \n
         * "); for (int iS = 1; iS < 5 ; iS++) { if (iS < 3) { exp1.runExp1(iS,
         * inputPaths.get(i)); } else { for (int k = 0; k < inputBufferSize.length; k++)
         * { exp1.runExp1(iS, inputPaths.get(i), inputBufferSize[k]); } }
         * 
         * } System.out.println("\n"); }
         * 
         * 
         * FileReader file = new FileReader(new File(inputPath)); long fLen = new
         * File(inputPath).length(); file.close(); System.out.println("The real leght of
         * the file is " + fLen);
         * 
         * Experiment2 exp2 = new Experiment2(); for (int i = 0; i < inputPaths.size();
         * i++) { System.out.println("File " + inputPaths.get(i) + " to experiment. \n
         * "); for (int iS = 1; iS < 5; iS++) { for (int j = 0; j < numberOfJump.length;
         * j++) { if (iS < 3) { exp2.runExp2(iS, inputPaths.get(i), numberOfJump[j]); }
         * else { for (int k = 0; k < inputBufferSize.length; k++) { exp2.runExp2(iS,
         * inputPaths.get(i), numberOfJump[j], inputBufferSize[k]); } } } }
         * System.out.println("\n"); }
         */

        /**
         * Experiment3 exp3 = new Experiment3(); for (int i = 1; i < 5; i++) { for (int
         * j = 1; j < 5; j++) { exp3.runExp3(i, j, inputFilePaths, outputFilePath,
         * inputBufferSize, outputBufferSize); } }
         */

        // Experiment3 exp = new Experiment3();
        // System.out.println(exp.runExp3(4, 1, inputPaths, outputP, inBS, outBS));

        try (OutputStream oex3 = new FileOutputStream(outExp3);
                Writer writer3 = new OutputStreamWriter(oex3, "UTF-8")) {
            writer3.write("\tResults of experiment 3 on all files\n");
            writer3.write("\t------------------------------------\n\n\n");

            Experiment3 exp3 = new Experiment3();
            writer3.write("Start of Experiment3 with our files.\n\n");
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 4; j++) {
                    System.out.println("input " + i + " and output " + j);
                    if (j < 3) {
                        writer3.write(exp3.runExp3(i, j, inputPaths,
                                outputPath + String.valueOf(i) + "_" + String.valueOf(j) + ".csv", inBS));
                    } else {
                        for (int k = 0; k < outputBufferSize.length; k++) {
                            writer3.write(exp3.runExp3(i, j, inputPaths,
                                    outputPath + String.valueOf(i) + "_" + String.valueOf(j) + ".csv", inBS,
                                    outputBufferSize[k]));
                        }
                    }
                }
                writer3.write('\n');
            }
        }

        System.out.println("Experiment3 completed.\nThe results are available in files " + outExp1 + " , " + outExp2
                + " and " + outExp3);

    }
}
