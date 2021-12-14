package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import dao.ProjectDAO;
import entity.Project;

public class Handler implements Runnable {

	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;

	public Handler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			while (true) {
				// nhan tu client

				Project project = (Project) inputStream.readObject();
				System.out.println("entity handler:" + project);
				ProjectDAO projectDAO = new ProjectDAO();
				boolean rs = projectDAO.add(project);
				if (rs) {
					System.out.println("theem thanh cong: \n" + rs);
					outputStream.writeObject("thêm thanh cong");
				}else {
					System.out.println("server: theem thaat bai: \n");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
