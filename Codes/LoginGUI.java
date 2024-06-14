package Codes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LoginGUI implements ActionListener {

	private JFrame loginframe;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private ImageIcon poster = new ImageIcon("asset\\images\\loginLogo.png");
	private JButton btnLogin;
	private JButton btnForgotPass;
	private JLabel needAccLabel;
	private JButton btnSignUp;
	private JLabel logoLabel;
	
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	
	public LoginGUI() throws SQLException
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initializeLG();
					if(loginframe.isVisible() == false)
						loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initializeLG() {
		loginframe = new JFrame();
		loginframe.getContentPane().setBackground(new Color(255, 255, 255));
		loginframe.setTitle("Đăng nhập");
		loginframe.setBounds(100, 100, 769, 425);
		loginframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setForeground(new Color(192, 192, 192));
		txtUsername.setFont(new Font("Tahoma", Font.ITALIC, 18));
		txtUsername.setText("  Username");
		txtUsername.setBounds(367, 103, 332, 41);
		loginframe.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setForeground(new Color(192, 192, 192));
		txtPassword.setText("  Password");
		txtPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		txtPassword.setColumns(10);
		txtPassword.setBounds(367, 165, 332, 41);
		loginframe.getContentPane().add(txtPassword);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(35, 75, 154));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setBounds(569, 219, 130, 41);
		btnLogin.setFocusPainted(false);
		loginframe.getContentPane().add(btnLogin);
		//Add Listener
		btnLogin.addActionListener(this);
		
		JLabel separateLabel = new JLabel("-------------------------------------------------------");
		separateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		separateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		separateLabel.setBounds(378, 272, 308, 13);
		loginframe.getContentPane().add(separateLabel);
		
		needAccLabel = new JLabel("Chưa có tài khoản? >>");
		needAccLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		needAccLabel.setBounds(367, 308, 176, 13);
		loginframe.getContentPane().add(needAccLabel);
		
		btnSignUp = new JButton("Đăng kí");
		btnSignUp.setForeground(new Color(0, 0, 0));
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBackground(new Color(255, 255, 255));
		btnSignUp.setBounds(569, 292, 130, 41);
		loginframe.getContentPane().add(btnSignUp);
		btnSignUp.setFocusPainted(false);
		btnSignUp.addActionListener(this);
		
		logoLabel = new JLabel("");
		logoLabel.setBounds(21, 103, 317, 192);
		loginframe.getContentPane().add(logoLabel);
		logoLabel.setIcon(poster);
		
		btnForgotPass = new JButton("Quên mật khẩu?");
		btnForgotPass.setBackground(new Color(240, 240, 240));
		btnForgotPass.addActionListener(this);
		btnForgotPass.setHorizontalAlignment(SwingConstants.LEFT);
		btnForgotPass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnForgotPass.setBounds(367, 228, 159, 27);
		btnForgotPass.setFocusPainted(false);
		loginframe.getContentPane().add(btnForgotPass);
		
		txtPassword.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	txtPassword.setText("");
	        	txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        	txtPassword.setForeground(new Color(0, 0, 0));
	        }
			@Override
			public void focusLost(FocusEvent e) {
				if (txtPassword.getText().equals("") == true)
				{
				txtPassword.setForeground(new Color(192, 192, 192));
				txtPassword.setText("  Password");
				txtPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
				txtPassword.setColumns(10);
				txtPassword.setBounds(367, 165, 332, 41);
				}
			}
	    });
		txtUsername.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	txtUsername.setText("");
	        	txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        	txtUsername.setForeground(new Color(0, 0, 0));
	        	
	        }

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().equals("") == true) {
				txtUsername.setForeground(new Color(192, 192, 192));
				txtUsername.setFont(new Font("Tahoma", Font.ITALIC, 18));
				txtUsername.setText("  Username");
				txtUsername.setBounds(367, 103, 332, 41);
				loginframe.getContentPane().add(txtUsername);
				txtUsername.setColumns(10);
				}
				
			}
	    });
	
}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		try 
		{
			if (ev.getSource() == btnLogin)
			{
				String checkAccReturn = DBoperation.getInstance().checkLogin(txtUsername.getText(), txtPassword.getText());
				if (checkAccReturn == "wrongpass")
				{
					lblNewLabel_5 = new JLabel("* Wrong Password!!!");
					lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblNewLabel_5.setForeground(new Color(255, 0, 0));
					lblNewLabel_5.setBounds(367, 204, 130, 22);
					loginframe.getContentPane().add(lblNewLabel_5);
					loginframe.revalidate();
					loginframe.repaint();
				}
				else
				{
					if (checkAccReturn == "noaccfound")
					{
						lblNewLabel_6 = new JLabel("* No Account Found!!!");
						lblNewLabel_6.setForeground(Color.RED);
						lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_6.setBounds(367, 143, 149, 22);
						loginframe.getContentPane().add(lblNewLabel_6);
						loginframe.revalidate();
						loginframe.repaint();
					}
					else
					{
						MainGUI.getFrame().setNames(txtUsername.getText(), checkAccReturn);
						loginframe.dispose();
					}
						
				}
				txtUsername.addFocusListener(new FocusListener(){
			        @Override
			        public void focusGained(FocusEvent e1){
			        	loginframe.remove(lblNewLabel_6);
			        	loginframe.revalidate();
						loginframe.repaint();
			        }
	
					@Override
					public void focusLost(FocusEvent e1) {
						//DO nothing
					}
			    });
				txtPassword.addFocusListener(new FocusListener(){
			        @Override
			        public void focusGained(FocusEvent e2){
			        	loginframe.remove(lblNewLabel_5);
			        	loginframe.revalidate();
						loginframe.repaint();
			        }
	
					@Override
					public void focusLost(FocusEvent e2) {
						//Do nothing
					}
			    });
			}
			else
			{
				if (ev.getSource() == btnSignUp)
				{
					RegisterGUI rg;
					try {
						rg = new RegisterGUI();
						rg.openRegister();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				else
				{
					if (ev.getSource() == btnForgotPass)
						new ForgotPasswordGUI();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}