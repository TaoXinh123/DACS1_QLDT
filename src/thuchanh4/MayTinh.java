package thuchanh4;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.*;

class TCPServer {
    public static void main(String args[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(1234);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            double a = inFromClient.readDouble();
			double b = inFromClient.readDouble();
           
            

            outToClient.writeUTF("Tong la: "+(a+b));

            // write result to file
           

            // close streams
          
        }
    }
}