import java.io.IOException;

public class Experiment1 {          // Sequential reading
    
    public void runExp1(int IType, String f, int B) throws IOException {
        if (IType == 1 || IType == 2) {
            lengthVersion1(IType, f);
        } 
        else if (IType == 3 || IType == 4){
            if (B >= 0) {
                lengthVersion2(IType, f, B);
            } else {
                throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and greater than zero.");
            }
        }
        else {
            throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }


    public void runExp1(int IType, String f) throws IOException {
        runExp1(IType, f, 0);
    }
    

    private int length(IStream iStream, String f) throws IOException {
        iStream.open(f);
        int str_sum = 0;
        while (!iStream.end_of_stream()) {
            int l = iStream.readln().length();
            str_sum += l;
        }
        System.out.println("Experience1 with input type " + iStream.toString().substring(iStream.toString().indexOf('$')+1, iStream.toString().indexOf('@')) + " on the file " + f + " give length " + str_sum);
        return str_sum;
    }

    
    private int lengthVersion1(int InputType, String f) throws IOException {
        switch (InputType) {
            case 1:
                return this.length(new IStream.IStream1(), f);
            case 2:
                return this.length(new IStream.IStream2(), f);
            default:
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }

    private int lengthVersion2(int InputType, String f, int B) throws IOException {
        switch (InputType) {
            case 3:
                return this.length(new IStream.IStream3(B), f);
            case 4:
                return this.length(new IStream.IStream4(B), f);
            default:
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }
}
