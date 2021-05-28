package ui.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import adminFactory_jy.JButtonCreator;
import adminFactory_jy.JLabelCreator;
import data.db.AdminDBMgr;
import data.db.MemberDBMgr;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminLoginDialog extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTextField txtLogin;
   private JPasswordField psfPW;
   adminLoginDialog loginDlg;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      try {
    	  adminLoginDialog dialog = new adminLoginDialog();
         dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         dialog.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Create the dialog.
    */
   public adminLoginDialog() {
	   JLabelCreator labelcreator = new JLabelCreator();
	   JButtonCreator btncreator = new JButtonCreator();
	   
	  this.loginDlg =loginDlg;
   	  setTitle("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
      setBounds(100, 100, 532, 511);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBackground(new Color(0, 102, 51));
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(null);
      
      JPanel panel = setJpanel(50, 206, 415, 256);
      contentPanel.add(panel);
      panel.setLayout(null);
      
      setTxtLogin();
      panel.add(txtLogin);
      txtLogin.setColumns(10);
      
      JLabel lblNewLabel_2 = (JLabel) labelcreator.create("\\uBE44\\uBC00\\uBC88\\uD638","����",12, 106, 391, 15);
      panel.add(lblNewLabel_2);
      
      JLabel label = (JLabel) labelcreator.create("\uC544\uC774\uB514","����",12, 22, 391, 15);
      panel.add(label);
      
      JButton button = (JButton) btncreator.create("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778","����",12, 194, 391, 40);
      button.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent arg0) {
      		String login =txtLogin.getText();
      		String pw =new String(psfPW.getPassword());
      		
      		AdminDBMgr mgr = new AdminDBMgr();
      		int r =mgr.loginProcess(login, pw);
      		switch (r) {
            case MemberDBMgr.LOGIN_SUCCESS:
               JOptionPane.showMessageDialog(null, "�α��ο� �����ϼ̽��ϴ�.");
               setVisible(false);
               adminMain adMain = new adminMain();
               adMain.setVisible(true);
               break;
            case MemberDBMgr.LOGIN_NOT_FOUND:
               JOptionPane.showMessageDialog(null, "ȸ���� ������ ã�� �� �����ϴ�.");
               break;
            case MemberDBMgr.LOGIN_PW_MISMATCH:
               JOptionPane.showMessageDialog(null, "�α��� Ȥ�� ��й�ȣ�� Ʋ���̽��ϴ�.");
               break;

            case MemberDBMgr.LOGIN_ERROR:
               JOptionPane.showMessageDialog(null, "�������� �ʴ� �����Դϴ�.");
               break;
            }
      	}
      });
     
      panel.add(button);
      
      setJpasswordField();
      panel.add(psfPW);
      
      JLabel lblNewLabel = (JLabel) labelcreator.createWithIcon
    		  ("", "C:\\dev2020\\java_ws\\Starbucks\\images\\logo\\\uB85C\uACE0(150x150).png",12, 10, 492, 150);
      contentPanel.add(lblNewLabel);
      
      JLabel labelLogin = (JLabel) labelcreator.createWithHorizontal("��Ÿ���� ������ �α���","����",50, 170, 415, 26,12);
      labelLogin.setForeground(new Color(255, 255, 255));
      contentPanel.add(labelLogin);
   }

private JPanel setJpanel(int x, int y, int w, int h) {
	JPanel panel = new JPanel();
    panel.setBackground(new Color(255, 255, 255));
    panel.setBounds(x,y,w,h);
	return panel;
}

private void setJpasswordField() {
	psfPW = new JPasswordField();
      psfPW.setBorder(new LineBorder(new Color(0, 121,51)));
      psfPW.setBounds(14, 131, 389, 37);
}

private void setTxtLogin() {
	txtLogin = new JTextField();
      txtLogin.setBorder(new LineBorder(new Color(0, 102, 51)));
      txtLogin.setForeground(Color.BLACK);
      txtLogin.setBounds(14, 49, 389, 37);
}

}