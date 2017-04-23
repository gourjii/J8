
public class LambdaTest {
	interface Operation{
		  int doSmth(int a, int b);
	  }
	
	public static void test(){
	Operation addLambda = (int a, int b) -> a + b;
	  
	Operation addAnonym = new Operation(){
		@Override
		public int doSmth(int a, int b){
			return a+b;
		}
	};
	
	System.out.println("---Lambda---");
	System.out.println(addLambda.doSmth(5,4));
	System.out.println(addAnonym.doSmth(3,4));
	}
}
