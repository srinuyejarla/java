import java.util.Scanner;
public class linear{
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
        System.out.println("Element n elements");
        int n=sc.nextInt();
        int a[]=new int[n];
        System.out.println("Element"+n+"elements");
        System.out.println("Element key");
        int key=sc.nextInt();
        for(int i=0;i<n;i++){
        	a[i]=sc.nextInt();
        }
        int posi= linearsearch(a,n,key);
        if(posi!=-1)
        	System.out.println("element found at index"+posi);
        else
        	System.out.println("element is not found");
    }
}
    public static int linearsearch(int[] a, int n,int key) {
        for (int i = 0; i < n; i++) {
            if (a[i] == key) {
                return i;
            }
        }
        return -1;
    }


