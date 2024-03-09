
	package KetNoi;

	import java.sql.Connection;

	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;

	public class DBConnection{
		
		private PreparedStatement pst = null;
		private Connection con = null;;
		private Statement stmt = null;
		private ResultSet rs = null;
		
		public DBConnection() {
			try {
				String userName = "sa";
				String password = "12345";
				String url = "jdbc:sqlserver://LAPTOP-JPJBE60E:1433;databaseName=QLDT_Ck";
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con = DriverManager.getConnection(url,userName,password);
				System.out.println("1. Connected ok");
			} catch (Exception e) {
				System.out.println("2. Connected error");
			}
		}
		
		//Load ban
		public ResultSet showTacGia() {
			try {
				
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM TacGia");
				return rs;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//Them vao bang tac gia
		public void addTacGia(int maTacGia, String tenTacGia, String ngaySinh, String gioiTinh, String dcEmail ) {
			try {
				
					pst = con.prepareStatement("INSERT INTO TacGia VALUES(?, ?, ?,?,?)");
					pst.setInt(1, maTacGia);
					pst.setString(2, tenTacGia);
					pst.setString(3, ngaySinh);
					pst.setString(4, gioiTinh);
					pst.setString(5, dcEmail);
					pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Xoa Ban
		public void Deletetg(String  maTacGia) {
			try {
				PreparedStatement stmt = con.prepareStatement("DELETE  FROM TacGia WHERE maTacGia = ?");
				stmt.setString(1, maTacGia);
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void Updatetg(int maTacGia, String tenTacGia, String ngaySinh, String gioiTinh, String dcEmail) {
			try {
				PreparedStatement stmt1 = con.prepareStatement("UPDATE TacGia set tenTacGia=?, ngaySinh=?, gioiTinh=?, dcEmail=? WHERE maTacGia=?");
				
				stmt1.setInt(5, maTacGia);
				stmt1.setString(1, tenTacGia);
				stmt1.setString(2, ngaySinh);
				stmt1.setString(3, gioiTinh);
				stmt1.setString(4, dcEmail);
				stmt1.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public  ResultSet  Searchtg(int maTacGia) {
				 try {
					stmt= con.createStatement();
					rs=stmt.executeQuery("SELECT * FROM TacGia WHERE  maTacGia='"
							+ maTacGia +"'" );
					 System.out.println("tim kiem  OK!");
					return rs;
				} catch (Exception e) {
					
				}
				return null;
				
		}
		
		//---------------------------------
		//thêm dữ liệu vào bnag dề tài sql
		public ResultSet showDeTai() {
			try {
				
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM DeTai");
				return rs;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		public void themDeTai(int maDeTai, String maTacGia, String tenDeTai, String linhVuc, String ngayNop) {
			try {
				
					pst = con.prepareStatement("INSERT INTO DeTai VALUES(?,?, ?, ?,?)");
					pst.setInt(1, maDeTai);
					pst.setString(2, maTacGia);
					pst.setString(3, tenDeTai);
					pst.setString(4, linhVuc);
					pst.setString(5, ngayNop);
					pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Xoa Ban
		public void Deletedt(int maDeTai) {
			try {
				PreparedStatement stmt = con.prepareStatement("DELETE  FROM DeTai WHERE maDeTai = ?");
				stmt.setInt(1, maDeTai);
				stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void suaDeTai(int maDeTai,String maTacGia, String tenDeTai, String linhVuc, String ngayNop) {
			try {
				PreparedStatement stmt1 = con.prepareStatement("UPDATE DeTai set maTacGia = ?,tenDeTai=?, linhVuc=?, ngayNop=? WHERE maDeTai=?");
				
				stmt1.setInt(5, maDeTai);
				stmt1.setString(1, maTacGia);
				stmt1.setString(2, tenDeTai);
				stmt1.setString(3, linhVuc);
				stmt1.setString(4, ngayNop);
				
				stmt1.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public  ResultSet  Searchdt(int maDeTai) {
			 try {
				stmt= con.createStatement();
				rs=stmt.executeQuery("SELECT * FROM DeTai WHERE  maDeTai='"
						+ maDeTai +"'" );
				 System.out.println("tim kiem  OK!");
				return rs;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
	}

		
			
		}
		
		
		

		


