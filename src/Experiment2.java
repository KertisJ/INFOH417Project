import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Experiment2 {
    private Random rand = new Random();
    private int B ;

    public void runExp2(int IType, String f, int j, int Buf) throws IOException {
        this.B = Buf;
        switch (IType) {
            case 1 :
                randjump1(f, j);
                break;
            case 2 :
                randjump2(f, j);
                break;
            case 3 :
                if (B > 0) {
                    randjump3(f, j);
                } else {
                    throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
                break;
            case 4 :
                if (B > 0) {
                    randjump4(f, j);
                } else {
                    throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            break;
            default :
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    // Only if in the main class we call runExp2 without specifying the size of the buffer
    // So especially for input stream mechanisms 1 and 2 "exp.runExp2(inputStreamMechanism, filePath, numberOfJumps)";
    public void runExp2(int IType, String f, int j) throws IOException {
        runExp2(IType, f, j, 0);
    }

    private int randjump1(String f, int j) throws IOException {
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
        System.out.println("Experience2 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum);
        return str_sum;
    }

    private int randjump2(String f, int j) throws IOException {
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
        System.out.println("Experience2 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum);
        return str_sum;
    }

    private int randjump3(String f, int j) throws IOException {
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
        System.out.println("Experience2 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum);
        return str_sum;
    }

    private int randjump4(String f, int j) throws IOException {
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
        System.out.println("Experience2 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " with " + j + " number of jumps gives length " + str_sum);
        return str_sum;
    }
}

