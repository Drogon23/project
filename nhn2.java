import java.util.*;
class Main {
//100Á¡
  public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num  = scan.nextInt();
		
		int check = -1;
		for(int i=0; i<3; i++){
			num = Fel(num);
			if(num>=10000){
				System.out.println("-1");
				return;
			}
			check = checkNum(num);
			if(check == 1){
				System.out.println(num);
				return;
			}
		}
		System.out.println("-1");
		return;
		
  }
	
	public static int checkNum(int n){
		String temp = Integer.toString(n);
		String reverse = "";
		for(int i=temp.length()-1; i>=0; i--){
			reverse += temp.charAt(i);
		}
		int revn = Integer.parseInt(reverse);
		if(n == revn){
			return 1;
		}else{
			return -1;
		}
	}
	public static int Fel(int n){
		String temp = Integer.toString(n);
		String reverse = "";
		for(int i=temp.length()-1; i>=0; i--){
			reverse += temp.charAt(i);
		}
		int sum = Integer.parseInt(reverse)+n;
		return sum;
	}
}
