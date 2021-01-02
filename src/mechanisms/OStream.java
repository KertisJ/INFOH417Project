package mechanisms;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

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

        public void writeln(String str) throws IOException { // https://beginnersbook.com/2013/12/java-string-tochararray-method-example/
            for (char c : str.toCharArray()) { // converting c into sequence of characters and return an Array of chars
                f.write(c);
            }
        }

        public void close() throws IOException {
            f.close();
        }
    }

    public class OStream2 implements OStream { // Output mechanism with buffering mechanism
        private BufferedWriter bw;

        public void create(String f_path) throws IOException { // https://www.programcreek.com/2011/03/fileoutputstream-vs-filewriter/
            this.bw = new BufferedWriter(new FileWriter(f_path));
        }

        public void writeln(String str) throws IOException {
            this.bw.write(str);
        }

        public void close() throws IOException {
            this.bw.close();
        }
    }

    public class OStream3 implements OStream { // Simple output with stream equipped with a size B buffer
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

        public OStream4(int bufferSize) {
            this.B = bufferSize;
        }

        /**
         * public void create(String f_path) throws IOException { //
         * https://howtodoinjava.com/java/nio/memory-mapped-files-mappedbytebuffer/
         * this.f = new RandomAccessFile(f_path, "rw"); this.f.setLength(0);
         * this.fChannel = this.f.getChannel(); this.bCount = 0; this.map(); }
         * 
         * public void writeln(String line) { for (char c : line.toCharArray()) {
         * putChar(c); } putChar(EOL); }
         * 
         * public void close() throws IOException { int len = this.bCount * this.B -
         * this.mappedb.remaining(); //this.fChannel.map(FileChannel.MapMode.READ_WRITE,
         * len, len); this.fChannel.truncate(len); //this.f.setLength(len);
         * this.mappedb.clear(); //System.out.println(this.f.length() + " and length " +
         * len); //this.fChannel.truncate(len); this.fChannel.close(); /** long length =
         * this.f.length() - 1; do { length -= 1; this.f.seek(length); char b = (char)
         * this.f.read(); } while(length > len); this.f.setLength(length+1);
         */
        /**
         * this.f.close(); }
         * 
         * private void map() throws IOException { int position = this.bCount * this.B;
         * this.mappedb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, position,
         * this.B); this.bCount += 1; }
         * 
         * private void putChar(char c) { if (!this.mappedb.hasRemaining()) { try {
         * this.map(); } catch (IOException e) { e.printStackTrace(); } }
         * this.mappedb.put((byte) c); }
         */

        public void create(String filepath, boolean append) throws IOException {
            this.f = new RandomAccessFile(new File(filepath), "rw");
            this.fChannel = this.f.getChannel();

            if (append) {
                this.position = this.fChannel.size();
            } else {
                this.fChannel.truncate(0);
            }

            mappedb = (MappedByteBuffer) MappedByteBuffer.allocateDirect(this.B);
        }

        /**
         * Write an integer to the stream.
         * 
         * @param element Integer to write to the stream.
         * @throws IOException
         */
        @Override
        public void writeln(String line) throws IOException {
            for (char c : line.toCharArray()) {

                if (!this.mappedb.hasRemaining()) {
                    try {
                        MappedByteBuffer mb = this.fChannel.map(FileChannel.MapMode.READ_WRITE, this.position, this.B);
                        mb.put(mappedb);
                        mb.force();
                        this.mappedb = (MappedByteBuffer) MappedByteBuffer.allocateDirect(B);
                        this.position = this.position + this.B;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            putChar(EOL);

            mappedb.putchar(line);
        }

        /**
         * Close the stream.
         * 
         * @throws IOException
         */
        @Override
        public void close() throws IOException {
            if (fChannel != null) {
                MappedByteBuffer mb = fChannel.map(MapMode.READ_WRITE, position, mappedb.position());
                this.mappedb.compact();
                mb.put(mappedb);
                mb.force();
                fChannel.close();
                f.close();
            }
        }
    }
}
