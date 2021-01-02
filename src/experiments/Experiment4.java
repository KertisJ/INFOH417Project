package experiments;

import mechanisms.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Experiment4 {
    private final String OUTPUT_FOLDER = "experiments_results/subfile_experiment4/";
    private Queue<String> subfilesQueue = new LinkedList<>();

    public void runExp4(String f, int k, int M, int d) throws IOException {
        this.extsort(f, k, M);
    }


    private void extsort(String f, int k, int M) throws IOException {
        // Priority queue bases on the k-th column
        // Custom comparator directly implemented in the queue 
        Queue<String> extsortbuf = new PriorityQueue<>((e1, e2) -> {
            String[] item1 = e1.split(",");
            String[] item2 = e2.split(",");
            String k1;
            String k2;
            try {
                k1 = item1[k - 1].trim();
                k2 = item2[k - 1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("ERROR: k is greater than the number of columns");
            }
            return k1.compareTo(k2);
        });

        // Best input mechanism
        IStream inputStream = new IStream.IStream2();
        // Best output mechanism
        OStream outputStream = new OStream.OStream3(1000);
        inputStream.open(f);
        // Current read line
        String line;
        int memoryLeft = M;
        int subfileCount = 0;

        // Write the sorted records to a new output file
        while (!inputStream.end_of_stream()) {
            line = inputStream.readln();
            //check if we have enough buffer for the next tuple or not
            if (memoryLeft < line.length()) {
                String outputFileName = OUTPUT_FOLDER + "outfile" + subfileCount + ".txt";
                outputStream.create(outputFileName);
                // do {
                    // outputStream.writeln(extsortbuf.poll());
                // } while (extsortbuf.poll() != null);
                while (extsortbuf.size() > 0) {
                    outputStream.writeln(extsortbuf.poll());
                }
                subfilesQueue.add(outputFileName);
                outputStream.close();
                // Reset the buffer to be the original size
                memoryLeft = M;
                subfileCount++;
            }
            extsortbuf.add(line);
            memoryLeft -= line.length();
        }

        // This outputs the remaining lines of the buffer into another subfile
        String outputFileName = OUTPUT_FOLDER + "outfile" + subfileCount + ".txt";
        outputStream.create(outputFileName);
        while (extsortbuf.size() > 0) {
            outputStream.writeln(extsortbuf.poll());
        }
        subfilesQueue.add(outputFileName);
        outputStream.close();
    }

}