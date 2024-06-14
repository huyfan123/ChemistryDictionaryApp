package Codes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.SwingConstants;

public class MainGUI {

	JFrame framef;
	private JPanel frame;
	private ImageIcon userIcon = new ImageIcon("asset\\icons\\user.png"); 
	private ImageIcon burgerBarIcon = new ImageIcon("asset\\icons\\menu.png");
	private ImageIcon logoIcon = new ImageIcon("asset\\images\\logo.png");
	private ImageIcon searchIcon = new ImageIcon("asset\\icons\\search.png");
	private ImageIcon sideMenuLogo = new ImageIcon("asset\\images\\smallerBlackLogo.png");
	private ImageIcon iconMS = new ImageIcon("asset\\images\\iconMS.png");
	private ImageIcon backArrow = new ImageIcon("asset\\icons\\backarrow.png");
	private ImageIcon volumeIcon = new ImageIcon("asset\\icons\\volume.png");
	private JTextField searchField;
	private JButton sideMenuLogoutButton;
	private JButton sideMenuLoginButton;
	private JPanel sideMenu;
	private JButton openMenuButton;
	private JButton testButton;
	private JPanel mainScreen;
	private JScrollPane searchResultPanel;
	private JScrollPane historySP;
	private JScrollPane searchRcmSP;
	private JPanel chemicalPanel;
	private JTextArea chemicalINF;
	private JLabel chemicalImage;
	private JLabel chemicalNameLabel;
	private JButton soundButton;
	private String mode;
	private JPanel menuBar;
	private JPanel searchBar;
	private JLayeredPane layeredPane;
	private JPanel dictionaryPanel;
	private JButton loginButton;
	private JLabel loginIconLabel;
	private JLabel displayNameLabel;
	private JLabel welcomeLabel;
	private JComboBox<String> searchModeCombobox;
	private DefaultListModel<String> recentListModel;
	private DefaultListModel<String> searchListModel;
	private DefaultListModel<String> searchRcmModel;
	private ArrayList<String> searchData;
	private String USER = "guest";
	private String displayName = "guest";	
	private static MainGUI currentFrame;
	private JPanel periodicTablePanel;
	private JLabel iconInMainScr;

	public static MainGUI getFrame() throws SQLException 
	{
	       if (currentFrame == null)
	    	   currentFrame = new MainGUI();
  
	       return currentFrame;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					currentFrame = new MainGUI();
					currentFrame.framef.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGUI() throws SQLException 
	{
		initializeMG();
	}

	@SuppressWarnings("serial")
	private void initializeMG() throws SQLException {		
		framef = new JFrame();	
		framef.setBounds(0, 0, 1980, 835);
		framef.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framef.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame = new JPanel();
		frame.setLayout(null);
		framef.setContentPane(frame);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(255, 255, 255));
		layeredPane.setBounds(0, 0, 1980, 835);
		frame.add(layeredPane);
		layeredPane.setLayout(null);
		
		dictionaryPanel = new JPanel();
		dictionaryPanel.setBackground(new Color(255, 255, 255));
		dictionaryPanel.setBounds(0, 0, 1540, 798);
		layeredPane.add(dictionaryPanel, Integer.valueOf(1));
		dictionaryPanel.setLayout(null);
		
		//The chemical infomation panel
		
		chemicalPanel = new JPanel();
		chemicalPanel.setBackground(new Color(255, 255, 255));
		chemicalPanel.setBounds(1540, 0, 1540, 798);
		layeredPane.add(chemicalPanel, Integer.valueOf(6));
		chemicalPanel.setLayout(null);
		
		soundButton = new JButton("");
		soundButton.setBackground(new Color(255, 255, 255));
		soundButton.setBounds(1172, 110, 85, 85);
		soundButton.setFocusPainted(false);
		soundButton.setIcon(volumeIcon);
		soundButton.setBorder(null);
		chemicalPanel.add(soundButton);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 1540, 100);
		topPanel.setBackground(new Color(43, 46, 97));
		chemicalPanel.add(topPanel);
		topPanel.setLayout(null);
		
		JButton backToMenuButton = new JButton("");
		backToMenuButton.setBackground(new Color(43, 46, 97));
		backToMenuButton.setBounds(30, 20, 60, 60);
		backToMenuButton.setFocusPainted(false);
		backToMenuButton.setBorder(null);
		backToMenuButton.setIcon(backArrow);
		topPanel.add(backToMenuButton);
		
		backToMenuButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
					closeChemicalPanel();
	        	}
	    	});
		
		chemicalNameLabel = new JLabel("");
		chemicalNameLabel.setForeground(new Color(255, 255, 255));
		chemicalNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		chemicalNameLabel.setBounds(159, 20, 1351, 60);
		topPanel.add(chemicalNameLabel);
		
		chemicalImage = new JLabel("");
		chemicalImage.setFont(new Font("Tahoma", Font.PLAIN, 99));
		chemicalImage.setBackground(new Color(255, 255, 255));
		chemicalImage.setBounds(1230, 195, 300, 300);
		chemicalPanel.add(chemicalImage);
		
		JScrollPane chemicalInfSP = new JScrollPane();
		chemicalInfSP.setBounds(10, 110, 1163, 688);
		chemicalPanel.add(chemicalInfSP);
		
		chemicalINF = new JTextArea();
		chemicalINF.setForeground(new Color(0, 0, 0));
		chemicalINF.setBounds(10, 110, 1163, 688);
		chemicalINF.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		chemicalINF.setEditable(false);
		chemicalINF.setFocusable(false);
		chemicalINF.setLineWrap(true);
		chemicalINF.setWrapStyleWord(true);
		chemicalINF.setFont(new Font("Tahoma", Font.PLAIN, 25));
		chemicalInfSP.setViewportView(chemicalINF);
		
		//END of the chemical infomation panel	
		
		//Side menu panel
		
		sideMenu = new JPanel();
		sideMenu.setBounds(-350, 0, 339, 798);
		layeredPane.add(sideMenu, Integer.valueOf(5));
		sideMenu.setBackground(new Color(255,255,255));
		sideMenu.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		sideMenu.setLayout(null);
			
		JLabel sideMenuIconLabel = new JLabel("");
		sideMenuIconLabel.setBounds(24, 24, 293, 132);
		sideMenuIconLabel.setIcon(sideMenuLogo);
		sideMenu.add(sideMenuIconLabel);
			
		JButton sideMenuDictButton = new JButton("Từ điển");
		sideMenuDictButton.setBackground(new Color(255, 255, 255));
		sideMenuDictButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		sideMenuDictButton.setBounds(0, 179, 338, 60);
		sideMenuDictButton.setFocusPainted(false);
		sideMenu.add(sideMenuDictButton);
			
		JButton sideMenuTableButton = new JButton("Bảng tuần hoàn");
		sideMenuTableButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		sideMenuTableButton.setBackground(Color.WHITE);
		sideMenuTableButton.setBounds(0, 237, 338, 60);
		sideMenuTableButton.setFocusPainted(false);
		sideMenu.add(sideMenuTableButton);
			
		JButton sideMenuTestButton = new JButton("Kiểm tra");
		sideMenuTestButton.setFont(new Font("Tahoma", Font.BOLD, 25));
		sideMenuTestButton.setBackground(Color.WHITE);
		sideMenuTestButton.setBounds(0, 296, 338, 60);
		sideMenuTestButton.setFocusPainted(false);
		sideMenu.add(sideMenuTestButton);
		sideMenuTestButton.setVisible(false);
			
		sideMenuLoginButton = new JButton("Đăng nhập");
		sideMenuLoginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		sideMenuLoginButton.setBackground(new Color(255, 255, 255));
		sideMenuLoginButton.setBounds(0, 738, 338, 50);
		sideMenuLoginButton.setFocusPainted(false);
		sideMenu.add(sideMenuLoginButton);
			
		sideMenuLogoutButton = new JButton("Đăng xuất");
		sideMenuLogoutButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		sideMenuLogoutButton.setBackground(Color.WHITE);
		sideMenuLogoutButton.setBounds(0, 738, 339, 50);
		sideMenuLogoutButton.setFocusPainted(false);
		sideMenu.add(sideMenuLogoutButton);	
		sideMenuLogoutButton.setVisible(false);
		
		/*
		JLabel sideMenuNameLabel = new JLabel("");
		sideMenuNameLabel.setBounds(72, 678, 253, 50);
		sideMenu.add(sideMenuNameLabel);
		
		JLabel sideMenuUserLabel = new JLabel("");
		sideMenuUserLabel.setBounds(10, 676, 52, 52);
		sideMenu.add(sideMenuUserLabel);
		*/
			
		sideMenuLoginButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
					try {
						new LoginGUI();
					} catch (SQLException e1) {
						e1.printStackTrace();}
					
					closeSideMenu();
	        	}
	    	});
			
		sideMenuLogoutButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
					setUser("guest", "guest");
					closeSideMenu();
	        	}
			});
			
		sideMenuDictButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		openDictionary();
					closeSideMenu();
	        	}
			});
			
		sideMenuTableButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		openPeriodictPanel();
					closeSideMenu();
	        	}
			});
			
		sideMenuTestButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
					closeSideMenu();
	        	}
			});	
		
		//End of side menu panel
		
		//Menu, search and main screen panel
		
		recentListModel = new DefaultListModel<String>();
		JList<String> recentList = new JList<String>(recentListModel);
		recentList.setBorder(new LineBorder(new Color(0, 0, 0)));
		recentList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		recentList.setBackground(new Color(240, 240, 240));
        recentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recentList.setCellRenderer(new CustomListCellRenderer());
        recentList.setBorder(BorderFactory.createTitledBorder("Lịch sử"));
        recentList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	int index = recentList.locationToIndex(e.getPoint());
                    if (index != -1) 
                    {
                        String selectedItem = recentList.getModel().getElementAt(index);
                        try {
                        	searchField.setText("");
							setChemicalPanel(DBoperation.getInstance().getType(USER, selectedItem) ,selectedItem);
						} catch (SQLException | IOException | LineUnavailableException
								| UnsupportedAudioFileException e1) {
							e1.printStackTrace();
						}
                    }
                }
            }
        });
        
		historySP = new JScrollPane(recentList);
		historySP.setBounds(713, 146, 374, 150);
		dictionaryPanel.add(historySP);
		historySP.setVisible(false);
		
		searchRcmModel = new DefaultListModel<String>();
		JList<String> searchRcmList = new JList<String>(searchRcmModel);
		searchRcmList.setBorder(new LineBorder(new Color(0, 0, 0)));
		searchRcmList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchRcmList.setBackground(new Color(240, 240, 240));
		searchRcmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchRcmList.setCellRenderer(new CustomListCellRenderer());
		searchRcmList.setBorder(BorderFactory.createTitledBorder("Gợi ý"));
		searchRcmList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	int index = searchRcmList.locationToIndex(e.getPoint());
                    if (index != -1) 
                    {
                        String selectedItem = searchRcmList.getModel().getElementAt(index);
                        try {
                        	searchField.setText("");
                        	setMode();
							setChemicalPanel(mode ,selectedItem);
							
							if(USER.equals("guest")==false)
							{
								if(DBoperation.getInstance().getType(USER, DBoperation.getInstance().getEnName(selectedItem, mode)) == null)
								{
									DBoperation.getInstance().addHistory(USER,DBoperation.getInstance().getEnName(selectedItem, mode),"nameEn");
									setHistory();
								}
							}
						} catch (SQLException | IOException | LineUnavailableException
								| UnsupportedAudioFileException e1) {
							e1.printStackTrace();
						}
                    }
                }
            }
        });
        
		searchRcmSP = new JScrollPane(searchRcmList);
		searchRcmSP.setBounds(713, 146, 374, 150);
		dictionaryPanel.add(searchRcmSP);
		searchRcmSP.setVisible(false);
		
		menuBar = new JPanel();
		menuBar.setBounds(0, 0, 1540, 57);
		menuBar.setBackground(new Color(29,42,87));
		dictionaryPanel.add(menuBar);
		menuBar.setLayout(null);
		
		openMenuButton = new JButton("");
		openMenuButton.setBounds(0, 0, 66, 57);
		openMenuButton.setBackground(new Color(29,42,87));
		openMenuButton.setFocusPainted(false);
		openMenuButton.setIcon(burgerBarIcon);
		menuBar.add(openMenuButton);
		
		JButton dictButton = new JButton("Từ điển");
		dictButton.setBounds(63, 0, 127, 57);
		dictButton.setForeground(new Color(255, 255, 255));
		dictButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		dictButton.setBackground(new Color(29,42,87));
		dictButton.setFocusPainted(false);
		dictButton.setBorderPainted(false);
		menuBar.add(dictButton);
		
		dictButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	openDictionary();	
	        }
	    });
		
		JButton tableButton = new JButton("Bảng tuần hoàn");
		tableButton.setBounds(200, 0, 220, 57);
		tableButton.setForeground(new Color(255, 255, 255));
		tableButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		tableButton.setBackground(new Color(29,42,87));
		tableButton.setFocusPainted(false);
		tableButton.setBorderPainted(false);
		menuBar.add(tableButton);
		
		tableButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	openPeriodictPanel();	
	        }
	    });
		
		testButton = new JButton("Kiểm tra");
		testButton.setBounds(417, 0, 139, 57);
		testButton.setForeground(new Color(255, 255, 255));
		testButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		testButton.setBackground(new Color(29,42,87));
		testButton.setFocusPainted(false);
		testButton.setBorderPainted(false);
		menuBar.add(testButton);
		testButton.setVisible(false);
		
		loginIconLabel = new JLabel("");
		loginIconLabel.setBounds(1330, 0, 60, 57);
		loginIconLabel.setForeground(new Color(255, 255, 255));
		loginIconLabel.setBackground(new Color(0, 0, 0));
		loginIconLabel.setIcon(userIcon);
		menuBar.add(loginIconLabel);
		
		loginButton = new JButton("Đăng nhập");
		loginButton.setBounds(1350, 0, 122, 57);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(29,42,87));
		loginButton.setFocusPainted(false);
		loginButton.setBorderPainted(false);
		menuBar.add(loginButton);
		
		displayNameLabel = new JLabel("");
		displayNameLabel.setForeground(new Color(255, 255, 255));
		displayNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		displayNameLabel.setBounds(1350, 0, 205, 57);
		menuBar.add(displayNameLabel);
		displayNameLabel.setVisible(false);
		
		welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setForeground(new Color(255, 255, 255));
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(1224, 0, 116, 57);
		menuBar.add(welcomeLabel);
		welcomeLabel.setVisible(false);
		
		searchBar = new JPanel();
		searchBar.setBounds(0, 57, 1540, 147);
		searchBar.setBackground(new Color(43, 46, 97));
		dictionaryPanel.add(searchBar);
		searchBar.setLayout(null);
			
		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(91, 10, 388, 132);
		logoLabel.setIcon(logoIcon);
		searchBar.add(logoLabel);
		
		searchField = new JTextField();
		searchField.setBounds(518, 41, 569, 47);
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		searchBar.add(searchField);
		Border paddingBorder = new EmptyBorder(5, 10, 5, 5);
        Border border = searchField.getBorder();
        searchField.setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
		searchField.setColumns(10);
		
		JButton searchButton = new JButton("");
		searchButton.setBounds(1097, 33, 60, 60);
		searchButton.setBackground(new Color(43, 46, 97));
		searchButton.setIcon(searchIcon);
		searchButton.setFocusPainted(false);
		searchButton.setBorderPainted(false);
		searchBar.add(searchButton);
		
		searchModeCombobox = new JComboBox<String>();
		searchModeCombobox.setBounds(518, 89, 171, 33);
		searchModeCombobox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchModeCombobox.setModel(new DefaultComboBoxModel<String>(new String[] {"Tên Tiếng Anh", "Tên Tiếng Việt", "CTHH / Kí hiệu"}));
		searchModeCombobox.setRenderer(new BasicComboBoxRenderer() {
			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 5, 5, 10));             
                return label;
            }
        });
		searchBar.add(searchModeCombobox);
		
		searchData = DBoperation.getInstance().getNames("", "nameEn");
		searchModeCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
	                setMode();
	                searchData = DBoperation.getInstance().getNames("", mode);
	                updateRcmListFilter();
            	}
            	catch(Exception cbe) {};
            }
        });
		
		mainScreen = new JPanel();
		mainScreen.setBackground(new Color(255, 255, 255));
		mainScreen.setBounds(0, 202, 1540, 596);
		dictionaryPanel.add(mainScreen);
		mainScreen.setLayout(null);
		
		searchListModel = new DefaultListModel<String>();
		JList<String> searchList = new JList<String>(searchListModel);
		searchList.setBorder(new LineBorder(new Color(0, 0, 0)));
		searchList.setFont(new Font("Tahoma", Font.PLAIN, 30));
		searchList.setBackground(new Color(255, 255, 255));
		searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		searchList.setCellRenderer(new CustomListCellRenderer());
		searchList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	int index = searchList.locationToIndex(e.getPoint());
                    if (index != -1) 
                    {
                        String selectedItem = searchList.getModel().getElementAt(index);
                        try {
							setChemicalPanel(mode, selectedItem);
							if (USER.equals("guest")==false)
							{
								if (DBoperation.getInstance().getType(USER, DBoperation.getInstance().getEnName(selectedItem, mode)) == null)
								{
									DBoperation.getInstance().addHistory(USER,DBoperation.getInstance().getEnName(selectedItem,mode),"nameEn");
									setHistory();
								}
							}
						} catch (SQLException | IOException | LineUnavailableException
								| UnsupportedAudioFileException e1) {
							e1.printStackTrace();
						}
                    }
                }
            }
        });
		
		searchResultPanel = new JScrollPane(searchList);
		searchResultPanel.setBackground(new Color(255, 255, 255));
		searchResultPanel.setBounds(0, 0, 1540, 596);
		Border brdr = BorderFactory.createLineBorder(Color.BLACK, 5);
		searchResultPanel.setBorder(BorderFactory.createTitledBorder(brdr, "Kết quả",1,2,new Font("Tahoma",Font.BOLD,30)));
		mainScreen.add(searchResultPanel);
		searchResultPanel.setVisible(false);
		
		iconInMainScr = new JLabel("");
		iconInMainScr.setBounds(460, 168, 620, 265);
		iconInMainScr.setIcon(iconMS);
		mainScreen.add(iconInMainScr);	
		
		//Periodict Table
		
		periodicTablePanel = new JPanel();
		periodicTablePanel.setBackground(new Color(255, 255, 255));
		periodicTablePanel.setBounds(1540, 0, 1540, 798);
		layeredPane.add(periodicTablePanel, Integer.valueOf(4));
		periodicTablePanel.setLayout(null);
		
		JPanel topPanel2 = new JPanel();
		topPanel2.setBounds(0, 0, 1540, 100);
		topPanel2.setBackground(new Color(43, 46, 97));
		periodicTablePanel.add(topPanel2);
		topPanel2.setLayout(null);
		
		JButton backToMenuButton2 = new JButton("");
		backToMenuButton2.setBackground(new Color(0, 0, 0));
		backToMenuButton2.setBounds(30, 20, 60, 60);
		backToMenuButton2.setFocusPainted(false);
		backToMenuButton2.setBorder(null);
		backToMenuButton2.setBackground(new Color(43, 46, 97));
		backToMenuButton2.setIcon(backArrow);
		topPanel2.add(backToMenuButton2);
		
		backToMenuButton2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	closePeriodictPanel();
	        }
	    });
		
		JLabel periodicLabel = new JLabel("Bảng tuần hoàn ");
		periodicLabel.setForeground(new Color(255, 255, 255));
		periodicLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		periodicLabel.setBounds(159, 20, 1351, 60);	
		topPanel2.add(periodicLabel);
		
		BufferedImage image = null;
        try {
            image = ImageIO.read(new File("asset\\images\\periodicTable.png"));
        } catch (IOException e) {
            e.printStackTrace();}
        JLabel label = new JLabel(new ImageIcon(image));
        
		JScrollPane theTable = new JScrollPane(label);
		theTable.setBounds(10, 110, 1520, 678);
		periodicTablePanel.add(theTable);
		
		//END of Periodict Table
		
		openMenuButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	openSideMenu();	
	        }
	    });
		
		loginButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
				try {
					new LoginGUI();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	        }
	    });
		
		searchField.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	checkSearch();
	        }
			@Override
			public void focusLost(FocusEvent e) {
				historySP.setVisible(false);
				searchRcmSP.setVisible(false);
			}
	    });
		setHistory();
		
		searchButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try 
	        	{
	        		setMode();
					setSearchResult(searchField.getText(), mode);
					searchField.setText("");
					historySP.setVisible(false);	
				}
	        	catch(Exception sqle){};
	        }
	    });
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent search) {
                updateRcmListFilter();
                checkSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent search) {
            	updateRcmListFilter();
            	checkSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent search) {
            	updateRcmListFilter();
            	checkSearch();
            }
        });
		//END of menu, search and main screen panel
	}
	
	//Functions start from here
	
	//set cellrender of the history jlist
	@SuppressWarnings("serial")
	static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
            Border border2 = new EmptyBorder(5, 5, 5, 10);
            label.setBorder(BorderFactory.createCompoundBorder(border, border2));
            return label;
        }
    }
	
	public void openSideMenu()
	{
		Timer timer = new Timer(10, new ActionListener() {
            int currentX = sideMenu.getX();
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX += 10; //Step = 10
                if (currentX >= 0) //End location
                    ((Timer) e.getSource()).stop();
                else
                	sideMenu.setLocation(currentX, sideMenu.getY());
            }
        });
		dictionaryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeSideMenu();
            }
        });
        timer.start();
	}
	
	public void closeSideMenu()
	{
		Timer timer = new Timer(10, new ActionListener() {
            int currentX = sideMenu.getX();
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX -= 10; //Step = 10
                if (currentX <= -350) //End location
                    ((Timer) e.getSource()).stop();
                else
                	sideMenu.setLocation(currentX, sideMenu.getY());
            }
        });
		for(MouseListener i : dictionaryPanel.getMouseListeners())
			dictionaryPanel.removeMouseListener(i);
        timer.start();
	}
	
	public void closeChemicalPanel()
	{
		Timer timer = new Timer(1, new ActionListener() {
            int currentX = chemicalPanel.getX();
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX += 155; //Step = 10
                if (currentX >= 1600) //End location
                {
                    ((Timer) e.getSource()).stop();
                	chemicalPanel.setLocation(1540, sideMenu.getY());
                }
                else
                	chemicalPanel.setLocation(currentX, sideMenu.getY());
            }
        });
        timer.start();
	}
	
	public void openChemicalPanel()
	{
		Timer timer = new Timer(20/310, new ActionListener() {
            int currentX = chemicalPanel.getX();
            int step = 155;
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX -= step ; //Step = 10
                if (currentX <= -165) //End location
                {
                    ((Timer) e.getSource()).stop();
                	chemicalPanel.setLocation(0, sideMenu.getY());
                }
                else
                	chemicalPanel.setLocation(currentX, sideMenu.getY());
            }
        });
        timer.start();
	}
	
	public void closePeriodictPanel()
	{
		Timer timer = new Timer(1, new ActionListener() {
            int currentX = periodicTablePanel.getX();
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX += 155; //Step = 10
                if (currentX >= 1600) //End location
                {
                    ((Timer) e.getSource()).stop();
                    periodicTablePanel.setLocation(1540, sideMenu.getY());
                }
                else
                	periodicTablePanel.setLocation(currentX, sideMenu.getY());
            }
        });
        timer.start();
	}
	
	public void openPeriodictPanel()
	{
		Timer timer = new Timer(20/310, new ActionListener() {
            int currentX = periodicTablePanel.getX();
            int step = 155;
            @Override
            public void actionPerformed(ActionEvent e) {
                currentX -= step ; //Step = 10
                if (currentX <= -165) //End location
                {
                    ((Timer) e.getSource()).stop();
                    periodicTablePanel.setLocation(0, sideMenu.getY());
                }
                else
                	periodicTablePanel.setLocation(currentX, sideMenu.getY());
            }
        });
        timer.start();
	}
		
	public void setHistory() throws SQLException
	{
		recentListModel.clear();
		if (USER.equals("guest") == false)
		{
			ArrayList<String> historyList = DBoperation.getInstance().getHistory(USER);
			for (String x : historyList)	
			{
				//System.out.println(x);
				recentListModel.addElement(x);
			}
		}
		framef.repaint();
		framef.revalidate();
	}
	
	public void setSearchResult(String ip, String type) throws SQLException
	{
		searchListModel.removeAllElements();
		ArrayList<String> resultList = DBoperation.getInstance().getNames(ip, type);
		for (String x : resultList)
		{
			//System.out.println(x);
			searchListModel.addElement(x);
		}
		searchResultPanel.setVisible(true);
	}
	
	public void updateRcmListFilter() {
		String filter = searchField.getText();
		searchRcmModel.clear();
		for (String item : searchData)
		{
			if (item.toLowerCase().startsWith(filter))
            	searchRcmModel.addElement(item);
        }
    }
	
	public void checkSearch()
	{
		if (searchField.getText().equals(""))
    	{
    		historySP.setVisible(true);
    		searchRcmSP.setVisible(false);
    	}
    	else
    	{
    		historySP.setVisible(false);
			searchRcmSP.setVisible(true);
    	}
	}
	
	public void setChemicalPanel(String type, String chemical) throws SQLException, IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		ArrayList<String> chemicalData = DBoperation.getInstance().getChemicalData(type, chemical);
		String setName = chemicalData.get(1) + " - " + chemicalData.get(2) + " - " + chemicalData.get(3);
		chemicalNameLabel.setText(setName);
		chemicalImage.setIcon(new ImageIcon(chemicalData.get(5)));
		
		FileInputStream file = new FileInputStream(chemicalData.get(4));    
		Scanner sc = new Scanner(file);
	    StringBuilder sb = new StringBuilder();
	    
	    while (sc.hasNextLine())
	        sb.append(sc.nextLine() + "\n");
	    chemicalINF.setText(sb.toString());
        sc.close();
        file.close();
        
	    ActionListener[] soundListener = soundButton.getActionListeners();
	    for (ActionListener listener : soundListener) 
	    	soundButton.removeActionListener(listener);
	    soundButton.addActionListener(new ActionListener() {
	    	@Override
		    public void actionPerformed(ActionEvent e) {
	    		File audioFile = new File(chemicalData.get(6));
			    AudioInputStream inputStream;
				try {
					inputStream = AudioSystem.getAudioInputStream(audioFile);			
					AudioFormat format = inputStream.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					Clip audio = (Clip) AudioSystem.getLine(info);
					audio.open(inputStream);
					audio.start();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
	    	}
	    });   
	    openChemicalPanel();
	}
	public void setMode()
	{
		String getMode = (String) searchModeCombobox.getSelectedItem();
		if (getMode.equals("Tên Tiếng Anh"))
        	mode = "nameEn";
		else if (getMode.equals("Tên Tiếng Việt"))
			mode = "nameVie";
		else if (getMode.equals("CTHH / Kí hiệu"))
			mode = "symbol";
	}
	
	public void setUser(String username, String display)
	{
		USER = username;
		displayName = display;
		
		if (USER.equals("guest"))
		{	
			sideMenuLogoutButton.setVisible(false);
			sideMenuLoginButton.setVisible(true);
			//testButton.setVisible(false);	
			displayNameLabel.setVisible(false);
			welcomeLabel.setVisible(false);
			loginButton.setVisible(true);
			try {
				setHistory();
			} catch (SQLException e) {
				e.printStackTrace();}
		}
		else
		{
			sideMenuLogoutButton.setVisible(true);
			sideMenuLoginButton.setVisible(false);
			//testButton.setVisible(true);
			displayNameLabel.setText(displayName);
			displayNameLabel.setVisible(true);
			welcomeLabel.setVisible(true);	
			loginButton.setVisible(false);	
			try {
				setHistory();
			} catch (SQLException e) {
				e.printStackTrace();}
		}
		framef.repaint();
		framef.revalidate();
	}
	
	public void setNames(String username, String display)
	{
		setUser(username,display);
	}
	
	public void openDictionary()
	{
		searchResultPanel.setVisible(false);
	}
}

