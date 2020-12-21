import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/* 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
 */

public interface IStream {

    void open(String f_path) throws IOException;
    String readln() throws IOException;
    void seek(long pos) throws IOException;
    boolean end_of_stream() throws IOException;
    char EOL = '\n';


    public class IStream1 implements IStream {  // Simple input mechanism
        private RandomAccessFile f;                          // better than filereader or fileinputstream
    
        public void open(String f_path) throws FileNotFoundException {
            this.f = new RandomAccessFile(f_path, "r");
        }
    
        public String readln() throws IOException {
            StringBuilder b = new StringBuilder();
            char c;
            do {
                c = (char) this.f.read();
                b.append(c);
            } while (c != EOL);
            return b.toString();
        }
    
        public void seek(long pos) throws IOException {
            this.f.seek(pos);
        }
    
        public boolean end_of_stream() throws IOException {
            long currentPos = this.f.getFilePointer();
            if (this.f.read() == -1) {                        // the InputStream will signal EOF by returning -1 from read()
                return true;
            } else {
                this.f.seek(currentPos);
                return false;
            }
        }
    }



    public class IStream2 implements IStream {  // Input mechanism with buffering mechanism
        private RandomAccessFile f;
        private BufferedReader br;
    
        public void open(String f_path) throws IOException {
            this.f = new RandomAccessFile(f_path, "r");
            this.br = new BufferedReader(new FileReader(this.f.getFD()));
        }
    
        public String readln() throws IOException {
            return br.readLine();
        }
    
        public void seek(long pos) throws IOException {
            this.f.seek(pos);
            this.br = new BufferedReader(new FileReader(this.f.getFD()));
        }
    
        public boolean end_of_stream() throws IOException {
            /**long currentPos = this.f.getFilePointer();
            String line = br.readLine();
            if (line != null) {
                this.f.seek(currentPos);
                return false;
            } else
                return true;*/
            this.br.mark(1);
            int i = this.br.read();
            this.br.reset();
            return i == -1;
        }
    }



    public class IStream3 implements IStream {  // Simple input with stream equipped with a size B buffer
        private RandomAccessFile f;
        private BufferedReader br;
        private int B;
    
        public IStream3(int bufferSize) {
            this.B = bufferSize;
        }
    
        public void open(String f_path) throws IOException {
            this.f = new RandomAccessFile(f_path, "r");
            this.br = new BufferedReader(new FileReader(this.f.getFD()), this.B);
        }
    
        public String readln() throws IOException {         // ** Add the buffer condition
            StringBuilder b = new StringBuilder();
            char c;
            do {
                c = (char) this.br.read();
                if (c != EOL)
                    b.append(c);
            } while (c != EOL);
            return b.toString();
        }
    
        public void seek(long pos) throws IOException {
            this.f.seek(pos);
            this.br = new BufferedReader(new FileReader(this.f.getFD()));
        }
    
        public boolean end_of_stream() throws IOException {
            long currentPos = this.f.getFilePointer();
            String line = br.readLine();
            if (line != null) {
                this.f.seek(currentPos);
                return false;
            } else
                return true;
        }
    }



    public class IStream4 implements IStream {  // Input with memory mapping
        private MappedByteBuffer Bb;
        private RandomAccessFile f;
        private long B, nextPos;
    
        public IStream4(int bufferSize) {
            this.B = bufferSize;
        }
    
        public void open(String f_path) throws IOException {
            this.f = new RandomAccessFile(f_path, "r");
            this.map();
        }
    
        public String readln() {                // https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
            StringBuilder b = new StringBuilder();
            char c;
            do {
                c = this.readChar();
                b.append(c);
            } while (c != EOL);
            return b.toString();
        }
    
        public void seek(long pos) throws IOException {
            this.nextPos = pos;
            this.map();
        }
        
        public boolean end_of_stream() throws IOException {
            return !this.Bb.hasRemaining() && this.nextPos >= this.f.length();
        }
    
        private void map() throws IOException {
            long size = Math.min(this.f.length() - this.nextPos, this.B);
            this.Bb = this.f.getChannel().map(FileChannel.MapMode.READ_ONLY, this.nextPos, size);
            this.nextPos += size;
        }
    
        private char readChar() {
            if (!this.Bb.hasRemaining()) {
                try {
                    this.map();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return (char) this.Bb.get();
        }
    }

}

