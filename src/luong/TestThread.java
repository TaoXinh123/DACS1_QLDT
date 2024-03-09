package luong;

import java.util.Date;

public class TestThread extends Thread{
	TestThread(){
		super();
		
	}
	Date d=null;
	public void run() {
		while(true) {
			try {
			
				System.out.println(d);
				this.sleep(1000);
				d= new Date(System.currentTimeMillis());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}

