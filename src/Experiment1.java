import java.io.IOException;

public class Experiment1 {          // Sequential reading
    
    private int B ;

    public void runExp1(int IType, String f, int Buf) throws IOException {
        this.B = Buf;
        switch (IType) {
            case 1 :
                length1(f);
                break;
            case 2 :
                length2(f);
                break;
            case 3 :
                if (B > 0) {
                    length3(f);
                } else {
                    throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
                break;
            case 4 :
                if (B > 0) {
                    length4(f);
                } else {
                    throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            break;
            default :
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    // Only if in the main class we call runExp1 without specifying the size of the buffer
    // So especially for input stream mechanisms 1 and 2 "exp.runExp1(inputStreamMechanism, filePath)";
    public void runExp1(int IType, String f) throws IOException {
        runExp1(IType, f, 0);
    }
    

    private int length1(String f) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream1 iStream = new IStream.IStream1(); 
        iStream.open(f);
        int str_sum = 0;
        while (!iStream.end_of_stream()) {
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Experience1 with input type " + 
            iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + 
            " on the file " + f + " give length " + str_sum + " and that took " + (endTime - startTime) + 
            " milliseconds");
        return str_sum;
    }

    private int length2(String f) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream2 iStream = new IStream.IStream2(); 
        iStream.open(f);
        int str_sum = 0;
        while (!iStream.end_of_stream()) {
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Experience1 with input type " + 
            iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) +
             " on the file " + f + " give length " + str_sum + " and that took " + (endTime - startTime) + 
             " milliseconds");
        return str_sum;
    }

    private int length3(String f) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream3 iStream = new IStream.IStream3(B); 
        iStream.open(f);
        int str_sum = 0;
        while (!iStream.end_of_stream()) {
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Experience1 with input type " + 
            iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + 
            " on the file " + f + " give length " + str_sum + " and that took " + (endTime - startTime) + 
            " milliseconds");
        return str_sum;
    }

    private int length4(String f) throws IOException {
        long startTime = System.currentTimeMillis();
        IStream.IStream4 iStream = new IStream.IStream4(B); 
        iStream.open(f);
        int str_sum = 0;
        while (!iStream.end_of_stream()) {
            int l = iStream.readln().length();
            str_sum += l;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Experience1 with input type " + 
            iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + 
            " on the file " + f + " give length " + str_sum + " and that took " + (endTime - startTime) + 
            " milliseconds");
        return str_sum;
    }
    
}
