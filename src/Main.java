import calls.*;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char cont;
        int choise;
        do {
        System.out.print("1. Experiment1 : Sequential reading\n2. Experiment2 : Random reading\n3. Experiment3 : Combined reading and writing\n4. Experiment4 : Multi-way merge\n\nWhat experiment do you want to run? _ ");
        choise = sc.nextInt ();
        switch (choise) {
            case 1:
                callExp1.main(null);
                break;
            case 2:
                callExp2.main(null);
                break;
            case 3:
                callExp3.main(null);
                break;
            case 4:
                callExp4.main(null);
                break;
            default:
                throw new IllegalArgumentException("You must enter a value between 1 and 4.");
        }

        System.out.print("\nTry another experiment? (Y/N) _ ");
        cont = (char) System.in.read();
        System.out.print("\n");
    } while (cont == 'Y' || cont == 'y');

    }
}
