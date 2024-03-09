package thuchanh4;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class TCPClient extends JFrame implements ActionListener {
    private JTextField num1Field, num2Field, resultField;
    private JButton calculateButton, readButton;
    private File resultFile = new File("result.txt");

    public TCPClient() {
        super("TCP Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // create text fields
        num1Field = new JTextField();
        num2Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        // create buttons
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        readButton = new JButton("Read from file");
        readButton.addActionListener(this);

        // add components to frame
        Container c = getContentPane();
        c.setLayout(new GridLayout(4, 2));
        c.add(new JLabel("Number 1:"));
        c.add(num1Field);
        c.add(new JLabel("Number 2:"));
        c.add(num2Field);
        c.add(calculateButton);
        c.add(resultField);
        c.add(readButton);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == calculateButton) {
                // create socket
                Socket clientSocket = new Socket("localhost", 6789);

                // get numbers from text fields
                int num1 = Integer.parseInt(num1Field.getText());
                int num2 = Integer.parseInt(num2Field.getText());

                // send numbers to server
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeInt(num1);
                outToServer.writeInt(num2);

                // receive result from server
                DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
                int result = inFromServer.readInt();

                // display result
                resultField.setText(Integer.toString(result));

                // close socket
                clientSocket.close();
            } else if (e.getSource() == readButton) {
                // read result from file
                FileInputStream fis = new FileInputStream(resultFile);
                DataInputStream dis = new DataInputStream(fis);
                int result = dis.readInt();

                // display result
                resultField.setText(Integer.toString(result));

                // close streams
                dis.close();
                fis.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        TCPClient frame = new TCPClient();
        frame.setVisible(true);
    }
}