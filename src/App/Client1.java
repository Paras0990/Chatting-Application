package App;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class Client1 extends JFrame implements ActionListener {
	
	
	JPanel p1;
	JTextField t1;
	JButton b1;
	static JFrame f1 = new JFrame();
	
	static Box vertical = Box.createVerticalBox();
	
	static JPanel a1;
	static Socket sc;
	static DataInputStream d_i;
	static DataOutputStream d_o;
	
	
	Client1(){
		f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
		
		p1=new JPanel();
		p1.setBackground(new Color(0,0,0));
		p1.setLayout(null);
		p1.setBounds(0,0,350,60);
		f1.add(p1);
		
		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("App/pics/back1.png"));
		Image i2=i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel l1=new JLabel(i3);
		l1.setBounds(5,17,30,30);
		p1.add(l1);
		
		l1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("App/pics/dp.png"));
		Image i5=i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
		ImageIcon i6=new ImageIcon(i5);
		JLabel l2=new JLabel(i6);
		l2.setBounds(50,10,40,40);
		p1.add(l2);
		
		JLabel l6=new JLabel("Mehra");
		l6.setFont(new Font("SANS_SARIF",Font.BOLD,22));
		l6.setForeground(Color.WHITE);
		l6.setBounds(115,10,100,20);
		p1.add(l6);
		
		JLabel l7=new JLabel("Active now");
		l7.setFont(new Font("SANS_SARIF",Font.PLAIN,10));
		l7.setForeground(Color.WHITE);
		l7.setBounds(115,30,100,20);
		p1.add(l7);
		
		
		ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("App/pics/call.jpg"));
		Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9=new ImageIcon(i8);
		JLabel l3=new JLabel(i9);
		l3.setBounds(200,17,30,30);
		p1.add(l3);
		
		ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("App/pics/vcall.png"));
		Image i11=i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i12=new ImageIcon(i11);
		JLabel l4=new JLabel(i12);
		l4.setBounds(255,17,30,30);
		p1.add(l4);
		
		ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("App/pics/1.jpg"));
		Image i14=i13.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i15=new ImageIcon(i14);
		JLabel l5=new JLabel(i15);
		l5.setBounds(300,17,30,30);
		p1.add(l5);
		
		t1=new JTextField();
		t1.setBounds(5,555,260,40);
		t1.setFont(new Font("SANS_SERIF",Font.PLAIN,18));
		f1.add(t1);
		
		b1=new JButton("Send");
		b1.setBounds(270,555,70,39);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SANS_SERIF",Font.BOLD,12));
		b1.addActionListener(this);
		f1.add(b1);
		
		a1=new JPanel();
		a1.setBounds(3,62,343,491);
		a1.setBackground(Color.WHITE);
		a1.setFont(new Font("SANS_SERIF",Font.PLAIN,18));
		f1.add(a1);
		
        f1.setLayout(null);
        f1.setLocation(800,100);
		f1.setSize(350,600);
		f1.setUndecorated(true);
		f1.setVisible(true);
		
	}
	
	 public void actionPerformed(ActionEvent ae){
	        
	        try{
	            String out = t1.getText();
	            
	            JPanel p2 = formatLabel(out);
	            
	            a1.setLayout(new BorderLayout());
	            
	            JPanel right = new JPanel(new BorderLayout());
	            right.add(p2, BorderLayout.LINE_END);
	            vertical.add(right);
	            vertical.add(Box.createVerticalStrut(15));
	            
	            a1.add(vertical, BorderLayout.PAGE_START);
	            
	            
	            d_o.writeUTF(out);
	            t1.setText("");
	        }catch(Exception e){
	            System.out.println(e);
	        }
	    }
	    
	    public static JPanel formatLabel(String out){
	        JPanel p3 = new JPanel();
	        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
	        
	        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
	        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        l1.setBackground(new Color(37, 211, 102));
	        l1.setOpaque(true);
	        l1.setBorder(new EmptyBorder(15,15,15,50));
	        
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        
	        JLabel l2 = new JLabel();
	        l2.setText(sdf.format(cal.getTime()));
	        
	        p3.add(l1);
	        p3.add(l2);
	        return p3;
	    }
	    
	    public static void main(String[] args) {
			new Client1().f1.setVisible(true);
	        
	        try{
	            
	            sc = new Socket("127.0.0.1", 5001);
	            d_i  = new DataInputStream(sc.getInputStream());
	            d_o = new DataOutputStream(sc.getOutputStream());
	            
	            String msginput = "";
	            
		    while(true){
	                a1.setLayout(new BorderLayout());
		        msginput = d_i.readUTF();
	            	JPanel p2 = formatLabel(msginput);
	                JPanel left = new JPanel(new BorderLayout());
	                left.add(p2, BorderLayout.LINE_START);
	                
	                vertical.add(left);
	                vertical.add(Box.createVerticalStrut(15));
	                a1.add(vertical, BorderLayout.PAGE_START);
	                f1.validate();
	            }
	            
	        }catch(Exception e){}
	    }    
	}