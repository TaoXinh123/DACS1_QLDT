package mvc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection{
	
	private PreparedStatement pst = null; // phương thức thực thi các câu truy vấn
	private Connection con = null;; //tạo biến connection ban đầu 
	private Statement stmt = null; //dùng để hỗ trợ thực thi câu lệnh truy vấn 
	private ResultSet rs = null;
	
	public DBconnection() {
		//đk sql vs drivermanager:>>>quản li hết tất cả
		try {
			String userName = "sa";
			String password = "12345";
			String url = "jdbc:sqlserver://LAPTOP-JPJBE60E:1433;databaseName=QLDT_Ck";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//tạo kết nối , grtconection là lấy ra để kết nối
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("1. Connected ok");
		} catch (Exception e) {
			System.out.println("2. Connected error");
		}
	}
	
	// resultset là phương thức dùng để lưu trữ kết quả khi truy vấn
	
	public ResultSet showTacGia() {
		try {
			
			stmt = con.createStatement(); // cung cấp đối tượng đê truy vấn cho resultset
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
			// preparestatement dùng để thực thi các câu truy vấn
			
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
	//
	public  ResultSet  Searchtg(int maTacGia) {
			 try {
				stmt= con.createStatement();// cung cấp đối tượng để truy vấn 
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
	public void addDeTai(int maDeTai, String maTacGia, String tenDeTai, String linhVuc, String ngayNop) {
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
	
	//Xoa 
	public void Deletedt(int maDeTai) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE  FROM DeTai WHERE maDeTai = ?");
			stmt.setInt(1, maDeTai);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void Updatedt(int maDeTai,String maTacGia, String tenDeTai, String linhVuc, String ngayNop) {
		try {
			PreparedStatement stmt1 = con.prepareStatement("UPDATE DeTai set maTacGia = ?,tenDeTai=?, linhVuc=?, ngayNop=? WHERE maDeTai=? ");
			
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
