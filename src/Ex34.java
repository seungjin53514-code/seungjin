public class Ex34{
    public static void main(String[] args) {
        System.out.println("구구단을 가로로 출력하는 프로그램");
        System.out.println( "[2단]" + "\t" + "[3단]" + "\t"+ "[4단]" + "\t"+ "[5단]" + "\t"
+ "[6단]" + "\t" + "[7단]" + "\t" + "[8단]" + "\t" + "[9단]");       
		for(int i=1; i<10;i++) {          
			for(int j=2;j<10;j++) {
				System.out.print(j+"X"+i+"="+i*j); //구구셈 출력   
                System.out.print("\t");            			
			}
              
            System.out.println("\t");
			
		}
        
	

}

}