package ThreadingFundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultExercise {
    public static final int MAX_PASSWORD = 9999;
	public static void main(String[] args) {
	   Random random = new Random();
	   Vault vault = new Vault(random.nextInt(MAX_PASSWORD));
       List<Thread> threads = new ArrayList<Thread>();
       threads.add(new AscendingThread(vault));
       threads.add(new DescendingThread(vault));
       threads.add(new PoliceThread());
       
       for(Thread thread: threads) {
    	   thread.start();
       }
	}
	
	private static class Vault{
		private int password;
		public Vault(int password) {
			this.password = password;
		}
		
		public boolean isPasswordCorrect(int guess) {
			try {
				Thread.sleep(5);
			}
			catch(InterruptedException e) {
				
			}
			return guess == this.password;
		}
	}
	
	private static abstract class HackerThread extends Thread{
      protected Vault vault;
		public HackerThread(Vault vault) {
		this.vault = vault;
		this.setName(this.getClass().getSimpleName());
		this.setPriority(MAX_PRIORITY);
	}
		@Override
			public synchronized void start() {
			    System.out.println("Starting Thread "+this.getName());
				super.start();
			}
	}
	
	private static class AscendingThread extends HackerThread{
		public AscendingThread(Vault vault) {
			super(vault);
		}
		
		@Override
		public void run() {
			for(int i=0; i<MAX_PASSWORD; i++) {
				if(vault.isPasswordCorrect(i)) {
					System.out.println(this.getName()+ " Password Breached "+ i);
					System.exit(0);
				}
			}
			super.run();
		}
	}
	
	private static class DescendingThread extends HackerThread{
		public DescendingThread(Vault vault) {
			super(vault);
		}
		
		@Override
		public void run() {
			for(int i=MAX_PASSWORD; i>=0; i--) {
				if(vault.isPasswordCorrect(i)) {
					System.out.println(this.getName()+ " Password Breached "+ i);
					System.exit(0);
				}
			}
			super.run();
		}
	}

	private static class PoliceThread extends Thread{
		@Override
		public void run() {
			for(int i=10; i>0; i--) {
				try {
					Thread.sleep(1000);
				}
				catch(InterruptedException e) {
					
				}
				System.out.println(i);
			}
			System.out.print("Hackers are caught!!!");
			System.exit(0);
			super.run();
		}
	}
}
