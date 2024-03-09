package thuchanh4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	public static void main(String args[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(1234);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            DataInputStream in = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(connectionSocket.getOutputStream());

            int a = in.readInt();
            int b = in.readInt();
            String dau =  in.readUTF();
            if(dau.equals("+")) {
            	 out.writeInt(a+b);
            }
           
            else if(dau.equals("-")) {
            	out.writeInt(a-b);
            }
            else if(dau.equals("*")) {
            	out.writeInt(a*b);
            }
            else if(dau.equals("/")) {
            	out.writeInt(a/b);
            }
//            if(s.equals("+")) {
//            	outToClient.writeInt("Tong la : " + (a + b));
//			} else if(s.equals("-")){
//				outToClient.writeUTF("Hieu la: " + (a-b));
//			} else if(s.equals("*")) {
//				outToClient.writeUTF("Tich la: " + a*b);
//			} else if(s.equals("/")) {
//				if(b != 0)outToClient.writeUTF("Thuong la: " + (a/b));
//				else {
//					outToClient.writeUTF("Gia tri b khong hop le!");
//				}
//
//            // write result to file
//           

            // close streams
          
        }
    }
}
