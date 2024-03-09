package thuchanh4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calculator extends JFrame {

	private JPanel contentPane;
	private JTextField atf;
	private JTextField btf;
	private JTextField kqtf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
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
	public Calculator() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mini Calculator ");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel alb = new JLabel("Nhap a: ");
		alb.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		alb.setBounds(10, 27, 94, 30);
		contentPane.add(alb);
		
		JLabel blb = new JLabel("Nhap b: ");
		blb.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		blb.setBounds(10, 80, 94, 30);
		contentPane.add(blb);
		
		atf = new JTextField();
		atf.setBounds(140, 27, 286, 30);
		contentPane.add(atf);
		atf.setColumns(10);
		
		btf = new JTextField();
		btf.setBounds(140, 80, 286, 27);
		contentPane.add(btf);
		btf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ket qua: ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 132, 94, 27);
		contentPane.add(lblNewLabel);
		
		kqtf = new JTextField();
		kqtf.setEditable(false);
		kqtf.setBounds(140, 133, 286, 30);
		contentPane.add(kqtf);
		kqtf.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 183, 436, 12);
		contentPane.add(separator);
		
		JButton btcong = new JButton("+");
		btcong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double a = Double.parseDouble(atf.getText());
					double b= Double.parseDouble(btf.getText());					
					double kq= a+b;
					kqtf.setText(String.valueOf(kq));
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Nhap hai so can tính");
				}
					
			
			}
		});
		btcong.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btcong.setBounds(0, 205, 88, 37);
		contentPane.add(btcong);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double a = Double.parseDouble(atf.getText());
					double b= Double.parseDouble(btf.getText());
					double kq= a-b;
					kqtf.setText(String.valueOf(kq));
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Phai nhap day du hai so");
				}
				
			}
			
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton_1.setBounds(86, 205, 88, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnhan = new JButton("*");
		btnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double a = Double.parseDouble(atf.getText());
					double b= Double.parseDouble(btf.getText());
					double kq= a*b;
					kqtf.setText(String.valueOf(kq));
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Phai nhap day du hai so");
				}
				
			}
				
		});
		btnhan.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnhan.setBounds(173, 205, 88, 37);
		contentPane.add(btnhan);
		
		JButton btchia = new JButton("/");
		btchia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double a = Double.parseDouble(atf.getText());
				double b= Double.parseDouble(btf.getText());
				if(b==0) {
					JOptionPane.showMessageDialog(null, "Không hợp lệ ");
				}else {
					double kq= a/b;
					kqtf.setText(String.valueOf(kq));
				}
			}
			
		});
		btchia.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btchia.setBounds(259, 205, 88, 37);
		contentPane.add(btchia);
		
		JButton btnNewButton = new JButton("Xoa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atf.setText("");
				btf.setText("");
				kqtf.setText("");
				
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnNewButton.setBounds(347, 205, 89, 37);
		contentPane.add(btnNewButton);
	}
}
