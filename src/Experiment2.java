import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Experiment2 {
    private Random rand = new Random();

    public void runExp2(int IType, String f, int j, int B) throws IOException {
        if (IType == 1 || IType == 2) {
            randjumpVersion1(IType, f, j);
        } 
        else if (IType == 3 || IType == 4){
            if (B >= 0) {
                randjumpVersion2(IType, f, j, B);
            } else {
                throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and greater than zero.");
            }
        }
        else {
            throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    public void runExp2(int IType, String f, int j) throws IOException {
        runExp2(IType, f, j, 0);
    }

    private int randjump(String f, IStream iStream, int j) throws IOException {
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
        System.out.println("Experience2 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum);
        return str_sum;
    }

    public int randjumpVersion1(int IType, String f, int j) throws IOException {
        switch (IType) {
            case 1:
                return this.randjump(f, new IStream.IStream1(), j);
            case 2:
                return this.randjump(f, new IStream.IStream2(), j);
            default:
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    public int randjumpVersion2(int IType, String f, int j, int B) throws IOException {
        switch (IType) {
            case 3:
                return this.randjump(f, new IStream.IStream3(B), j);
            case 4:
                return this.randjump(f, new IStream.IStream4(B), j);
            default:
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }
}