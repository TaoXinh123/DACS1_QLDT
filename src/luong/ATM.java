package luong;

import java.util.Scanner;

public class ATM {
	private static int sodu = 10000;
	static int tienrut, tiennap;
	
	
	public ATM() {
		System.out.println("Số dư tài khoản là:" + (sodu));
	}
	
	public synchronized void rutTien(int tienrut) {
		while(tienrut > sodu) {
			System.out.println("Khong du tien rut!");
			try {
				wait(); //cho nap tien
			} catch (Exception e) {
				// TODO: handle exception
			} 
			sodu -= tienrut;
			System.out.println("Rut tien thanh cong. So du hien tai: " + (sodu ));
			
		}
		sodu -= tienrut;
		System.out.println("Rut tien thanh cong. So du hien tai: " + (sodu ));
		//System.exit(0);
	}
	
	public synchronized void napTien(int tiennap) {
		while(sodu < tienrut) {
			sodu += tiennap;
			System.out.println("Nap tien thanh cong. So du hien tai: " + (sodu ));
			notify(); //thong bao da nap tien
		}
		
	}

	public static void main(String[] args) {
		final ATM c = new ATM();
		Thread t1 = new Thread() {
		public void run() {
				Scanner sc = new Scanner(System.in);
				tienrut = sc.nextInt();
				c.rutTien(tienrut);
				
			}
		};
		t1.start();
		
		Thread t2 = new Thread() {
		public void run() {
				Scanner sc = new Scanner(System.in);
				tiennap = sc.nextInt();
				c.napTien(tiennap);
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					
					}	
				
		};
		t2.start();
	}

	//dọc ghi cac doi tượng 
}