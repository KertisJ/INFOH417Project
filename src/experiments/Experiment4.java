package experiments;

import mechanisms.*;
import java.io.IOException;
import java.util.*;
import java.util.Comparator; 
import java.util.PriorityQueue; 


public class Experiment4 {
    private final String OUTPUT_FOLDER = "experiments_results/subfile_experiment4/";
    private Queue<String> subfilesQueue = new LinkedList<>();

    public void runExp4(String f, int k, int M, int d) throws IOException {
        this.extsort(f, k, M, d);
    }


    private void extsort(String f, int k, int M, int d) throws IOException {
        // Priority queue bases on the k-th column
        // Custom comparator directly implemented in the queue 
        PriorityQueue<String> sortQueue = new PriorityQueue<>(new Comparate());
            // -> {
            // String[] subStr1 = s1.split(",");
            // String[] subStr2 = s2.split(",");
            // String k1;
            // String k2;
            // try {
                // k1 = subStr1[k - 1].trim();
                // k2 = subStr2[k - 1].trim();
            // } catch (ArrayIndexOutOfBoundsException e) {
                // throw new IllegalArgumentException("ERROR: k is greater than the number of " + f + " columns");
            // }
            // return k1.compareTo(k2);
        // });
        
        // Best input mechanism
        IStream inputStream = new IStream.IStream3(10000);
        // Best output mechanism
        OStream outputStream = new OStream.OStream3(10000);
        inputStream.open(f);
        // Current read line
        String line;
        int memoryLeft = M;
        int subfileCount = 1;

        // Write the sorted records to a new output file
        while (!inputStream.end_of_stream()) {
            line = inputStream.readln();
            //check if we have enough buffer for the next tuple or not
            if (memoryLeft < line.length()) {
                String outputFileName = OUTPUT_FOLDER + "outfile" + subfileCount + ".txt";
                outputStream.create(outputFileName);
                // do {
                    // outputStream.writeln(sortQueue.poll());
                // } while (sortQueue.poll() != null);
                while (!sortQueue.isEmpty()) {
                    outputStream.writeln(sortQueue.poll());
                }
                subfilesQueue.add(outputFileName);
                outputStream.close();
                // Reset the buffer to be the original size
                memoryLeft = M;
                subfileCount++;
            }
            sortQueue.add(line);
            memoryLeft -= line.length();
        }

        // Sort the remaining lines of the buffer into one last subfile
        String outputFileName = OUTPUT_FOLDER + "outfile" + subfileCount + ".txt";
        subfileCount++;
        outputStream.create(outputFileName);
        while (!sortQueue.isEmpty()) {
            outputStream.writeln(sortQueue.poll());
        }
        subfilesQueue.add(outputFileName);
        outputStream.close();

        // Merge d files 
        sortQueue.clear();
        List<IStream.IStream3> inputStreams = new ArrayList<>();
        while (this.subfilesQueue.size() > 1) {
            int num = Math.min(d, subfilesQueue.size());
            for (int i = 0; i < num; i++){
                inputStreams.add(new IStream.IStream3(100000));
                inputStreams.get(i).open(subfilesQueue.poll());        
            }           
            outputFileName = OUTPUT_FOLDER + "outfile" + subfileCount + ".txt";
            subfileCount++;
            outputStream.create(outputFileName);
            for(int i = 0; i < num; i++) {
                sortQueue.add(i + ";by;te;" + inputStreams.get(i).readln());
            }
            do {
                String[] spitline = sortQueue.poll().split(";by;te;");
                outputStream.writeln(spitline[1]);
                int idfile = Integer.parseInt(spitline[0]);
                if (!inputStreams.get(idfile).end_of_stream()) {
                    sortQueue.add(idfile + ";by;te;"+ inputStreams.get(idfile).readln());
                }                
            } while(!sortQueue.isEmpty());
            outputStream.close();
            subfilesQueue.add(outputFileName);
        }

    }

}
