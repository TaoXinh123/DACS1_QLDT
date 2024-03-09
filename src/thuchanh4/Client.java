package thuchanh4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		double a, b;
		String  result;
		String dau= null;
		Scanner sc = new Scanner(System.in);
		Socket con = new Socket("localhost", 2345);
		
		DataInputStream in = new DataInputStream(con.getInputStream());
		
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		System.out.println("Nhap a: ");
		a = sc.nextDouble();
		System.out.println("Nhap b: ");
		b = sc.nextDouble();
		System.out.println("Nhap dau(+,-,*,/): ");
		dau = sc.next();
		out.writeDouble(a);
		out.writeDouble(b);
		out.writeUTF(dau);
		
		result = in.readUTF();
		System.out.println("From server: " + result);
		con.close();
	}

}