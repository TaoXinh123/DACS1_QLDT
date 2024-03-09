package mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class QuanLyDeTai extends JFrame {
	
	//dể gọi lớp tạo kết nối
	DBconnection con= new DBconnection();
	
	//---------------
	
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField matgtf;
	private JTextField tentgtf;
	private JTextField nstgtf;
	private JTextField emailtf;
	private JTextField madttf;
	private JTextField tendttf;
	private JTextField lvdttf;
	private JTextField ngayndttf;
	private JTable tabledt;
	private JTable tabletg;
	
	JComboBox matgdtcb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					QuanLyDeTai frame = new QuanLyDeTai();
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
	public QuanLyDeTai() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(" QUẢN LÝ ĐỀ TÀI KHOA HOC");
		setBounds(100, 100, 873, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//tạo ra hai nút tap chuyển trang
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 839, 411);
		contentPane.add(tabbedPane);

		
		JPanel PANELtg = new JPanel();
		tabbedPane.addTab("Tác Giả", null, PANELtg, null);
		PANELtg.setBounds(600, 300, 550, 400);
		PANELtg.setLayout(null);
		
		JPanel pnnhap = new JPanel();
		pnnhap.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nh\u1EADp th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnnhap.setBounds(10, 29, 629, 163);
		PANELtg.add(pnnhap);
		pnnhap.setLayout(null);
		
		JLabel matglb = new JLabel("Mã tác giả:");
		matglb.setBounds(10, 24, 80, 33);
		matglb.setForeground(new Color(0, 0, 0));
		matglb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(matglb);
		
		JLabel tentglb = new JLabel("Tên tác giả:");
		tentglb.setBounds(10, 67, 87, 33);
		tentglb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(tentglb);
		
		JLabel nstglb = new JLabel("Ngày sinh:");
		nstglb.setBounds(10, 110, 80, 33);
		nstglb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(nstglb);
		
		matgtf = new JTextField();
		matgtf.setBounds(100, 28, 178, 26);
		pnnhap.add(matgtf);
		matgtf.setColumns(10);
		
		tentgtf = new JTextField();
		tentgtf.setBounds(100, 71, 178, 26);
		pnnhap.add(tentgtf);
		tentgtf.setColumns(10);
		
		nstgtf = new JTextField();
		nstgtf.setBounds(100, 114, 178, 26);
		pnnhap.add(nstgtf);
		nstgtf.setColumns(10);
		
		JLabel emailtglb = new JLabel("Email:");
		emailtglb.setBounds(313, 24, 63, 33);
		emailtglb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(emailtglb);
		
		final JLabel gttglb = new JLabel("Giới tính:");
		gttglb.setBounds(313, 65, 76, 33);
		gttglb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(gttglb);
		
		emailtf = new JTextField();
		emailtf.setBounds(386, 28, 200, 26);
		pnnhap.add(emailtf);
		emailtf.setColumns(10);
		
		String n[]= {"Nam","Nữ"};
		final JComboBox gtcb = new JComboBox(n);
		gtcb.setBounds(386, 70, 103, 27);
		pnnhap.add(gtcb);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_3.setBounds(10, 212, 629, 162);
		PANELtg.add(panel_3);
		panel_3.setLayout(null);
		
		tabletg = new JTable();
		tabletg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//de hien thong tin len bang
				int row=tabletg.getSelectedRow();
				if(row>=0) {
					
					matgtf.setText(tabletg.getValueAt(row,0).toString());
					tentgtf.setText(tabletg.getValueAt(row,1).toString());
					nstgtf.setText(tabletg.getValueAt(row,2).toString());
					gtcb.setSelectedItem(tabletg.getValueAt(row,3).toString());
					emailtf.setText(tabletg.getValueAt(row,4).toString());
					
				}
			}
		});
		tabletg.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Mã tác giả", "Tác giả", "Ngày sinh", "Giới tính", "Email"
			}
		));

		informationTacGia();  //de hien thi 
		
		JScrollPane scrollPane_1 = new JScrollPane(tabletg);
		scrollPane_1.setBounds(10, 17, 609, 135);
		panel_3.add(scrollPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ds ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_4.setBounds(649, 0, 161, 293);
		PANELtg.add(panel_4);
		panel_4.setLayout(null);
		
		JButton addtg = new JButton("Add");
		addtg.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Add-icon.png"));
		addtg.setBounds(20, 22, 122, 32);
		panel_4.add(addtg);
		addtg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn thêm ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
						int maTacGia=Integer.parseInt(matgtf.getText());
						String tenTacGia=tentgtf.getText();
						String ngaySinh=nstgtf.getText();
						String gioiTinh=gtcb.getSelectedItem().toString();
						String dcEmail=emailtf.getText();
						con.addTacGia(maTacGia, tenTacGia, ngaySinh, gioiTinh, dcEmail);// them vo sql
						
						informationTacGia();//goi phuong thuc de hien thi thong tin ra bang
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Không thêm được");// để hiện thị khi k thêm đc
					}
					
				}
			}
		});
		addtg.setBackground(new Color(192, 192, 192));
		addtg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton updatetg = new JButton("Update");
		updatetg.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Actions-document-edit-icon (1).png"));
		updatetg.setBounds(20, 64, 122, 32);
		panel_4.add(updatetg);
		updatetg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn sửa ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
						int maTacGia=Integer.parseInt(matgtf.getText());
						String tenTacGia=tentgtf.getText();
						String ngaySinh=nstgtf.getText();
						String gioiTinh=gtcb.getSelectedItem().toString();
						String dcEmail=emailtf.getText();
						con.Updatetg(maTacGia, tenTacGia, ngaySinh, gioiTinh, dcEmail);// them vo sql
						
						informationTacGia();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Không sửa được");// để hiện thị khi k thêm đc
					}
				}
			}
		});
		updatetg.setBackground(new Color(192, 192, 192));
		updatetg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton cleartg = new JButton("Clear");
		cleartg.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Clear-icon.png"));
		cleartg.setBounds(20, 147, 122, 32);
		panel_4.add(cleartg);
		//addActionListener phương thức để bắt sự kiện 
		cleartg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					matgtf.setText("");
					tentgtf.setText(" ");
					nstgtf.setText(" ");
					gttglb.setText(" ");
					emailtf.setText(" ");
				
				
			}
		});
		cleartg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cleartg.setBackground(new Color(192, 192, 192));
		
		JButton deletetg = new JButton("Delete");
		deletetg.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Button-Close-icon.png"));
		deletetg.setBounds(20, 105, 122, 32);
		panel_4.add(deletetg);
		deletetg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
					String  maTacGia=matgtf.getText();
						con.Deletetg(maTacGia);
						
						informationTacGia();
					}catch(Exception e1) {
						
					}
				}
			}
		});
		deletetg.setBackground(new Color(192, 192, 192));
		deletetg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton searchtg = new JButton("Search");
		searchtg.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Zoom-icon.png"));
		searchtg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn muốn tìm kiếm?")==JOptionPane.YES_OPTION) {
				try {
					int maTacGia=Integer.parseInt(matgtf.getText()) ;					
					ResultSet rs = con.Searchtg(maTacGia);
					DefaultTableModel dtm = (DefaultTableModel)tabletg .getModel();
					dtm.setRowCount(0);	
					try {
						while(rs.next()) {
							Vector<Object> vec = new Vector<Object>();
							vec.add(rs.getInt("maTacGia"));
							 vec.add(rs.getString("tenTacGia"));
							 vec.add(rs.getString("ngaySinh"));
							 vec.add(rs.getString("gioiTinh"));
							 vec.add(rs.getString("dcEmail"));
												
							 dtm.addRow(vec);
							}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
									e1.printStackTrace();
									}
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "ko tim duoc");
							}
							}
						}
					});
				//tim kiem ben ham main
				
		searchtg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchtg.setBackground(new Color(192, 192, 192));
		searchtg.setBounds(20, 189, 122, 32);
		panel_4.add(searchtg);
		
		JButton qltgbt = new JButton("Reset");
		qltgbt.setBackground(new Color(192, 192, 192));
		qltgbt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Go-back-icon.png"));
		qltgbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationTacGia();
				
			}
		});
		qltgbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		qltgbt.setBounds(20, 231, 122, 32);
		panel_4.add(qltgbt);
		
		JLabel tttglb = new JLabel("THÔNG TIN TÁC GIẢ");
		tttglb.setBounds(268, 0, 195, 30);
		PANELtg.add(tttglb);
		tttglb.setForeground(new Color(255, 0, 0));
		tttglb.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JPanel panelDt = new JPanel();
		tabbedPane.addTab("Đề Tài", null, panelDt, null);
		panelDt.setLayout(null);
		// panel đựng khung nhập
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 26, 667, 166);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nh\u1EADp th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panelDt.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel madtlb = new JLabel("Mã đề tài:");
		madtlb.setBounds(10, 26, 80, 34);
		madtlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(madtlb);
		
		JLabel tendtlb = new JLabel("Tên đề tài:");
		tendtlb.setBounds(10, 75, 80, 34);
		tendtlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(tendtlb);
		
		madttf = new JTextField();
		madttf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		madttf.setBounds(100, 31, 206, 26);
		panel_5.add(madttf);
		madttf.setColumns(10);
		
		tendttf = new JTextField();
		tendttf.setBounds(100, 79, 206, 26);
		tendttf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel_5.add(tendttf);
		tendttf.setColumns(10);
		
		JLabel lvlb = new JLabel("Lĩnh vực:");
		lvlb.setBounds(348, 26, 74, 34);
		lvlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(lvlb);
		
		JLabel ngaynlb = new JLabel("Ngày nộp:");
		ngaynlb.setBounds(348, 75, 74, 34);
		ngaynlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(ngaynlb);
		
		lvdttf = new JTextField();
		lvdttf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lvdttf.setBounds(432, 31, 206, 26);
		panel_5.add(lvdttf);
		lvdttf.setColumns(10);
		
		ngayndttf = new JTextField();
		ngayndttf.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ngayndttf.setBounds(432, 80, 206, 26);
		panel_5.add(ngayndttf);
		ngayndttf.setColumns(10);
		
		matgdtcb = new JComboBox();
		matgdtcb.setBounds(100, 122, 206, 26);
		panel_5.add(matgdtcb);
		
		JLabel matgdtlb = new JLabel("Mã tác giả");
		matgdtlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		matgdtlb.setBounds(10, 119, 80, 37);
		panel_5.add(matgdtlb);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(687, 10, 137, 290);
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ds ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panelDt.add(panel_6);
		panel_6.setLayout(null);
		
		JButton adddt = new JButton("Add");
		adddt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Add-icon.png"));
		adddt.setBounds(10, 21, 117, 32);
		panel_6.add(adddt);
		adddt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn muốn thêm ?")==JOptionPane.YES_OPTION) {
					try {
						int maDeTai=Integer.parseInt(madttf.getText());
						String maTacGia= matgdtcb.getSelectedItem().toString();
						String tenDeTai=tendttf.getText();
						String linhVuc=lvdttf.getText();
						String ngayNop=ngayndttf.getText();
						con.addDeTai(maDeTai,maTacGia, tenDeTai, linhVuc, ngayNop);// them vo sql
						
						informationDeTai();//goi phuong thuc de hien thi thong tin ra bang
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Không thêm được");
					}
				}
			}
		});
		adddt.setBackground(new Color(192, 192, 192));
		adddt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton updatedt = new JButton("Update");
		updatedt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Actions-document-edit-icon (1).png"));
		updatedt.setBounds(10, 63, 117, 32);
		panel_6.add(updatedt);
		updatedt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn sửa ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
						int maDeTai=Integer.parseInt(madttf.getText());
						String tenDeTai=tendttf.getText();
						String maTacGia = matgdtcb.getSelectedItem().toString();
						String linhVuc=lvdttf.getText();
						String ngayNop=ngayndttf.getText().toString();
						
						con.Updatedt(maDeTai, maTacGia, tenDeTai, linhVuc, ngayNop);// them vo sql
						
						informationDeTai();
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		updatedt.setBackground(new Color(192, 192, 192));
		updatedt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		
		JButton deletedt = new JButton("Delete");
		deletedt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Button-Close-icon.png"));
		deletedt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
						int maDeTai=Integer.parseInt(madttf.getText());
						con.Deletedt(maDeTai);
						
						informationDeTai();
					}catch(Exception e1) {
						
					}
				}
			}
		});
		deletedt.setBounds(10, 105, 117, 32);
		panel_6.add(deletedt);
		deletedt.setBackground(new Color(192, 192, 192));
		deletedt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		
		JButton cleardt = new JButton("Clear");
		cleardt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Clear-icon.png"));
		cleardt.setBounds(10, 147, 117, 32);
		panel_6.add(cleardt);
		cleardt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madttf.setText("");
				tendttf.setText(" ");
				lvdttf.setText(" ");
				ngayndttf.setText(" ");
			}
		});
		cleardt.setBackground(new Color(192, 192, 192));
		cleardt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton searchdtbt = new JButton("Search");
		searchdtbt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Zoom-icon.png"));
		searchdtbt.setBounds(10, 189, 117, 32);
		panel_6.add(searchdtbt);
		searchdtbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn muốn tìm kiếm ?")==JOptionPane.YES_OPTION) {
				try {
					int maDeTai=Integer.parseInt(madttf.getText()) ;					
					ResultSet rs = con.Searchdt(maDeTai);
					DefaultTableModel dtm = (DefaultTableModel)tabledt .getModel();
					dtm.setRowCount(0);	
					try {
						while(rs.next()) {
							Vector<Object> vec = new Vector<Object>();
							vec.add(rs.getInt("maDeTai"));
							vec.add(rs.getString("maTacGia"));
							 vec.add(rs.getString("tenDeTai"));
							 vec.add(rs.getString("linhVuc"));
							 vec.add(rs.getString("ngayNop"));
							
							 dtm.addRow(vec);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Không tìm được!");
					}
				}
			}
		});
		searchdtbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchdtbt.setBackground(new Color(192, 192, 192));
		
		JButton qldtbt = new JButton("Reset");
		qldtbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationDeTai();
			}
		});
		qldtbt.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\Saved Pictures\\Go-back-icon.png"));
		qldtbt.setBackground(new Color(192, 192, 192));
		qldtbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		qldtbt.setBounds(10, 231, 117, 32);
		panel_6.add(qldtbt);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 202, 675, 172);
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panelDt.add(panel_7);
		
	
		tabledt = new JTable();
		tabledt.setBounds(1, 25, 653, 16);
		tabledt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//de hien thong tin len bang
				int row=tabledt.getSelectedRow();
				if(row>=0) {
					//dùng để kích chuột vào bảng thì dữ liệu sẽ đổ lên tf 
					madttf.setText(tabledt.getValueAt(row,0).toString());
					matgdtcb.setSelectedItem(tabledt.getValueAt(row,1).toString());
					tendttf.setText(tabledt.getValueAt(row,2).toString());
					lvdttf.setText(tabledt.getValueAt(row,3).toString());
					ngayndttf.setText(tabledt.getValueAt(row,4).toString());
					
				}
			}
		});
		
		tabledt.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 \u0111\u1EC1 t\u00E0i", "M\u00E3 t\u00E1c gi\u1EA3 ", "T\u00EAn \u0111\u1EC1 t\u00E0i", "L\u0129nh v\u1EF1c", "Ng\u00E0y n\u1ED9p"
			}
		));
		
		//gọi phương thức để hiển thị lên bảng  phai nằm trong panel của đề tài
		
		informationDeTai();
		panel_7.setLayout(null);
		
		panel_7.add(tabledt);
		JScrollPane scrollPane = new JScrollPane(tabledt);
		scrollPane.setBounds(10, 23, 655, 139);
		panel_7.add(scrollPane);
		
		JLabel ttdtlb = new JLabel("THÔNG TIN ĐỀ TÀI");
		ttdtlb.setBounds(292, 0, 181, 30);
		panelDt.add(ttdtlb);
		ttdtlb.setForeground(new Color(255, 0, 0));
		ttdtlb.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JButton exitdtlb = new JButton("Exit");
		exitdtlb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitdtlb.setBounds(10, 421, 89, 32);
		contentPane.add(exitdtlb);
		exitdtlb.setBackground(new Color(255, 0, 128));
		exitdtlb.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		combobox();
		
	}
	//tao phuong thuc de do du lieu vao bang
private void informationTacGia() {
		
		ResultSet rs = con.showTacGia();
		DefaultTableModel dtm = (DefaultTableModel) tabletg.getModel();
		dtm.setRowCount(0);
		// vector dùng để lưu trữ dữ liệu  và thêm dữ liệu vào bảng
		
			try {
				while(rs.next()) {
                    Vector<Object> vec = new Vector<Object>();
                    vec.add(rs.getString("maTacGia"));
                    vec.add(rs.getString("tenTacGia"));
                    vec.add(rs.getString("ngaySinh"));
                    vec.add(rs.getString("gioiTinh"));
                    vec.add(rs.getString("dcEmail"));
                    dtm.addRow(vec);
					}
				} catch (SQLException e) {
						
					e.printStackTrace();
		}
	}
private void informationDeTai() {
	
		ResultSet rs = con.showDeTai();
		DefaultTableModel dtm =  (DefaultTableModel) tabledt.getModel();
		dtm.setRowCount(0);
					try {
						while(rs.next()) {
							Vector<Object> vec = new Vector<Object>();
							vec.add(rs.getInt("maDeTai"));
							vec.add(rs.getString("maTacGia"));
							vec.add(rs.getString("tenDeTai"));
							vec.add(rs.getString("linhVuc"));
							vec.add(rs.getString("ngayNop"));
               
							dtm.addRow(vec);
						}
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
		}
	}
//phương thức đổ dữ liệu từ csdl qua cho combobox
public void combobox() {
	Connection con;
	//xây dựng một đối tượng cbb trống 
	DefaultComboBoxModel model = (DefaultComboBoxModel) matgdtcb.getModel();
	model.removeAllElements();
	try {
		String userName = "sa";
		String password = "12345";
		String url = "jdbc:sqlserver://LAPTOP-JPJBE60E:1433;databaseName=QLDT_Ck";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(url,userName,password);
		
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("select maTacGia from TacGia");
		while (rs.next()) {
			String matacgia = rs.getString("maTacGia");
			
			matgdtcb.addItem(matacgia);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

