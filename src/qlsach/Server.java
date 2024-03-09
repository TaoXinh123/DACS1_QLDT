package qlsach;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {
	static DBConnection con= new DBConnection();
	static PreparedStatement pre= null;
	static ResultSet rs=null;
	
	private static final int PORT = 12345;
	private static ArrayList<Sach> sachList = new ArrayList<>();
	static ArrayList<Client> listCl = new ArrayList<Client>();
	static File file = null;
	static ObjectInputStream ois = null;
	static ObjectOutputStream oos = null;
	static FileOutputStream fos = null;
	static FileInputStream fis = null;
//doc dl input output la kieu du lieu byte
	
	public static void main(String[] args) {
		try {
			loadFromFile(); 
			//tao mot socket cua server de lang nghe ket noi tu client
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server ready ... ");

			while (true) {
				//tao socket chap nhan cac ket noi tu client
				Socket socket = serverSocket.accept();
			
				String clientAddress = socket.getInetAddress().getHostAddress();
				int clientPort = socket.getPort();
				Client newclient = new Client(clientAddress, clientPort, socket);

				listCl.add(newclient);
				
				// Để truy cập thông tin của một client cụ thể
//				int port = clientMap.get(clientAddress);
//				System.out.println("Client connected: " + port);
				for (Client client : listCl) {
					System.out.println(client.getPort() + " " + client.getIp());

				}
				// tao luong va truyen vao ham khoi tao luong; 
				Thread thread = new Thread(new Luong(socket));
				thread.start();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static synchronized void addSach(Sach sach, ObjectOutputStream oos) throws IOException {
		sachList.add(sach);
		saveToFile();
		// lưu student vào file s.bin
		System.out.println("Added new book: " + sach);
	}
	private static synchronized void editSach(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		sachList.clear(); //xoa toan bo doi tuong co trong list
		sachList = (ArrayList<Sach>) ois.readObject();

		saveToFile();
		// lưu sach vào file s.bin
		System.out.println("edit book: ");
	}

	private static synchronized void removeSach(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		int s1 = (int) ois.readObject();
		ArrayList<Sach> toRemove = new ArrayList<>();
		for (Sach sach : sachList) {
			if (s1 == sach.getId()) {
				// Mark student for removal
				toRemove.add(sach);
				System.out.println("Da danh dau sach can xoa: " + sach);
			}
		}
		// Remove students from list
		sachList.removeAll(toRemove);
		saveToFile();

	}
	
	private static synchronized void thongke(ObjectInputStream ois,ObjectOutputStream oos) throws IOException, ClassNotFoundException {
		int nam = (int) ois.readObject();
		int d=0;
		for (Sach sach : sachList) {
			if (nam== sach.getNamXB()) {
				// Mark student for removal
				d++;
			}
		}
//		String tg = (String ) ois.readObject();
//		int d=0;
//		for(Sach sach : sachList) {
//			if(sach.getTacGia().equals(tg)) {
//				d++;
//			}
//		}
		System.out.println(d);
		// Remove students from list
		
		oos.writeObject(d);
		oos.flush();
	}
	
//login dang nhap vao 
	private static synchronized void login(ObjectOutputStream oos, ObjectInputStream ois)throws IOException, ClassNotFoundException, SQLException {
		try {
			String ten = (String) ois.readObject();
			String mk = (String ) ois.readObject();
			
		//	con = getConnection();
			rs= con.Login(ten);
			
			if(rs.next()) {
				oos.writeObject("ok");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//bt tim kiem
	private static synchronized void searchSach(ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException{
		try {
			ArrayList<Sach> find = new ArrayList<>();
			int id = (int) ois.readObject();
			for (Sach sach : sachList) {
				if (id== sach.getId()) {
					find.add(sach);
					
				} 
			}
			oos.writeObject(find);
			oos.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("loi");
		}
		
	}

	
	private static synchronized void sendRemove(ObjectOutputStream oos) {
		try {

			oos.writeObject(sachList);
			oos.flush();
			System.out.println("Đã gửi ds tới Client");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void sendSachList(ObjectOutputStream oos) {
		try {

			oos.writeObject(sachList);
			oos.flush();
			System.out.println("Đã gửi ds tới Client");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void loadFromFile() {
		file = new File("s.bin");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			sachList = (ArrayList<Sach>) ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Loaded data from file");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void saveToFile() {
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(sachList);
			oos.close();
			fos.close();
			System.out.println("Đã lưu vào file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static synchronized void removeClient(Client client) {
		if (listCl.contains(client)) {
			listCl.remove(client);
			System.out.println("Client disconnected: " + client.getPort() + " " + client.getIp());
		}
	}

//	public static Sach findSachByID(int maSach) {
//	    for (Sach sach : sachList) {
//	        if (sach.getId() == maSach) {
//	            return sach;
//	        }
//	    }
//	    return null;
//	}
	
	static class Luong extends Thread {
		private Socket socket;  //nhan doi tuong socket lam tham so 

		public Luong(Socket socket) {
			this.socket = socket;  //dai dien ket noi giua client va server 
		}

		@Override
		public void run() {
			try {
				//tao doi tuong ois vaf oos de doc va ghi dl
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

				// Read client request and process it

				while (!socket.isClosed()) {
					try {
						//doc duoi dang chuoi va chuyen lai thanh mot mang cac chuoi 
						String request = ("" + inputStream.readObject());
						String[] s = request.split(":", 2);

						switch (s[0]) {
						case "add":
							Sach sach = (Sach) inputStream.readObject();
							addSach(sach, outputStream);
							break;
						case "list":
							loadFromFile();
							sendSachList(outputStream);
							break;
						case "remove":
							removeSach(inputStream);
							break;

						case "cancel":
							for (int i = 0; i < listCl.size(); i++) {
								int port = listCl.get(i).getPort();
								int out = Integer.parseInt(s[1]);
								if (out == port) {
									listCl.remove(listCl.get(i));

									System.out.println("Client disconnected: " + port);
								}

							}
							break;
						case "edit":
							editSach(inputStream);
							break;
						case "thong ke":
							thongke(inputStream, outputStream);
							break;
						case "login":
						       try {
								login(outputStream, inputStream);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						   
							break;
						case "search":
							searchSach(inputStream, outputStream);
							break;
						default:
							break;
						}
						System.out.println(s[0]);
					} catch (SocketException e) {

					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("da thoat");
			}
		}
	}
}
