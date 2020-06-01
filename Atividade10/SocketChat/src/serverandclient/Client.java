package serverandclient;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextArea text;
	private JTextField MsgField;
	private JButton SendMsg;
	private JButton Exit;
	private JLabel HistoryLabel;
	private JLabel MessageLabel;
	private JPanel pnlContent;
	private Socket socket;
	private OutputStream output;
	private Writer output_w;
	private BufferedWriter buffer_w;
	private JTextField txtIP;
	private JTextField txtPorta;
	private JTextField txtNome;

	public Client() throws IOException {
		JLabel lblMessage = new JLabel("Cheking!");
		txtIP = new JTextField("127.0.0.1");
		txtPorta = new JTextField("0567");
		txtNome = new JTextField("Client");
		Object[] texts = { lblMessage, txtIP, txtPorta, txtNome };
		JOptionPane.showMessageDialog(null, texts);
		pnlContent = new JPanel();
		text = new JTextArea(10, 20);
		text.setEditable(false);
		text.setBackground(new Color(240, 240, 240));
		MsgField = new JTextField(20);
		HistoryLabel = new JLabel("Message Historian");
		MessageLabel = new JLabel("Message");
		SendMsg = new JButton("Send");
		SendMsg.setToolTipText("Sending Message");
		Exit = new JButton("Exit");
		Exit.setToolTipText("Quit Chat");
		SendMsg.addActionListener(this);
		Exit.addActionListener(this);
		SendMsg.addKeyListener(this);
		MsgField.addKeyListener(this);
		JScrollPane scroll = new JScrollPane(text);
		text.setLineWrap(true);
		pnlContent.add(HistoryLabel);
		pnlContent.add(scroll);
		pnlContent.add(MessageLabel);
		pnlContent.add(MsgField);
		pnlContent.add(Exit);
		pnlContent.add(SendMsg);
		setTitle(txtNome.getText());
		setContentPane(pnlContent);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(250, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void conectar() throws IOException {

		socket = new Socket(txtIP.getText(), Integer.parseInt(txtPorta.getText()));
		output = socket.getOutputStream();
		output_w = new OutputStreamWriter(output);
		buffer_w = new BufferedWriter(output_w);
		buffer_w.write(txtNome.getText() + "\r\n");
		buffer_w.flush();
	}

	public void enviarMensagem(String msg) throws IOException {

		if (msg.equals("Exit")) {
			buffer_w.write("Disconnected \r\n");
			text.append("Disconnected \r\n");
		} else {
			buffer_w.write(msg + "\r\n");
			text.append(txtNome.getText() + " diz -> " + MsgField.getText() + "\r\n");
		}
		buffer_w.flush();
		MsgField.setText("");
	}

	public void escutar() throws IOException {

		InputStream input_s = socket.getInputStream();
		InputStreamReader input_r = new InputStreamReader(input_s);
		BufferedReader buffer_r = new BufferedReader(input_r);
		String msg = "";

		while (!"Exit".equalsIgnoreCase(msg))

			if (buffer_r.ready()) {
				msg = buffer_r.readLine();
				if (msg.equals("Exit"))
					text.append("System Server Halt \r\n");
				else
					text.append(msg + "\r\n");
			}
	}

	public void sair() throws IOException {

		enviarMensagem("Exit");
		buffer_w.close();
		output_w.close();
		output.close();
		socket.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getActionCommand().equals(SendMsg.getActionCommand()))
				enviarMensagem(MsgField.getText());
			else if (e.getActionCommand().equals(Exit.getActionCommand()))
				sair();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				enviarMensagem(MsgField.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) throws IOException {

		Client app = new Client();
		app.conectar();
		app.escutar();
	}
}