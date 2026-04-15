class Circle4_6{
	int radius;
	public Circle4_6(int radius) {
		this.radius = radius;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
}

public class CircleArray {

	public static void main(String[] args) {
		Circle4_6[] c; // Circle 배열에 대한 레퍼런스 c 선언
		c=new Circle4_6[5]; // 5개의 레퍼런스 배열 생성
		
		for(int i=0;i<c.length;i++) { //배열개수만큼
			c[i]=new Circle4_6(i); // i 번쨰 원소 객체 생성
		}
		for(int i=0;i<c.length;i++) { // 배열의 모든 Circle 객체의 면적 풀력    
			System.out.print((int)(c[i].getArea())+" ");
		}
	}

}