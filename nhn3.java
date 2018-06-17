import java.util.*;
class Main {
//100Á¡
  public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		scan.nextLine();
		long[] arr = new long[a];
		for(int i=0; i<a; i++){
			arr[i] = scan.nextLong();
		}
		Arrays.sort(arr);
			long x1 = arr[0];
		long x2 =arr[1];
		long distance = x2-x1;
		for(int i=2; i<a; i++){
			if(arr[i]-arr[i-1]<distance){
				distance = arr[i]-arr[i-1];
				x2 = arr[i];
				x1 = arr[i-1];
			}
			
		}
		System.out.println(x1+" "+x2);
		
  }
}
