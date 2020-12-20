import java.io.IOException;


public class Main {
    public static void main (String[] args) throws IOException{
        String filePath = "imdb/info_type.csv";
        int InputType = 3;

        System.out.println("Hello World");
        Experiment1 exp1 = new Experiment1();
        IStream.IStream1 inp = new IStream.IStream1();
        System.out.println(exp1.length(inp, filePath));
        
        Experiment1 exp2 = new Experiment1();
        if (InputType == 1 || InputType == 2) {
            System.out.println("Experience1 with Input type " + InputType + " give length " + exp2.lengthVersion(InputType, filePath));
        } 
        else {
            int bufferSize = 100;
            System.out.println("Experience1 with Input type " + InputType + " give length " +exp2.lengthVersion(InputType, filePath, bufferSize));
        }
    }
 }

 