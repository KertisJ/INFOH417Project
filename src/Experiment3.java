import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Experiment3 {      //Combined Read and Write

    public void runExp3(int IType, int OType, List<String> inputf, String outputf, int B_i, int B_o) throws IOException {
        if (IType == 1 || IType == 2) {
            if (OType == 1 || OType == 2) {
                rrmergeVersion1_a(IType, OType, inputf, outputf);
            } else if (OType == 3 || OType == 4){
                if (B_o > 0) {
                    rrmergeVersion1_b(IType, OType, inputf, outputf, B_o);
                } else {
                    throw new IllegalArgumentException("For this type of output stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            } else {
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for output streams.");
            }
        } else if (IType == 3 || IType == 4){
            if (OType == 1 || OType == 2) {
                if (B_i > 0) {
                    rrmergeVersion2_a(IType, OType, inputf, outputf, B_i);
                } else {
                    throw new IllegalArgumentException("For this type of input stream, the size of the buffer must be passed as an argument, and > 0.");
                }
            } else if (OType == 3 || OType == 4){
                if (B_i > 0 && B_o > 0) {
                    rrmergeVersion2_b(IType, OType, inputf, outputf, B_i, B_o);
                } else {
                    throw new IllegalArgumentException("For this type of input and output stream, the sizes of the buffers must be passed as an argument, and > 0.");
                }
            } else {
                throw new IllegalArgumentException("Please enter a number between 1 and 4 for output streams.");
            }
        } else {
            throw new IllegalArgumentException("Please enter a number between 1 and 4 for input streams.");
        }
    }


    private List<IStream> IList = new ArrayList<>();
    private OStream output;

    private void rrmerge() throws IOException {
        long startTime = System.currentTimeMillis();
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
        long endTime = System.currentTimeMillis();
        System.out.println("Experience3 took " + (endTime - startTime) + " milliseconds");
    }

    // none is buffered
    private void rrmergeVersion1_a(int IType, int OType, List<String> inPaths, String outPath) throws IOException {
        if (IType == 1) {
            for (String fPath : inPaths) {
                IStream.IStream1 i = new IStream.IStream1();
                i.open(fPath);
                this.IList.add(i);
            }
        } else {
            for (String fPath : inPaths) {
                IStream.IStream2 i = new IStream.IStream2();
                i.open(fPath);
                this.IList.add(i);
            }
        }

        if (OType == 1) {
            this.output = new OStream.OStream1();
        } else {
            this.output = new OStream.OStream1();
        }
        output.create(outPath);
        
        this.rrmerge();
    }

	// only output is buffered
    private void rrmergeVersion1_b(int IType, int OType, List<String> inPaths, String outPath, int B_o) throws IOException {
        if (IType == 1) {
            for (String fPath : inPaths) {
                IStream.IStream1 i = new IStream.IStream1();
                i.open(fPath);
                this.IList.add(i);
            }
        } else {
            for (String fPath : inPaths) {
                IStream.IStream2 i = new IStream.IStream2();
                i.open(fPath);
                this.IList.add(i);
            }
        }

        if (OType == 3) {
            this.output = new OStream.OStream3(B_o);
        } else {
            this.output = new OStream.OStream4(B_o);
        }
        output.create(outPath);
        
        this.rrmerge();
    }


	// only input is buffered
	private void rrmergeVersion2_a(int IType, int OType, List<String> inPaths, String outPath, int B_i) throws IOException {
        if (IType == 3) {
            for (String fPath : inPaths) {
                IStream.IStream3 input = new IStream.IStream3(B_i);
                input.open(fPath);
                this.IList.add(input);
            }
        } else {
            for (String fPath : inPaths) {
                IStream.IStream4 input = new IStream.IStream4(B_i);
                input.open(fPath);
                this.IList.add(input);
            }
        }

        if (OType == 1) {
            this.output = new OStream.OStream1();
        } else {
            this.output = new OStream.OStream2();
        }
        output.create(outPath);
        
		this.rrmerge();
	}


	// both buffered
	private void rrmergeVersion2_b(int IType, int OType, List<String> inPaths, String outPath, int B_i, int B_o) throws IOException {
        if (IType == 3) {
            for (String fPath : inPaths) {
                IStream.IStream3 i = new IStream.IStream3(B_i);
                i.open(fPath);
                this.IList.add(i);
            }
        } else {
            for (String fPath : inPaths) {
                IStream.IStream4 i = new IStream.IStream4(B_i);
                i.open(fPath);
                this.IList.add(i);
            }
        }

        if (OType == 3) {
            this.output = new OStream.OStream3(B_o);
        } else {
            this.output = new OStream.OStream4(B_o);
        }
        output.create(outPath);

        this.rrmerge();
    }
}
