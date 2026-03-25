import java.util.Scanner;

public class boo{
    public static void main(String[] args){
        System.out.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 분리하여 입력하세요.");
        
        Scanner Scanner = new Scanner(System.in);
        String name = Scanner.next(); // 문자열 읽기
        System.out.println("당신의 이름은 "+name+"입니다");
        String city = Scanner.next();
        System.out.println("당신이 사는 도시는 "+city+"입니다");
        int age = Scanner.nextInt(); // 정수 읽기
        System.out.println("당신의 나이는 "+age+"살입니다 ");
        double weight = Scanner.nextDouble(); // 상수 읽기
        System.out.println("당신의 체중은 "+weight+"kg입니다 ");
        boolean Single = Scanner.nextBoolean(); // 논리값 읽기
        System.out.println("당신의 독신 여부는 "+ Single +"입니다.");
        
        Scanner.close();
    }
}
