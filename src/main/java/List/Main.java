package List;

public class Main {
    public static void main(String[] args) {
        final int N = 10;
        List l = new List(N);
        for (int i = 0; i < N/2; ++i) {
            l.addNumber( (1 << i) );
        }
        l.addNumber(2);
        l.addNumber(8);
        l.write();
        l.cancelFirst(2);
        l.write();
        for (int i = 0; i < N/2; ++i) {
            l.addNumber( (1 << i) );
        }
        l.write();
        System.out.println("Po usunięciu powtórzeń:");
        l.cancelRepeat();
        l.write();
    }
}
