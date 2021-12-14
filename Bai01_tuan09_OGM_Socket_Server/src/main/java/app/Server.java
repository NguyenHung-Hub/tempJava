package app;

import java.awt.Container;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Server extends JFrame {
	private static final long serialVersionUID = 8395012674993617785L;
	private JTextArea txa;

	public Server() {
		setTitle("server");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		Container container = getContentPane();

		container.add(new JScrollPane(txa = new JTextArea()));

		new Thread(() -> {
			try {
				ServerSocket serverSocket = new ServerSocket(8090);

				SwingUtilities.invokeLater(() -> {
					txa.append("\n server start at:" + LocalDateTime.now());
					System.out.println("s");
				});

				while (true) {

					Socket socket = serverSocket.accept();
					SwingUtilities.invokeLater(() -> {
						txa.append("\n Client IP:" + socket.getInetAddress().getHostAddress());
						txa.append("\n Client name:" + socket.getInetAddress().getHostName());
					});

					Handler handler = new Handler(socket);
					Thread thread = new Thread(handler);
					thread.start();
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Server().setVisible(true);
		});
	}
}
