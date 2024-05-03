package ThreadingFundamentals;

public class ThreadCreation {

	public static void main(String args[]){
		Thread thread = new Thread(new Runnable() { // By creating Object for Thread Class
			
			@Override
			public void run() {
				System.out.println("New Thread "+ Thread.currentThread().getName());
				
			}
		});
		thread.setName("Thread - 1");
		thread.setPriority(thread.MAX_PRIORITY);
		
		System.out.println("Current Thread " + Thread.currentThread().getName());
		thread.start();
		System.out.println("Current Thread " + Thread.currentThread().getName());
		
		
		Thread s = new ThreadClass();
		s.start();
		
	}
	
	public static class ThreadClass extends Thread{
		@Override
		public void run() {
			System.out.print("Thread From Class"+ Thread.currentThread().getName());
		}
	}
}
