package concertinoSonata;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.filechooser.FileFilter;

class ClientUpload extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
	CmdTest ct = new CmdTest();
    JFileChooser fc;
    JButton b, b1;
    JTextField tf;
    JLabel tl1;
    JRadioButtonMenuItem r1, r2, r3;
    ButtonGroup btnGroup;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    String soundPath;
    String programPath;
    String mode="1";
    int i;

    ClientUpload() {
        super("Concertino Sonata");
        tf = new JTextField();
        tf.setBounds(20, 50, 190, 30);
        add(tf);
        
        tl1 = new JLabel("Modo de visualización");
        tl1.setBounds(130, 100, 150, 30);
        add(tl1);
        btnGroup = new ButtonGroup();        
        r1 = new JRadioButtonMenuItem("Modo 1", true);
        r1.setBounds(20, 150, 100, 30);
        r2 = new JRadioButtonMenuItem("Modo 2", false);
        r2.setBounds(140, 150, 100, 30);
        r3 = new JRadioButtonMenuItem("Modo 3", false);
        r3.setBounds(260, 150, 100, 30);
        //A single selection
        btnGroup.add(r1);
        btnGroup.add(r2);
        btnGroup.add(r3);
        add(r1);
        add(r2);
        add(r3);
        
        r1.addActionListener(this);
        r2.addActionListener(this);
        r3.addActionListener(this);

        b = new JButton("Browse Song");
        b.setBounds(250, 50, 150, 30);
        add(b);
        b.addActionListener(this);
        b1 = new JButton("Run Concertino Sonata");
        b1.setBounds(100, 200, 200, 50);
        add(b1);
        b1.addActionListener(this);
        fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {

        public String getDescription() {
        	   return ".wav";
         }

        public boolean accept(File f) {
        	if (f.isDirectory()) {
        		return true;
        	} else {
        		String filename = f.getName().toLowerCase();
        		return filename.endsWith(".wav") || filename.endsWith(".wave") ;
        	}
          }
        });
        
        setLayout(null);
        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
        	//System.out.print(e.getSource());
            if (e.getSource() == b) {
                int x = fc.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    copy();
                }
            }
            if (e.getSource() == b1) { //Button Run program
           // System.out.print("Run");
	            if(soundPath!=null) {
	                ct.callRunCmd(soundPath, programPath, mode);
	            }
            }
            
            if (e.getSource() == r1) { //Radio 1
            	System.out.print("R1");
            	mode="1";
            }
            if (e.getSource() == r2) { //Radio 2
            	System.out.print("R2");
            	mode="2";
            }
            if (e.getSource() == r3) { //Radio 2
            	System.out.print("R3");
            	mode="3";
            }
        } catch (Exception ex) {
        }
    }

    public void copy() throws IOException {
        File f1 = fc.getSelectedFile();
		//System.out.println(f1.getAbsolutePath());
		soundPath = f1.getAbsolutePath();
        tf.setText(f1.getAbsolutePath());//Path
    }

    public static void main(String... d) {
        new ClientUpload();
    }
}