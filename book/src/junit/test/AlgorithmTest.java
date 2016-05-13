package junit.test;

public class AlgorithmTest {

    public static int test(int money){
        if(money<0){
            throw new RuntimeException("无效金额");
        }
        if(money>0){
            money = money * 2  - 1;
        }
        return money;
    }
    
    
    public static void main(String[] args) {
       String x = new String("123");
       x = String.valueOf(x);
       String y = "123";
       System.err.println(x==y);
    }
}
