package mechanisms;

import java.io.*;
import java.lang.ref.Cleaner;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface OStream {

    void create(String f_path) throws IOException;

    void writeln(String string) throws IOException;

    void close() throws IOException;

    char EOL = '\n';

    public class OStream1 implements OStream { // Simple output mechanism
        private FileWriter f;

        public void create(String f_path) throws IOException {
            f = new FileWriter(f_path);
        }

        public void writeln(String str) throws IOException { 
        // https://beginnersbook.com/2013/12/java-string-tochararray-method-example/
            for (char c : str.toCharArray()) { 
                // converting c into sequence of characters and return an Array of chars
                f.write(c);
            }
        }

        public void close() throws IOException {
            f.close();
        }
    }

    public class OStream2 implements OStream { 
        // Output mechanism with buffering mechanism
        private BufferedWriter bw;

        public void create(String f_path) throws IOException { 
            // https://www.programcreek.com/2011/03/fileoutputstream-vs-filewriter/
            this.bw = new BufferedWriter(new FileWriter(f_path));
        }

        public void writeln(String str) throws IOException {
            this.bw.write(str);
        }

        public void close() throws IOException {
            this.bw.close();
        }
    }

    public class OStream3 implements OStream { 
        // Simple output with stream equipped with a size B buffer
        private BufferedWriter bw;
        private int B;

        public OStream3(int bufferSize) {
            this.B = bufferSize;
        }

        public void create(String f_path) throws IOException {
            bw = new BufferedWriter(new FileWriter(f_path), B);
        }

        public void writeln(String str) throws IOException { // ** Add the buffer condition
            bw.write(str);
        }

        public void close() throws IOException {
            bw.close();
        }
    }

    public class OStream4 implements OStream { // Input with memory mapping
        private MappedByteBuffer mappedb;
        private RandomAccessFile f;
        private FileChannel fChannel;
        private int B;
        private int bCount;

        public OStream4(int bufferSize) {
            this.B = bufferSize;
        }

        
        public void create(String f_path) throws IOException { //https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
            // Files.deleteIfExists(Paths.get(f_path));
            this.f = new RandomAccessFile(f_path, "rw");
            this.f.setLength(0);
            this.fChannel = this.f.getChannel();
            //this.fChannel.truncate(0);
            this.bCount = 0;
            this.map(this.B); 
        }
        
        public void writeln(String line) throws IOException { 
            for (char c : line.toCharArray()) {
                putChar(c); 
            } // putChar(EOL); 
        }
        
        public void close() throws IOException {
            // char x = ' ';
            // while (this.mappedb.hasRemaini ng()) {
                // 
                // this.mappedb.put((byte) x);
            // }
            int len = this.bCount * this.B - this.mappedb.remaining();
            // Cleaner cleaner = ((sun.nio.ch.DirectBuffer) mappedb).cleaner();
            // if (cleaner != null) {
                // cleaner.clean();
            // }

            //this.fChannel.truncate(len); 
            //this.f.setLength(len);
            this.mappedb.clear(); 
            //System.out.println(this.f.length() + " and length " + len); 
            //this.fChannel.truncate(len);
            this.fChannel.close(); 
            
            // this.f.setLength(len);
            this.f.close(); 
        }
        
        private void map(int siz) throws IOException {
            int position = this.bCount * siz;
            this.mappedb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, position, siz);
            this.bCount += 1; 
        }
        
        private void putChar(char c) throws IOException {
            if (!this.mappedb.hasRemaining()) { 
                try {
                    this.map(this.B);
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            }
            this.mappedb.put((byte) c); 
        }
        
        }
    }

