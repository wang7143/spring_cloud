public class test {
    public static void main(String[] args) {
        int n = 10;
        int x = 4;
        int a = n++ + x;
        int b = n - ++x;
        System.out.println(a + " " +  b);
    }
}
