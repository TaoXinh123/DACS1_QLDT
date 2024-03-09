package KetNoi;

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
import javax.swing.DefaultComboBoxModel;
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

public class QLDETAI extends JFrame {
	
	//dể gọi lớp tạo kết nối
	DBConnection con= new DBConnection();
	
	private PreparedStatement pst = null;
	//private Connection con = null;;
	private Statement stmt = null;
	private ResultSet rs = null;

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
	
	JComboBox jcbtacgia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLDETAI frame = new QLDETAI();
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
	public QLDETAI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 0, 816, 411);
		contentPane.add(tabbedPane);

		
		JPanel PANELtg = new JPanel();
		tabbedPane.addTab("Tác Giả", null, PANELtg, null);
		PANELtg.setBounds(600, 300, 550, 400);
		PANELtg.setLayout(null);
		
		JPanel pnnhap = new JPanel();
		pnnhap.setBorder(new TitledBorder(null, "Nh\u1EADp th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnnhap.setBounds(10, 29, 629, 163);
		PANELtg.add(pnnhap);
		pnnhap.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã tác giả:");
		lblNewLabel.setBounds(10, 24, 80, 33);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tác giả:");
		lblNewLabel_1.setBounds(10, 67, 87, 33);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày sinh:");
		lblNewLabel_2.setBounds(10, 110, 80, 33);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(lblNewLabel_2);
		
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
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(313, 24, 63, 33);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(lblNewLabel_3);
		
		final JLabel gttgtf = new JLabel("Giới tính:");
		gttgtf.setBounds(313, 65, 76, 33);
		gttgtf.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pnnhap.add(gttgtf);
		
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
		panel_3.setBounds(10, 226, 791, 148);
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
//		table_1.setBounds(373, 33, -315, 46);
//		panel_3.add(table_1);
		informationTacGia();  //de hien thi 
		
		JScrollPane scrollPane_1 = new JScrollPane(tabletg);
		scrollPane_1.setBounds(10, 21, 771, 118);
		panel_3.add(scrollPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ds ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_4.setBounds(649, 0, 129, 226);
		PANELtg.add(panel_4);
		panel_4.setLayout(null);
		
		JButton bttg = new JButton("Add");
		bttg.setBounds(20, 22, 88, 32);
		panel_4.add(bttg);
		bttg.addActionListener(new ActionListener() {
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
		bttg.setBackground(new Color(128, 255, 255));
		bttg.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton bttg1 = new JButton("Update");
		bttg1.setBounds(20, 64, 88, 32);
		panel_4.add(bttg1);
		bttg1.addActionListener(new ActionListener() {
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
		bttg1.setBackground(new Color(128, 255, 255));
		bttg1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton btclear = new JButton("Clear");
		btclear.setBounds(20, 147, 88, 32);
		panel_4.add(btclear);
		btclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn xóa ?")==JOptionPane.YES_OPTION) {
					matgtf.setText(" ");
					tentgtf.setText(" ");
					nstgtf.setText(" ");
					gttgtf.setText(" ");
					emailtf.setText(" ");
				}
				
			}
		});
		btclear.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btclear.setBackground(new Color(128, 255, 255));
		
		JButton bttg2 = new JButton("Delete");
		bttg2.setBounds(20, 105, 88, 32);
		panel_4.add(bttg2);
		bttg2.addActionListener(new ActionListener() {
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
		bttg2.setBackground(new Color(128, 255, 255));
		bttg2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton searchbt = new JButton("Search");
		searchbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					});
				//tim kiem ben ham main
				
		searchbt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		searchbt.setBackground(new Color(128, 255, 255));
		searchbt.setBounds(20, 189, 88, 32);
		panel_4.add(searchbt);
		
		JLabel lblNewLabel_6 = new JLabel("THÔNG TIN TÁC GIẢ");
		lblNewLabel_6.setBounds(268, 0, 195, 30);
		PANELtg.add(lblNewLabel_6);
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JPanel panelDt = new JPanel();
		tabbedPane.addTab("Đề Tài", null, panelDt, null);
		panelDt.setLayout(null);
		// panel đựng khung nhập
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nh\u1EADp th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_5.setBounds(10, 26, 667, 133);
		panelDt.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel madtlb = new JLabel("Mã đề tài:");
		madtlb.setBounds(10, 5, 80, 34);
		madtlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(madtlb);
		
		JLabel tendtlb = new JLabel("Tên đề tài:");
		tendtlb.setBounds(10, 42, 80, 34);
		tendtlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(tendtlb);
		
		madttf = new JTextField();
		madttf.setBounds(100, 10, 206, 26);
		panel_5.add(madttf);
		madttf.setColumns(10);
		
		tendttf = new JTextField();
		tendttf.setBounds(100, 46, 206, 26);
		tendttf.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_5.add(tendttf);
		tendttf.setColumns(10);
		
		JLabel lvlb = new JLabel("Lĩnh vực:");
		lvlb.setBounds(348, 26, 74, 34);
		lvlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_5.add(lvlb);
		
		JLabel ngaynlb = new JLabel("Ngày nộp:");
		ngaynlb.setFont(new Font("Times New Roman", Font.BOLD, 16));
		ngaynlb.setBounds(348, 75, 74, 34);
		panel_5.add(ngaynlb);
		
		lvdttf = new JTextField();
		lvdttf.setBounds(432, 31, 206, 26);
		panel_5.add(lvdttf);
		lvdttf.setColumns(10);
		
		ngayndttf = new JTextField();
		ngayndttf.setBounds(432, 80, 206, 26);
		panel_5.add(ngayndttf);
		ngayndttf.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Mã tác giả:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 74, 80, 37);
		panel_5.add(lblNewLabel_4);
		
		jcbtacgia = new JComboBox();
		jcbtacgia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcbtacgia.setBounds(100, 82, 206, 26);
		panel_5.add(jcbtacgia);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ds ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_6.setBounds(687, 10, 114, 240);
		panelDt.add(panel_6);
		panel_6.setLayout(null);
		
		//---------------------------
		tabledt = new JTable();
		tabledt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//de hien thong tin len bang
				int row=tabledt.getSelectedRow();
				if(row>=0) {
					
					madttf.setText(tabledt.getValueAt(row,0).toString());
					matgtf.setText(tabledt.getValueAt(row,1).toString());
					tendttf.setText(tabledt.getValueAt(row,2).toString());
					lvdttf.setText(tabledt.getValueAt(row,3).toString());
					//gtcb.setSelectedItem(tabledt.getValueAt(row,3).toString());
					ngayndttf.setText(tabledt.getValueAt(row,4).toString());
					
				}
			}
		});
		tabledt.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Mã Đề Tài", "Mã Tác giả", "Tên Đề Tài", "Lĩnh vực", "Ngày Nộp"
			}
		));
		informationDeTai();
		
		JButton adddt = new JButton("Add");
		adddt.setBounds(10, 21, 89, 32);
		panel_6.add(adddt);
		adddt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn muốn thêm ?")==JOptionPane.YES_OPTION) {
					try {
						int maDeTai=Integer.parseInt(madttf.getText());
						String maTacGia = jcbtacgia.getSelectedItem().toString();
						String tenDeTai=tendttf.getText();
						String linhVuc=lvdttf.getText();
						String ngayNop=ngayndttf.getText();
						con.themDeTai(maDeTai, maTacGia, tenDeTai, linhVuc, ngayNop);// them vo sql
						
						informationDeTai();//goi phuong thuc de hien thi thong tin ra bang
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Không thêm được");
					}
				}
			}
		});
		adddt.setBackground(new Color(128, 255, 255));
		adddt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton updatedt = new JButton("Update");
		updatedt.setBounds(10, 63, 89, 32);
		panel_6.add(updatedt);
		updatedt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Bạn thật sự muốn sửa ?")==JOptionPane.YES_OPTION) {
					try {
						//lay du lieu tu tf 
						int maDeTai=Integer.parseInt(madttf.getText());
						String tenDeTai=tendttf.getText();
						String maTacGia = jcbtacgia.getSelectedItem().toString();
						String linhVuc=lvdttf.getText();
						String ngayNop=ngayndttf.getText().toString();
						
						con.suaDeTai(maDeTai,maTacGia, tenDeTai, linhVuc, ngayNop);// them vo sql
						
						informationDeTai();
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		updatedt.setBackground(new Color(128, 255, 255));
		updatedt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		
		JButton deletedt = new JButton("Delete");
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
		deletedt.setBounds(10, 105, 89, 32);
		panel_6.add(deletedt);
		deletedt.setBackground(new Color(128, 255, 255));
		deletedt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		
		JButton cleardt = new JButton("Clear");
		cleardt.setBounds(10, 147, 89, 32);
		panel_6.add(cleardt);
		cleardt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				madttf.setText(" ");
				tendttf.setText(" ");
				lvdttf.setText(" ");
				ngayndttf.setText(" ");
			}
		});
		cleardt.setBackground(new Color(128, 255, 255));
		cleardt.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JButton searchdtbt = new JButton("Search");
		searchdtbt.setBounds(10, 189, 89, 32);
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
		searchdtbt.setBackground(new Color(128, 255, 255));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Danh s\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_7.setBounds(10, 169, 675, 201);
		panelDt.add(panel_7);
		panel_7.setLayout(null);
		
	
		tabledt = new JTable();
		//tabledt.setBounds(26, 25, 628, 32);
		tabledt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//de hien thong tin len bang
				int row=tabledt.getSelectedRow();
				if(row>=0) {
					
					madttf.setText(tabledt.getValueAt(row,0).toString());
					tendttf.setText(tabledt.getValueAt(row,1).toString());
					lvdttf.setText(tabledt.getValueAt(row,2).toString());
					ngayndttf.setText(tabledt.getValueAt(row,3).toString());
					
				}
			}
		});
		
		tabledt.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Mã đề tài","Mã tác giả", "T\u00EAn \u0111\u1EC1 t\u00E0i", "L\u0129nh v\u1EF1c", "Ng\u00E0y n\u1ED9p"
			}
		));
		
		//gọi phương thức để hiển thị lên bảng nhưng phải nằm dưới cái bảng ni để dễ quaner lí mà phai nằm trong panel của đề tài
		
		informationDeTai();
		combobox();
		
		panel_7.add(tabledt);
		JScrollPane scrollPane = new JScrollPane(tabledt);
		scrollPane.setBounds(10, 22, 655, 159);
		panel_7.add(scrollPane);
		
		JLabel lblNewLabel_5 = new JLabel("THÔNG TIN ĐỀ TÀI");
		lblNewLabel_5.setBounds(292, 0, 181, 30);
		panelDt.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
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
		
		
	}
	//tao phuong thuc de do du lieu vao bang
private void informationTacGia() {
		
		ResultSet rs = con.showTacGia();
		DefaultTableModel dtm = (DefaultTableModel) tabletg.getModel();
		dtm.setRowCount(0);

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


/*---PHƯƠNG THỨC ĐỔ DỮ LIỆU TỪ CSDL CHO COMBOBOX-------------
-------------------------------------------------------------*/	
	public void combobox() {
		Connection con;
		DefaultComboBoxModel model = (DefaultComboBoxModel) jcbtacgia.getModel();
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
				jcbtacgia.addItem(matacgia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
