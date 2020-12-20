import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public interface OStream {

    void create(String f_path) throws IOException;
    void writeln(String string) throws IOException;
    void close() throws IOException;
    char EOL = '\n';


    public class OStream1 implements OStream {  // Simple output mechanism
        private FileWriter f;
    
        public void create(String f_path) throws IOException {  
            f = new FileWriter(f_path);
        }
    
        public void writeln(String str) throws IOException {    // https://beginnersbook.com/2013/12/java-string-tochararray-method-example/
            for (char c : str.toCharArray()) {                //converting c into sequence of characters and return an Array of chars
                f.write(c);
            }
            f.write(EOL);
        }
        
        public void close() throws IOException {
            f.close();
        }
    }



    public class OStream2 implements OStream {  // Output mechanism with buffering mechanism
        private BufferedWriter bw;
    
        public void create(String f_path) throws IOException {  // https://www.programcreek.com/2011/03/fileoutputstream-vs-filewriter/
            this.bw = new BufferedWriter(new FileWriter(f_path));
        }
    
        public void writeln(String str) throws IOException {
            this.bw.write(str);
            this.bw.newLine();
        }
    
        public void close() throws IOException {
            this.bw.close();
        }
    }



    public class OStream3 implements OStream {  // Simple output with stream equipped with a size B buffer
        private BufferedWriter bw;
        private int B;
    
        public OStream3(int bufferSize) {
            this.B = bufferSize;
        }
    
        public void create(String f_path) throws IOException {
            bw = new BufferedWriter(new FileWriter(f_path), B);
        }
    
        public void writeln(String str) throws IOException {    // ** Add the buffer condition
            for (char ch : str.toCharArray()) {
                bw.write(ch);
            }
            bw.newLine();
        }
    
        public void close() throws IOException {
            bw.close();
        }
    }



    public class OStream4 implements OStream {  // Input with memory mapping
        private MappedByteBuffer Bb;
        private RandomAccessFile f;
        private FileChannel fChannel;
        private int B;
        private int bCount;
    
        public OStream4(int bufferSize) {
            this.B = bufferSize;
        }
    
        public void create(String f_path) throws IOException {      // https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
            this.f = new RandomAccessFile(f_path, "rw");
            this.bCount = 0;
            this.map();
        }
    
        public void writeln(String line) {
            for (char c : line.toCharArray()) {
                putChar(c);
            }
            putChar(EOL);
        }
    
        public void close() throws IOException {
            this.Bb.force();
            this.fChannel.truncate(this.bCount * this.B - this.Bb.remaining());
            this.fChannel.close();
            this.Bb.clear();
            this.f.close();
        }
    
        private void map() throws IOException {
            this.f.setLength(0);
            this.fChannel = this.f.getChannel();
            this.Bb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, this.bCount * this.B, this.B);
            this.bCount += 1;
        }
    
        private void putChar(char c) {
            if (!this.Bb.hasRemaining()) {
                try {
                    this.map();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.Bb.put((byte) c);
        }
    }

}

