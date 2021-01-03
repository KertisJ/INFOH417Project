package experiments;

import mechanisms.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment3 { // Combined Read and Write
    private int B_i;
    private int B_o;
    private List<IStream> IList = new ArrayList<>();
    private OStream output;

    public String runExp3(int OType, List<String> inputf, String outputf, int B_size_i, int B_size_o)
            throws IOException {
        this.B_i = B_size_i;
        this.B_o = B_size_o;
        
        if (this.B_i > 0) {
            switch (OType) {
                case 1:
                    return rrmerge3_1(inputf, outputf);
                case 2:
                    return rrmerge3_2(inputf, outputf);
                case 3:
                    if (this.B_o > 0) {
                        return rrmerge3_3(inputf, outputf);
                    } else {
                        throw new IllegalArgumentException(
                                "For output stream 3, the size of the buffer must be passed as an argument, and > 0.");
                    }
                case 4:
                    if (this.B_o > 0) {
                        return rrmerge3_4(inputf, outputf);
                    } else {
                        throw new IllegalArgumentException(
                                "For output stream 4, the size of the buffer must be passed as an argument, and > 0.");
                    }
                default:
                    throw new IllegalArgumentException(
                            "Please enter a number between 1 and 4 for output streams.");
            }
        } else {
            throw new IllegalArgumentException(
                    "For input stream 3, the size of the buffer must be passed as an argument, and > 0.");
        }
                  
    }

    public String runExp3(int OType, List<String> inputf, String outputf, int B_size_i) throws IOException {
        return runExp3(OType, inputf, outputf, B_size_i, 0);
    }

    private void rrmerge() throws IOException {
        while (this.IList.size() > 0) {
            List<IStream> fullRead = new ArrayList<>();
            for (int i = 0; i < this.IList.size(); i++) {
                IStream input = this.IList.get(i);
                if (!input.end_of_stream()) {
                    this.output.writeln(input.readln());
                } else {
                    fullRead.add(input);
                }
            }
            this.IList.removeAll(fullRead);
        }
        this.output.close();
    }

    private String rrmerge3_1(List<String> inPaths, String outPath) throws IOException {
        long startTime = System.currentTimeMillis();
        for (String fPath : inPaths) {
            IStream.IStream3 iStream = new IStream.IStream3(this.B_i);
            iStream.open(fPath);
            this.IList.add(iStream);
        }
        this.output = new OStream.OStream1();
        this.output.create(outPath);

        this.rrmerge();
        long endTime = System.currentTimeMillis();
        return ("Experience3 merge our files with InputMechanism3 and OutputMechanism1 with Bi = "+B_i+", and that took "
                + (endTime - startTime) + " milliseconds.\n");
    }

    private String rrmerge3_2(List<String> inPaths, String outPath) throws IOException {
        long startTime = System.currentTimeMillis();
        for (String fPath : inPaths) {
            IStream.IStream3 iStream = new IStream.IStream3(this.B_i);
            iStream.open(fPath);
            this.IList.add(iStream);
        }
        this.output = new OStream.OStream2();
        this.output.create(outPath);

        this.rrmerge();
        long endTime = System.currentTimeMillis();
        return ("Experience3 merge our files with InputMechanism3 and OutputMechanism2 with Bi = "+B_i+", and that took "
                + (endTime - startTime) + " milliseconds.\n");
    }

    private String rrmerge3_3(List<String> inPaths, String outPath) throws IOException {
        long startTime = System.currentTimeMillis();
        for (String fPath : inPaths) {
            IStream.IStream3 iStream = new IStream.IStream3(this.B_i);
            iStream.open(fPath);
            this.IList.add(iStream);
        }
        this.output = new OStream.OStream3(this.B_o);
        this.output.create(outPath);

        this.rrmerge();
        long endTime = System.currentTimeMillis();
        return ("Experience3 merge our files with InputMechanism3 and OutputMechanism3 with Bi = "+B_i+", with Bo = "
                + this.B_o + ", and that took " + (endTime - startTime) + " milliseconds.\n");
    }

    private String rrmerge3_4(List<String> inPaths, String outPath) throws IOException {
        long startTime = System.currentTimeMillis();
        for (String fPath : inPaths) {
            IStream.IStream3 iStream = new IStream.IStream3(this.B_i);
            iStream.open(fPath);
            this.IList.add(iStream);
        }
        this.output = new OStream.OStream4(this.B_o);
        this.output.create(outPath);

        this.rrmerge();
        long endTime = System.currentTimeMillis();
        return ("Experience3 merge our files with InputMechanism3 and OutputMechanism4 with Bi = "+B_i+", with Bo = "
                + this.B_o + ", and that took " + (endTime - startTime) + " milliseconds.\n");
    }

}