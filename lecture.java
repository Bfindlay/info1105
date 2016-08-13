public class lecture{
    
    public static int recurse(int x, int n){
            return (x<=0)? -1 : (x*recurse(x,n-1));   
    }

    public static void main(String[] args){
        System.out.println(recurse(10,20));
    }

}
