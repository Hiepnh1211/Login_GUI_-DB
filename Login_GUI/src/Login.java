import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel panel;
	private JTextField Username;
	private JPasswordField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 266);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usrername:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(10, 52, 122, 32);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(10, 94, 122, 32);
		panel.add(lblPassword);
		
		Username = new JTextField();
		Username.setBounds(139, 52, 133, 28);
		panel.add(Username);
		Username.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","0968536745");
					
					String username = Username.getText();
					String password = Password.getText();
					
					java.sql.Statement stm = con.createStatement();
					
					String sql = "select * from username where username = '"+username+"' and password = '"+password+"'"
							+ "";
					Resultset rs = (Resultset) stm.executeQuery(sql);
					
					if(((ResultSet) rs).next()) {
						dispose();
						HomePage hp = new HomePage();
						hp.show();
					}else {
						//JOptionPane.showMessageDialog(null, "Wrong Username or Password", sql, ABORT);
						Username.setText(null);
						Password.setText(null);
					}
					
					con.close();
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(52, 144, 96, 32);
		panel.add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Username.setText(null);
				Password.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReset.setBounds(176, 144, 96, 32);
		panel.add(btnReset);
		
		Password = new JPasswordField();
		Password.setBounds(142, 94, 130, 28);
		panel.add(Password);
	}
}
