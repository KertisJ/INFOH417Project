package experiments;
import java.util.Comparator;

public class comparate implements Comparator<String>{
    public int compare(String s1, String s2) {
        String[] subStr1 = s1.split(",");
        String[] subStr2 = s2.split(",");
        String k1;
        String k2;
        try {
            k1 = subStr1[2 - 1].trim();
            k2 = subStr2[2 - 1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("ERROR: k is greater than the number of columns");
        }
        return k1.compareTo(k2);
    }
}