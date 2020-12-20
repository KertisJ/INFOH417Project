//import io.*;

import java.io.IOException;

public class Experiment1 {
    public int length(IStream input, String f) throws IOException {
        input.open(f);
        int line_sum = 0;
        while (!input.end_of_stream()) {
            String line = input.readln();
            line_sum += line.length();
        }
        return line_sum;
    }

    public int lengthVersion(int InputType, String filePath) throws IOException {
        switch (InputType) {
            case 1:
                return this.length(new IStream.IStream1(), filePath);
            case 2:
                return this.length(new IStream.IStream2(), filePath);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }

    public int lengthVersion(int InputType, String filePath, int bufferSize) throws IOException {
        switch (InputType) {
            case 3:
                return this.length(new IStream.IStream3(bufferSize), filePath);
            case 4:
                return this.length(new IStream.IStream4(bufferSize), filePath);
            default:
                throw new IllegalArgumentException("wrong argument");
        }
    }
}
