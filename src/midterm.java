import java.util.Arrays;
import java.util.Scanner;

public class midterm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		System.out.println("java Midterm.java ");
        System.out.print("시작수 를 입력하세요 : ");
        int start = sc.nextInt();
        System.out.print("끝수 를 입력하세요  : ");
        int end = sc.nextInt();
        System.out.print("찾고자하는 배수를 입력하세요 : ");
        int multiple = sc.nextInt();

       
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

       
        int[] tempArr = new int[end - start + 1];
        int index = 0;

        for (int i = start; i <= end; i++) {
            if (i % multiple == 0) {
                tempArr[index++] = i;
            }
       
        }

      
        int[] resultArr = Arrays.copyOf(tempArr, index);

       System.out.println(start + "에서 " + end + "까지 사시의 " + multiple + "의 배수는 " + index +  "개입니다");
        System.out.println("그 수는 다음과 같습니다  ");
        System.out.println(Arrays.toString(resultArr));

        sc.close();
    }
}