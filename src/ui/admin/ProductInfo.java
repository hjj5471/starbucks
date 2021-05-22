package ui.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import data.Member;
import data.Product;
import data.db.ProductDBMgr;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtImagePath;
	private JTextField txtPrice;
	private JTextField txtRegDate;
	private final ButtonGroup btnHotIceGruop = new ButtonGroup();
	private JTable pdTable;
	JComboBox comboCatgory;
	JRadioButton rdHot;
	JRadioButton rdIce;
	private JTextField txtID;
	ProductDBMgr mgr ;
	ProductInfo PInfo;
	JLabel lbImage;
	private JTextField txtSearch;
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProductInfo frame = new ProductInfo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public ProductInfo() {
		this.PInfo =PInfo;
		this.mgr= new ProductDBMgr();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\uC0C1\uD488\uAD00\uB9AC");
		setBounds(100, 100, 926, 734);
		
		setContentPane();
		
		JSplitPane splitPane = setJsplitPane();
		
		JPanel pnMain = setpnMain(splitPane);
		
		JLabel lblNewLabel = setlblNewLabel();
		pnMain.add(lblNewLabel);
		
		JButton btnProductList = setBtnProductList();
		pnMain.add(btnProductList);
		
		JScrollPane scrollPane = setScrollPane();
		pnMain.add(scrollPane);
		
		setpdTable();
		scrollPane.setViewportView(pdTable);
		
		settxtSearch();
		pnMain.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnclear = setbtnClear();
		pnMain.add(btnclear);
		
		JPanel pnSub = setpnsub();
		splitPane.setRightComponent(pnSub);
		pnSub.setLayout(null);
		
		setlbImage();
		pnSub.add(lbImage);
		
		JLabel lbID = setLabel("\\uAD00\\uB9AC \\uBC88\\uD638",266);
		pnSub.add(lbID);
		
		JLabel label = setLabel("\uC0C1\uD488\uBA85",314);
		pnSub.add(label);
		
		setTxtField(txtName,  311, 128);
		pnSub.add(txtName);
		
		JLabel label_1 = setLabel("\uCE74\uD14C\uACE0\uB9AC",360);
		pnSub.add(label_1);
		
		JLabel label_2 = setLabel("\uC0AC\uC9C4\uD30C\uC77C\uACBD\uB85C",405);
		pnSub.add(label_2);
		
		setTxtField(txtImagePath, 404,105);
		pnSub.add(txtImagePath);
		
		JLabel label_3 = setLabel("\uAC00\uACA9",457);
		pnSub.add(label_3);
		
		setTxtField(txtPrice, 454,128);
		pnSub.add(txtPrice);
		
		JLabel label_4 = setLabel("\uC720\uBB34",504 );
		pnSub.add(label_4);
		
		JButton btnNewButton = setbtnNewButton();
		pnSub.add(btnNewButton);
		
		JButton button = setButton();
		pnSub.add(button);
		
		JButton button_1 = setButton_1();
		pnSub.add(button_1);
		
		JButton button_2 = setButton_2();
		pnSub.add(button_2);
		
		JLabel label_5 = setLabel("\uCD9C\uC2DC\uC77C",541);
		pnSub.add(label_5);
		
		setNonEditableTxtField(txtRegDate, 538);
		pnSub.add(txtRegDate);
		
		setBtnRd(rdHot,129, 500, 62, 23);
		pnSub.add(rdHot);
		
		setBtnRd(rdIce,195, 500, 53, 23);
		pnSub.add(rdIce);
		
		setComboCategory();
		pnSub.add(comboCatgory);
		
		setNonEditableTxtField(txtID, 265);
		pnSub.add(txtID);
		
		JButton btnNewButton_1 = setBtnNewButton_1();
		pnSub.add(btnNewButton_1);
		
		
	}
	private void setTxtField(JTextField txtField, int y,int w) {
		txtField = new JTextField();
		txtField.setColumns(10);
		txtField.setBounds(129, y,w ,21);
	}
	
	private void setNonEditableTxtField(JTextField txtField, int y) {
		txtField = new JTextField();
		txtField.setEditable(false);
		txtField.setColumns(10);
		txtField.setBounds(129, y,128 ,21);
	}
	
	private void settxtSearch() {
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String target = txtSearch.getText();
				showSearchProduct(target);
			}
		});
		txtSearch.setBounds(189, 24, 186, 25);
	}
	

	private void setComboCategory() {
		comboCatgory = new JComboBox();
		comboCatgory.setModel(new DefaultComboBoxModel(new String[] {"Coffee", "Beverage", "Salad", "Dessert"}));
		comboCatgory.setSelectedIndex(0);
		comboCatgory.setBounds(129, 357, 128, 21);
	}
	private JButton setBtnNewButton_1() {
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String currentDirectoryPath 
				= "./images";
			JFileChooser openDlg = new JFileChooser(currentDirectoryPath);
			int r = openDlg.showOpenDialog(PInfo);
			if( r == JFileChooser.APPROVE_OPTION ) {
				File selImgFile 
					= openDlg.getSelectedFile();
				txtImagePath.setText(selImgFile.getPath());
				lbImage.setIcon(new ImageIcon(selImgFile.getPath()));
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\dev2020\\java_ws\\Starbucks\\images\\icons\\folder.png"));
		btnNewButton_1.setBounds(235, 403, 22, 23);
		return btnNewButton_1;
	}
	private void setBtnRd(JRadioButton rd, int x,int y, int w,int h) {
		if(rd.equals(rdIce)) {
			rd = new JRadioButton("ICE");
		}
		else {
			rd = new JRadioButton("HOT");
		}
		btnHotIceGruop.add(rd);
		rd.setBounds(x,y,w,h);
	}
	

	private JButton setButton_2() {
		JButton button_2 = new JButton("\uC0C1\uD488 \uC0AD\uC81C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =Integer.parseInt(txtID.getText());
				String name =txtName.getText();
				int r =mgr.deleteOneProdcut(id,name);
				if (r == 1) {
					JOptionPane.showMessageDialog(null, name+"상품 삭제 성공");
				}else {
					JOptionPane.showMessageDialog(null, name+"회원 삭제 실패");
				}
			}
		});
		button_2.setBounds(144, 629, 113, 23);
		return button_2;
	}
	private JButton setButton_1() {
		JButton button_1 = new JButton("\uC0C1\uD488 \uC218\uC815");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtID.getText());
				String name = txtName.getText();
				String category= (String) comboCatgory.getSelectedItem();
				String imagePath = txtImagePath.getText();
				int price = Integer.parseInt(txtPrice.getText());
				int hot = rdHot.isSelected()?1:2;
				String date = txtRegDate.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date regDay = null;
				try {
					regDay = sdf.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
				
				Product pd = new Product(id, name, category, imagePath, price, hot, regDay);
				boolean b =mgr.editOneProduct(pd);
				if(b) {
					JOptionPane.showMessageDialog(null,name+"수정 성공!!");	
				}else {
						JOptionPane.showMessageDialog(null,name+"수정 실패!!");	
				}
				
			}
		});
		button_1.setBounds(23, 629, 108, 23);
		return button_1;
	}
	private JButton setButton() {
		JButton button = new JButton("\uBAA9\uB85D \uBE44\uC6B0\uAE30");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText("");
				txtName.setText("");
				txtImagePath.setText("");
				comboCatgory.setSelectedIndex(1);
				btnHotIceGruop.clearSelection();
				txtPrice.setText("");
				txtRegDate.setText("");
				lbImage.setIcon(new ImageIcon("C:\\dev2020\\java_ws\\Starbucks\\images\\logo\\로고(150x150).png"));	
			}
		});
		button.setBounds(143, 584, 114, 23);
		return button;
	}
	private JButton setbtnNewButton() {
		JButton btnNewButton = new JButton("\uC0C1\uD488 \uCD94\uAC00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				int id = Integer.parseInt(txtID.getText());
				String name = txtName.getText();
				String category= (String) comboCatgory.getSelectedItem();
				String imagePath = txtImagePath.getText().substring(41);
				int price = Integer.parseInt(txtPrice.getText());
				int hot = rdHot.isSelected()?1:2;
				
				Product pd = new Product(name, category, imagePath, price, hot);
				
				if(mgr.addNewOneProduct(pd) == true) {
					JOptionPane.showMessageDialog(null, name+"추가 성공!!");
				}else {
					JOptionPane.showMessageDialog(null, name+"추가 실패!!");
				}	
			}
			
		});
		btnNewButton.setBounds(23, 584, 108, 23);
		return btnNewButton;
	}

	private JLabel setLabel(String text, int y) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 13));
		label.setBounds(12, y, 105, 18);
		return label;
	}
	

	private JLabel setLabel() {
		JLabel label = new JLabel("\uC0C1\uD488\uBA85");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 13));
		label.setBounds(12, 314, 105, 18);
		return label;
	}

	private void setlbImage() {
		lbImage = new JLabel("");
		lbImage.setHorizontalAlignment(SwingConstants.CENTER);
		lbImage.setIcon(new ImageIcon("C:\\dev2020\\java_ws\\Starbucks\\images\\logo\\\uB85C\uACE0(150x150).png"));
		lbImage.setBackground(new Color(0, 255, 0));
		lbImage.setBounds(12, 20, 245, 220);
	}
	
	private JPanel setpnsub() {
		JPanel pnSub = new JPanel();
		pnSub.setBackground(new Color(255, 255, 255));
		return pnSub;
	}
	
	private JPanel setpnMain(JSplitPane splitPane) {
		JPanel pnMain = new JPanel();
		pnMain.setBackground(new Color(0, 101, 70));
		splitPane.setLeftComponent(pnMain);
		pnMain.setLayout(null);
		return pnMain;
	}
	private void setContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}
	private JButton setbtnClear() {
		JButton btnclear = new JButton("");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSearch.setText("");
//				showAllProduct();
				showSearchProduct("");
			}
		});
		btnclear.setToolTipText("\uD14D\uC2A4\uD2B8 \uC9C0\uC6B0\uAE30");
		btnclear.setIcon(new ImageIcon("C:\\dev2020\\java_ws\\Starbucks\\images\\icons\\bin.png"));
		btnclear.setBounds(387, 25, 47, 23);
		return btnclear;
	}

	private void setpdTable() {
		pdTable = new JTable();
		pdTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				 {"관리 번호", "상품명", "카테고리", "사진파일경로", "가격", "HOT/ICE" ,"출시일"};
				
				int selRow = pdTable.getSelectedRow();
			
				
				if(pdTable.getValueAt(selRow, 2).toString().equalsIgnoreCase("coffee")) {
					comboCatgory.setSelectedIndex(0);
				}else if (pdTable.getValueAt(selRow, 2).toString().equals("Beverage")) {
					comboCatgory.setSelectedIndex(1);
				}else if(pdTable.getValueAt(selRow, 2).toString().equals("Salad")){
					comboCatgory.setSelectedIndex(2);
				}else {
					comboCatgory.setSelectedIndex(3);
				}
				
				int id = (int) pdTable.getValueAt(selRow, 0);
				String name = (String) pdTable.getValueAt(selRow, 1);
				
				String imagePath = (String) pdTable.getValueAt(selRow, 3);
				int price =Integer.parseInt(pdTable.getValueAt(selRow, 4).toString());
				
				
				if( pdTable.getValueAt(selRow, 5).toString() == "HOT") rdHot.setSelected(true);
				else rdIce.setSelected(true);
//				Date regDate =(Date) pdTable.getValueAt(selRow, 6);
				
				
				txtID.setText(String.valueOf(id));
				txtName.setText(name);
				txtImagePath.setText(imagePath);
				lbImage.setIcon(new ImageIcon( "C:\\dev2020\\java_ws\\Starbucks\\images\\menu\\"+ imagePath));
				txtPrice.setText(String.valueOf(price));
				Date regDate = (Date) pdTable.getValueAt(selRow,6);
				String DayStr = regDate.toString();
				txtRegDate.setText(DayStr);
//				
				
				
//				comboCatgory.getSelectedIndex()pdTable.getValueAt(selRow, 2); // comboBox
			}
		});
	}
	private JScrollPane setScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(36, 69, 541, 553);
		return scrollPane;
	}
	private JButton setBtnProductList() {
		JButton btnProductList = new JButton("\uC0C1\uD488 \uC804\uCCB4 \uB9AC\uC2A4\uD2B8");
		btnProductList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtSearch.setText("");
				showSearchProduct("");
			}
		});
		
		btnProductList.setFont(new Font("굴림", Font.BOLD, 12));
		btnProductList.setBounds(446, 22, 133, 28);
		return btnProductList;
	}
	private JLabel setlblNewLabel() {
		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uB9AC\uC2A4\uD2B8");
		lblNewLabel.setFont(new Font("HY견고딕", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 165, 49);
		return lblNewLabel;
	}

	private JSplitPane setJsplitPane() {
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.7);
		contentPane.add(splitPane, BorderLayout.CENTER);
		return splitPane;
	}
	

	
	public void showSearchProduct(String target) {
		final String [] columnNames = {"관리 번호", "상품명", "카테고리", "사진파일경로", "가격", "HOT/ICE" ,"출시일"};
		
		//DB 
		ProductDBMgr pdMgr = new ProductDBMgr();
		ArrayList<Product> pdList =pdMgr.getAllProducts();
		Object[][] data = new Object[pdList.size()][columnNames.length];
		
		getTableData(pdList, data);
		
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		pdTable.setModel(dtm);
				
		/*sort the table */
		TableRowSorter<TableModel> trs = sortTable();

		trs.setRowFilter(RowFilter.regexFilter(target));
	}
	private void getTableData(ArrayList<Product> pdList, Object[][] data) {
		for (int i = 0; i < pdList.size(); i++) {
			Product pd = pdList.get(i);
				data[i][0] = pd.getId();
				data[i][1] = pd.getName();
				data[i][2] = pd.getCategory();
				data[i][3] = pd.getImagePath();
				data[i][4] = pd.getPrice();
				data[i][5] = pd.getHot() == 1 ? "HOT":"ICE";
				data[i][6] = pd.getRegDay();		
		}
	}
	private TableRowSorter<TableModel> sortTable() {
		TableRowSorter<TableModel> trs=new TableRowSorter<TableModel>(pdTable.getModel());
		pdTable.setRowSorter(trs);
		return trs;
	}
	public void searchProduct(String target) {
	      TableRowSorter<TableModel> trs=new TableRowSorter<TableModel>( pdTable.getModel());

	      pdTable.setRowSorter(trs);

	      trs.setRowFilter(RowFilter.regexFilter(target));
	   }


	protected void showAllProduct() {
		String [] columnNames = {"관리 번호", "상품명", "카테고리", "사진파일경로", "가격", "HOT/ICE" ,"출시일"};
		ProductDBMgr pdMgr = new ProductDBMgr();
		ArrayList<Product> pdList = pdMgr.getAllProducts();
		Object[][] data = new Object[pdList.size()][columnNames.length];
		
		getTableData(pdList, data);
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		pdTable.setModel(dtm);
	}
}
