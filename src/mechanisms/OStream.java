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
            for (char ch : str.toCharArray()) {
                bw.write(ch);
            }
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
        private long position = 0;
        private long l;

        public OStream4(int bufferSize) {
            this.B = bufferSize;
        }

        
        public void create(String f_path) throws IOException { //https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
            //Files.deleteIfExists(Paths.get(f_path));
            this.f = new RandomAccessFile(f_path, "rw");
            try {
                this.f.setLength(0);   
            } catch (Exception e) {
                //TODO: handle exception
            } finally {
                this.fChannel = this.f.getChannel();
                // this.fChannel.truncate(0);
                this.bCount = 0;
                // long s = Math.min(this.B, this.l.length());
                // this.position = this.bCount * this.B;
                // this.mappedb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, this.position, this.B);
                // this.bCount += 1; 
                // this.position = this.bCount * this.B;
                this.map(); 
            }
        }
        
        public void writeln(String line) throws IOException { 
            this.l = line.length();

            for (char c : line.toCharArray()) {
                writeChar(c); 
            } // putChar(EOL); 
            // this.l = 1;
        }
        
        public void close() throws IOException {
            char x = ' ';
            while (this.mappedb.hasRemaining()) {
                this.mappedb.put((byte) x);
            }
            this.mappedb.force();
            int len = this.bCount * this.B - this.mappedb.remaining();
            // Cleaner cleaner = ((sun.nio.ch.DirectBuffer) mappedb).cleaner();
            // if (cleaner != null) {
                // cleaner.clean();
            // }
            try {
                this.fChannel.truncate(len);
            } catch (Exception e){

            }finally{        
                //this.f.setLength(len);
                this.fChannel.close();
                this.mappedb.clear(); 
                //this.mappedb = null;
                //System.out.println(this.f.length() + " and length " + len); 
                //this.fChannel.truncate(len); 
                // this.f.setLength(len);
                this.f.close();
            } 
        }
        
        private void map() throws IOException {
            //long s = Math.min(this.B, this.l);
            this.position = this.bCount * this.B;
            this.mappedb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, this.position, this.B);
            //this.mappedb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, this.position, s);
            // this.position = this.bCount * s;
            this.bCount += 1;
        }
        
        private void writeChar(char c) throws IOException {
            if (!this.mappedb.hasRemaining()) {
                try {
                    this.map();
                } catch (IOException e) { 
                    e.printStackTrace(); 
                } 
            }
            this.mappedb.put((byte) c);
            this.l = this.l - 1;
        }
        
        }
    }

