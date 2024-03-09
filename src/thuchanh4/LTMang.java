package thuchanh4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LTMang extends JFrame {

	private JPanel contentPane;
	private JTextField t1tf;
	private JTextField t2tf;
	private JTextField kqtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LTMang frame = new LTMang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LTMang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nhập số thứ nhất: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 27, 147, 36);
		contentPane.add(lblNewLabel);
		
		t1tf = new JTextField();
		t1tf.setBounds(177, 27, 204, 36);
		contentPane.add(t1tf);
		t1tf.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập số thứ hai: ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 87, 147, 36);
		contentPane.add(lblNewLabel_1);
		
		t2tf = new JTextField();
		t2tf.setBounds(177, 87, 204, 36);
		contentPane.add(t2tf);
		t2tf.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Kết quả:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 149, 147, 28);
		contentPane.add(lblNewLabel_2);
		
		kqtf = new JTextField();
		kqtf.setEditable(false);
		kqtf.setBounds(177, 149, 204, 36);
		contentPane.add(kqtf);
		kqtf.setColumns(10);
		
		JButton thembt = new JButton("+");
		thembt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 // create socket
	                Socket clientSocket = new Socket("localhost", 1234);

	                // get numbers from text fields
	                int num1 = Integer.parseInt(t1tf.getText());
	                int num2 = Integer.parseInt(t2tf.getText());
                    
	               
	                // send numbers to server
	                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	                outToServer.writeInt(num1);
	                outToServer.writeInt(num2);
	                outToServer.writeUTF("+");
	                // receive result from server
	                DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
	                int result = inFromServer.readInt();

	                // display result
	                kqtf.setText(Integer.toString(result));

	                // close socket
	               
				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
					//JOptionPane.showMessageDialog(null, "Nhập hai số cần tính");
				}
			}
		});
		thembt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		thembt.setBounds(0, 213, 89, 40);
		contentPane.add(thembt);
		
		JButton trubt = new JButton("-");
		trubt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s = new Socket("localhost", 1234);

	                // get numbers from text fields
	                int num1 = Integer.parseInt(t1tf.getText());
	                int num2 = Integer.parseInt(t2tf.getText());
                    
	                // send numbers to server
	                DataOutputStream out = new DataOutputStream(s.getOutputStream());
	                out.writeInt(num1);
	                out.writeInt(num2);
	                out.writeUTF("-");
	                // receive result from server
	                DataInputStream in = new DataInputStream(s.getInputStream());
	                int result = in.readInt();

	                // display result
	                kqtf.setText(Integer.toString(result));
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Phải nhập đầy đủ hai số ");
				}
				
			}
		});
		trubt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		trubt.setBounds(100, 213, 89, 40);
		contentPane.add(trubt);
		
		JButton nhanbt = new JButton("*");
		nhanbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket s= new Socket("localhost", 1234);
					
					int num1=Integer.parseInt(t1tf.getText());
					int num2=Integer.parseInt(t2tf.getText());
					DataOutputStream out= new DataOutputStream(s.getOutputStream());
					out.writeInt(num1);
					out.writeInt(num2);
					out.writeUTF("*");
					
					DataInputStream in= new DataInputStream(s.getInputStream());
					int result = in.readInt();
					kqtf.setText(Integer.toString(result));
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		nhanbt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nhanbt.setBounds(198, 213, 89, 40);
		contentPane.add(nhanbt);
		
		JButton chiabt = new JButton("/");
		chiabt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Double.parseDouble(t1tf.getText());
				double b= Double.parseDouble(t2tf.getText());
				if(b==0) {
					JOptionPane.showMessageDialog(null, "Không hợp lệ ");
				}else {
					double kq= a/b;
					kqtf.setText(String.valueOf(kq));
				}
			}
			
		});
		chiabt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chiabt.setBounds(297, 213, 89, 40);
		contentPane.add(chiabt);
	}
	public void actionPerformed(ActionEvent e) {
        try {
            
               
//            } else if (e.getSource() == readButton) {
//                // read result from file
//                FileInputStream fis = new FileInputStream(resultFile);
//                DataInputStream dis = new DataInputStream(fis);
//                int result = dis.readInt();
//
//                // display result
//                kqtf.setText(Integer.toString(result));
//
//                // close streams
//                dis.close();
//                fis.close();
//            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
