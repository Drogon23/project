import java.util.*;
//100점
class Main {

	public static class NumA implements Comparable<NumA>{
  public int num;
	private int num2;
		public NumA(){
			this.num  =0;
			this.num2 = 0;
		}
		public void setNum(int n){
			this.num = n;
		}
	public void setNum2(int n){
		if(n<10){
			this.num2 = n*10+n;
		}else{
			this.num2 = n;
		}
		
	}
	@Override
    public int compareTo(NumA b) {
        return this.num2 - b.num2; //자신이 앞에 있는게 ascending order
    }
}

  public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a= scan.nextInt();
		scan.nextLine();
		NumA[] arr = new NumA[a];
		for(int i=0; i<a; i++){
			arr[i] = new NumA();
			arr[i].setNum(scan.nextInt());
			arr[i].setNum2(arr[i].num);
		
		}
		Arrays.sort(arr);
		String min ="";
		String max = "";
		for(int i=0; i<a; i++){
				min += Integer.toString(arr[i].num);
			max += Integer.toString(arr[a-i-1].num);
		}
		long sum =Long.parseLong(min)+Long.parseLong(max);
		System.out.println(sum);
	}
	
}