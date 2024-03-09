package thuchanh4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {

	public static void main(String[] args) throws Exception {
		double a,b;
		String s;
		ServerSocket ser = new ServerSocket(1234);
		//InetSocketAddress addr=
		while(true) {
			System.out.println("Server ready...");
			Socket con = ser.accept();
			System.out.println("Đã kết nối:" + con);

			DataInputStream in = new DataInputStream(con.getInputStream());
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			a = in.readDouble();
			b = in.readDouble();
			s = in.readUTF();
			
			System.out.println("From client " + a + " and " + b);
			out.writeUTF("tong la: " + (a+b));
			if(s.equals("+")) {
				out.writeUTF("Tong la : " + (a + b));
			} else if(s.equals("-")){
				out.writeUTF("Hieu la: " + (a-b));
			} else if(s.equals("*")) {
				out.writeUTF("Tich la: " + a*b);
			} else if(s.equals("/")) {
				if(b != 0)out.writeUTF("Thuong la: " + (a/b));
				else {
					out.writeUTF("Gia tri b khong hop le!");
				}
			}
			
		}
	}

}