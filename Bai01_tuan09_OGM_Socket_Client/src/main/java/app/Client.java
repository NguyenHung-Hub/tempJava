package app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import entity.Employee;
import entity.Project;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6874869980432907407L;
	private JTextArea txa;
	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtVersion;
	private JButton btnThem;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;

	private JTextField txtId;

	public Client() {
		setTitle("client");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		Container container = getContentPane();

		container.add(new JScrollPane(txa = new JTextArea()));

		Box mainBox = Box.createVerticalBox();
		container.add(mainBox, BorderLayout.NORTH);

		Box box0 = Box.createHorizontalBox();
		box0.add(new JLabel("id: "));
		box0.add(txtId = new JTextField(20));
		mainBox.add(box0);

		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("name: "));
		box1.add(txtName = new JTextField(20));
		mainBox.add(box1);

		Box box2 = Box.createHorizontalBox();
		box2.add(new JLabel("type: "));
		box2.add(txtType = new JTextField(20));
		mainBox.add(box2);

		Box box3 = Box.createHorizontalBox();
		box3.add(new JLabel("version: "));
		box3.add(txtVersion = new JTextField(20));
		txtVersion.setText("1.0.0;1.0.2");
		mainBox.add(box3);

		Box box4 = Box.createHorizontalBox();
		box4.add(btnThem = new JButton("Theem"));
		mainBox.add(box4);

		txtName.setText("p");
		txtType.setText("Software");

		new Thread(() -> {
			try {
				Socket socket = new Socket("localhost", 8090);
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				inputStream = new ObjectInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}).start();

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String idString = txtId.getText().trim();
					String nameString = txtName.getText().trim();
					String typeString = txtType.getText().trim();
					String versionString = txtVersion.getText().trim();

					Set<String> versionSet = new HashSet<String>(Arrays.asList(versionString.split(";")));

					Project project = new Project(Long.parseLong(idString), nameString, typeString, versionSet);
					Employee employee = new Employee();

					employee.setEmployeeID(100l);
					project.addDetail(employee, 7);

					outputStream.writeObject(project);

					// nhan ket qua
					String rs = (String) inputStream.readObject();
					System.out.println("ket qua tra ve: " + rs);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Client().setVisible(true);
		});
	}
}
