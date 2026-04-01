//예제 3-5
import java.util.Scanner;

public class Ex35 {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("정수를 5개 입력하세요.");
		int sum=0; //정수형 sum=0 선언
		
		for(int i=0;i<5;i++) {
			int n=scanner.nextInt();
			if(n<=0){
				continue; //0이나 음수의 경우 더하지 않고 반복 진행
			}
			else {
					sum+=n;
				 } // 양수인 경우 sum+n;
				
		}
		
		System.out.println("양수의 합은 "+sum);
		
		scanner.close();
	}

}