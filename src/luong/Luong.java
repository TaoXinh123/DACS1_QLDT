package luong;
import java.util.Date;
import java.util.Scanner;  
public class Luong {
	public Luong() {
		super();
		
		L1 t1 = new L1();
		L2 t2 = new L2();
		L3 t3 = new L3();
		
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		//hien hi ket qua
		System.out.println("Dien tich hinh chu nhat la: "+getDt() );
		System.out.println("Chu vi hinh chu nhat la: "+ getCv());
		//System.out.println(dt);
		//System.out.println(cv);
	}
	
	
	
	double dai, rong, cv, dt;
	Date d=null;
	public double getDai() {
		return dai;
	}
	public void setDai(double dai) {
		this.dai = dai;
	}
	public double getRong() {
		return rong;
	}
	public void setRong(double rong) {
		this.rong = rong;
	}
	public double getCv() {
	return cv;
	}
	public void setCv(double cv) {
	this.cv = cv;
	}
	public double getDt() {
	return dt;
	}
	public void setDt(double dt) {
	this.dt = dt;
	}
	class L1 extends Thread {
		public void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap dai: ");
		dai = sc.nextDouble();
		System.out.println("Nhap rong: ");
		rong = sc.nextDouble();
	
	}
}
	
	class L2 extends Thread{
		public void run() {
			dt = dai*rong;
			
		}
	}
	
	class L3 extends Thread{
		public void run() {
			cv = (dai + rong)*2;
			
		}
	}
	
	
	public static void main(String[] args) {
           new Luong();
           
           
	}
    
}
