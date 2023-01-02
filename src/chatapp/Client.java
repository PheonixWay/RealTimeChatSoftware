/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pheonix
 */
public class Client implements ActionListener {
    JTextField text;
    static JPanel mPanel;
    static Box vertical=Box.createVerticalBox();
    static JFrame f=new JFrame();
    static DataOutputStream output;
    Client(){
        f.setSize(450,695);
        f.setLayout(null);
        f.setLocation(800,40);
        f.getContentPane().setBackground(Color.BLACK);
        
        
        //creating header for our whatsapp desktop app like showing name image and other icon
        JPanel header=new JPanel();
        header.setLayout(null);
        header.setBackground(Color.BLACK);
        header.setBounds(0,0,450,70);
        f.add(header);
        
        //adding back button image to header panel 
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);//scaling image to fit in the label
        ImageIcon i3=new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        header.add(back);
        
        //adding event to back image 
        back.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        
        });
        
        //adding profile image to header
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/bunty.jpeg"));
        Image i5=i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);//scaling image to fit in the label
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        header.add(profile);
        
        //adding video image to header
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);//scaling image to fit in the label
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        header.add(video);
        
        //adding call image to header
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);//scaling image to fit in the label
        ImageIcon i12=new ImageIcon(i11);
        JLabel call=new JLabel(i12);
        call.setBounds(360, 20, 35, 30);
        header.add(call);
        
        //adding settings image to header
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);//scaling image to fit in the label
        ImageIcon i15=new ImageIcon(i14);
        JLabel settings=new JLabel(i15);
        settings.setBounds(420, 20, 10, 25);
        header.add(settings);
        
        //adding name label 
        JLabel name=new JLabel("Juned");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        name.setBounds(100,25,100,18);
        header.add(name);
        
        //adding status label
        JLabel status=new JLabel("Online");
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,10));
        status.setBounds(100,45,100,18);
        header.add(status);
        
        mPanel=new JPanel();
        mPanel.setBounds(5,75,440,570);
        
        //creating scrool pane to scrool the msg
        JScrollPane scrollView = new JScrollPane(mPanel);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scrollView.setBounds(5,75,440,570);
        scrollView.getVerticalScrollBar().setUnitIncrement(10);
        f.add(scrollView);
        
        //adding textfield to take msg from user
        text=new JTextField();
        text.setBounds(5, 650, 310, 40);
        text.setFont(new Font("SAN_SERIF",Font.PLAIN,15));
        f.add(text);
        
        //adding send button 
        JButton send=new JButton("Send");
        send.setBackground(Color.WHITE);
        send.setForeground(Color.BLACK);
        send.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
        send.setBounds(320,650,123,40);
        f.add(send);
        
        //adding event to send button
        send.addActionListener(this);
           
        
        
        
        
        f.setUndecorated(true);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String outpu=text.getText();
      
            JPanel p1=formatLabel(outpu);//creating panel for adding label to put on right side of mpanel

            JPanel right=new JPanel(new BorderLayout());
            right.add(p1,BorderLayout.LINE_END);

            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));

            mPanel.setLayout(new BorderLayout());
            
            output.writeUTF(outpu);
            
            text.setText("");
            mPanel.add(vertical,BorderLayout.PAGE_START);
            f.validate();
        }catch(IOException a ){
            a.printStackTrace();
        }
        
    }
    public static JPanel formatLabel(String out){
        JLabel msgOut=new JLabel("<html> <p style=\"width:150px\">"+out+"</p></html>");
        
        JPanel p1=new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        msgOut.setFont(new Font("Tahoma",Font.BOLD,16) );
        msgOut.setBorder(new EmptyBorder(10,15,10,20));
        msgOut.setBackground(Color.GREEN);
        msgOut.setOpaque(true);
        p1.add(msgOut);
        
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel date=new JLabel();
        date.setText(sdf.format(cal.getTime()));
        
        p1.add(date);
        return p1;
        
    }
    public static JPanel formatLabelRecieve(String out){
        //we can set label size using html in label of swing
        JLabel msgOut=new JLabel("<html> <p style=\"width:150px\">"+out+"</p></html>");
        msgOut.setFont(new Font("Tahoma",Font.BOLD,16) );
        msgOut.setBorder(new EmptyBorder(10,15,10,20));
        msgOut.setBackground(Color.GRAY);
        msgOut.setOpaque(true);
        
        //creating panel to add label to mPanel means msg panel top panel
        JPanel p1=new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        
        //adding component to panel
        p1.add(msgOut);
        
        //adding time feature in msg using Calendor module in  java
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel date=new JLabel();
        date.setText(sdf.format(cal.getTime()));
        p1.add(date);
        return p1;
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Client();
        try {
            Socket s=new Socket("127.0.0.1",7000);
            DataInputStream input=new DataInputStream(s.getInputStream());
            output=new DataOutputStream(s.getOutputStream());
            while(true){
                mPanel.setLayout(new BorderLayout());
                String msg=input.readUTF();
                JPanel panel=formatLabelRecieve(msg); 
                JPanel left=new JPanel(new BorderLayout());
                left.add(panel,BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                mPanel.add(vertical,BorderLayout.PAGE_START);
                f.validate();
                    
            }
            
        } catch (IOException ex) {
            String Message=ex.getMessage();
            JOptionPane optionPane = new JOptionPane(Message);
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
    
}
