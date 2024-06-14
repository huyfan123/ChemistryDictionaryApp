package Codes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class ForgotPasswordGUI implements ActionListener {

	private JFrame frame;
	private JTextField displayTextField;
	private JTextField usernameTextField;
	private JTextField newPassTextField;
	private JTextField confirmPassTextField;
	private JButton btnConfirm;
	private JLabel displayLabel;
	private JLabel usernameLabel;
	private JLabel newPassLabel;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_2_1;
	private JLabel lblNewLabel;
	private JLabel lblClickAnywhereTo;
	private JLabel changePassLabel;
	
	public ForgotPasswordGUI()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
     * @wbp.parser.constructor
     */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 482, 552);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Đổi mật khẩu");
		frame.getContentPane().setLayout(null);
		
		changePassLabel = new JLabel("Đổi mật khẩu!");
		changePassLabel.setBounds(10, 80, 448, 47);
		changePassLabel.setForeground(new Color(0, 0, 0));
		changePassLabel.setFont(new Font("Script MT Bold", Font.PLAIN, 25));
		changePassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(changePassLabel);
		
		displayTextField = new JTextField();
		displayTextField.setBounds(106, 168, 251, 31);
		frame.getContentPane().add(displayTextField);
		displayTextField.setColumns(10);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(106, 234, 251, 31);
		usernameTextField.setColumns(10);
		frame.getContentPane().add(usernameTextField);
		
		JLabel confirmPassLabel = new JLabel("Xác nhận mật khẩu mới");
		confirmPassLabel.setBounds(106, 337, 175, 22);
		confirmPassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		confirmPassLabel.setForeground(new Color(0, 0, 0));
		confirmPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(confirmPassLabel);
		
		newPassTextField = new JTextField();
		newPassTextField.setBounds(106, 296, 251, 31);
		newPassTextField.setColumns(10);
		frame.getContentPane().add(newPassTextField);
		
		confirmPassTextField = new JTextField();
		confirmPassTextField.setBounds(106, 359, 251, 31);
		confirmPassTextField.setColumns(10);
		frame.getContentPane().add(confirmPassTextField);
		
		btnConfirm = new JButton("Xác nhận");
		btnConfirm.setBounds(178, 445, 121, 36);
		btnConfirm.setBackground(new Color(35, 75, 154));
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConfirm.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(btnConfirm);
		btnConfirm.setFocusPainted(false);
		btnConfirm.addActionListener(this);
		
		newPassLabel = new JLabel("Mật khẩu mới");
		newPassLabel.setBounds(106, 275, 121, 22);
		newPassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		newPassLabel.setForeground(new Color(0, 0, 0));
		newPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(newPassLabel);
		
		usernameLabel = new JLabel("Tên người dùng");
		usernameLabel.setBounds(106, 212, 121, 22);
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setForeground(new Color(0, 0, 0));
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(usernameLabel);
		
		displayLabel = new JLabel("Biệt danh");
		displayLabel.setBounds(106, 147, 121, 22);
		displayLabel.setHorizontalAlignment(SwingConstants.LEFT);
		displayLabel.setForeground(new Color(0, 0, 0));
		displayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(displayLabel);
			
	}

	@Override
	public void actionPerformed(ActionEvent ere) {
		if (ere.getSource() == btnConfirm)
		{
			List<JLabel> rmvThis = new ArrayList<JLabel>();
			try {
			String check;
			check = DBoperation.getInstance().checkForgotPass(displayTextField.getText(), usernameTextField.getText(), newPassTextField.getText(),confirmPassTextField.getText());
			
			if (check.equals("Accepted"))
			{
				DBoperation.getInstance().changePass(usernameTextField.getText(), newPassTextField.getText());
				
				frame.getContentPane().removeAll();
				
			    lblNewLabel = new JLabel("Thành công!!");
				lblNewLabel.setFont(new Font("Rockwell", Font.PLAIN, 30));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setForeground(new Color(0, 0, 0));
				lblNewLabel.setBounds(32, 153, 406, 47);
				frame.getContentPane().add(lblNewLabel);
				
				lblClickAnywhereTo = new JLabel("Nhấn vào màn hình để quay lại Đăng nhập!");
				lblClickAnywhereTo.setHorizontalAlignment(SwingConstants.CENTER);
				lblClickAnywhereTo.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
				lblClickAnywhereTo.setForeground(new Color(0, 0, 0));
				lblClickAnywhereTo.setBounds(32, 198, 406, 47);
				frame.getContentPane().add(lblClickAnywhereTo);
				
				frame.revalidate();
				frame.repaint();
				
				frame.getContentPane().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    frame.dispose();
	                }
	            });
			}
			else
			{
				if (check.equals("NotFound"))
				{
					lblNewLabel_1_1 = new JLabel("*This UserName isn't exist!");
					lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
					lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
					lblNewLabel_1_1.setForeground(Color.RED);
					lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblNewLabel_1_1.setBounds(106, 264, 297, 27);
					frame.getContentPane().add(lblNewLabel_1_1);
					
					frame.revalidate();
					frame.repaint();
					
					rmvThis.add(lblNewLabel_1_1);
				}
				else
				{
						if (check.equals("CfPassWrong"))
						{
							lblNewLabel_1_2 = new JLabel("*Incorrect Confirm password!");
							lblNewLabel_1_2.setVerticalAlignment(SwingConstants.TOP);
							lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
							lblNewLabel_1_2.setForeground(Color.RED);
							lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lblNewLabel_1_2.setBounds(106, 387, 269, 27);
							frame.getContentPane().add(lblNewLabel_1_2);
							
							frame.revalidate();
							frame.repaint();
							
							rmvThis.add(lblNewLabel_1_2);
							
						}
						else
						{
							if (check == "Null")
							{
								lblNewLabel_1_2_1 = new JLabel("*Null information is not allowed!!");
								lblNewLabel_1_2_1.setBounds(132, 424, 225, 27);
								lblNewLabel_1_2_1.setVerticalAlignment(SwingConstants.TOP);
								lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
								lblNewLabel_1_2_1.setForeground(Color.RED);
								lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));			
								frame.getContentPane().add(lblNewLabel_1_2_1);
								
								frame.revalidate();
								frame.repaint();
								
								rmvThis.add(lblNewLabel_1_2_1);
								
							}
							else
							{
							lblNewLabel_1 = new JLabel("*DisplayName doesn't match UserName!");
							lblNewLabel_1.setForeground(new Color(255, 0, 0));
							lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
							lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
							lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
							lblNewLabel_1.setBounds(106, 197, 280, 27);
							frame.getContentPane().add(lblNewLabel_1);

							frame.revalidate();
							frame.repaint();
							
							rmvThis.add(lblNewLabel_1);
							
							}
						}
					}
				}
			btnConfirm.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	for(JLabel lb : rmvThis)
	            		frame.remove(lb);
	            	frame.revalidate();
					frame.repaint();
	            }
	        });
		}
			catch (Exception err) {	
				err.printStackTrace();
			}
		}
	}
}
