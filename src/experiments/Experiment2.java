package experiments;

import mechanisms.IStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Experiment2 {
    private Random rand = new Random();
    private int B;

    public String runExp2(int IType, String f, int j, int Buf) throws IOException {
        this.B = Buf;
        switch (IType) {
            case 1:
                return randjump1(f, j);
            case 2:
                return randjump2(f, j);
            case 3:
                if (B > 0) {
                    return randjump3(f, j);
                } else {
                    throw new IllegalArgumentException(
                            "For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            case 4:
                if (B > 0) {
                    return randjump4(f, j);
                } else {
                    throw new IllegalArgumentException(
                            "For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            default:
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    // Only if in the main class we call runExp2 without specifying the size of the
    // buffer
    // So especially for input stream mechanisms 1 and 2
    // "exp.runExp2(inputStreamMechanism, filePath, numberOfJumps)";
    public String runExp2(int IType, String f, int j) throws IOException {
        return runExp2(IType, f, j, 0);
    }

    private String randjump1(String f, int j) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream1 iStream = new IStream.IStream1();
        iStream.open(f);
        int str_sum = 0;
        RandomAccessFile file = new RandomAccessFile(f, "r");
        long fLen = file.length();
        file.close();
        for (int i = 0; i < j; i++) {
            long p = (rand.nextInt((int) fLen));
            iStream.seek(p);
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        return ("Experience2 with input type "
                + iStream.toString().substring(iStream.toString().indexOf('$') + 1, iStream.toString().indexOf('@'))
                + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum + " and that took "
                + (endTime - startTime) + " milliseconds\n");
    }

    private String randjump2(String f, int j) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream2 iStream = new IStream.IStream2();
        iStream.open(f);
        int str_sum = 0;
        RandomAccessFile file = new RandomAccessFile(f, "r");
        long fLen = file.length();
        file.close();
        for (int i = 0; i < j; i++) {
            long p = (rand.nextInt((int) fLen));
            iStream.seek(p);
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        return ("Experience2 with input type "
                + iStream.toString().substring(iStream.toString().indexOf('$') + 1, iStream.toString().indexOf('@'))
                + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum + " and that took "
                + (endTime - startTime) + " milliseconds\n");
    }

    private String randjump3(String f, int j) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream3 iStream = new IStream.IStream3(B);
        iStream.open(f);
        int str_sum = 0;
        RandomAccessFile file = new RandomAccessFile(f, "r");
        long fLen = file.length();
        file.close();
        for (int i = 0; i < j; i++) {
            long p = (rand.nextInt((int) fLen));
            iStream.seek(p);
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        return ("Experience2 with input type "
                + iStream.toString().substring(iStream.toString().indexOf('$') + 1, iStream.toString().indexOf('@'))
                + " on the file " + f + " with " + j + " number of jumps and buffer size B = " + B + " gives length "
                + str_sum + ", and that took " + (endTime - startTime) + " milliseconds\n");
    }

    private String randjump4(String f, int j) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream4 iStream = new IStream.IStream4(B);
        iStream.open(f);
        int str_sum = 0;
        RandomAccessFile file = new RandomAccessFile(f, "r");
        long fLen = file.length();
        file.close();
        for (int i = 0; i < j; i++) {
            long p = (rand.nextInt((int) fLen));
            iStream.seek(p);
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        return ("Experience2 with input type "
                + iStream.toString().substring(iStream.toString().indexOf('$') + 1, iStream.toString().indexOf('@'))
                + " on the file " + f + " with " + j + " number of jumps and buffer size B = " + B + " gives length "
                + str_sum + ", and that took " + (endTime - startTime) + " milliseconds\n");
    }
}
