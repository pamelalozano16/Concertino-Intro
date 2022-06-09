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
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    String soundPath;
    String programPath;
    int i;

    ClientUpload() {
        super("Concertino Sonata");
        tf = new JTextField();
        tf.setBounds(20, 50, 190, 30);
        add(tf);

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
        try {
            s = new Socket("localhost", 10);
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            send();
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == b) {
                int x = fc.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    copy();
                }
            }
            if (e.getSource() == b1) { //Button Run program
             //   send();
           // System.out.print("Run");
	            if(soundPath!=null) {
	                ct.callRunCmd(soundPath, programPath);
	            }
            }
        } catch (Exception ex) {
        }
    }

    public void copy() throws IOException {
        File f1 = fc.getSelectedFile();
		//System.out.println(f1.getAbsolutePath());
		soundPath = f1.getAbsolutePath();
        tf.setText(f1.getAbsolutePath());//Path
       // in = new FileInputStream(f1.getAbsolutePath());
//        while ((i = in.read()) != -1) {
//           // System.out.print(i);
//        }
    }
    
//    public void copyProgram() throws IOException {
//        File f1 = fc2.getSelectedFile();
//		//System.out.println(f1.getAbsolutePath());
//        programPath = f1.getAbsolutePath();
//        tf.setText(f1.getAbsolutePath());//Path
//       // in = new FileInputStream(f1.getAbsolutePath());
////        while ((i = in.read()) != -1) {
////           // System.out.print(i);
////        }
//    }

    public void send() throws IOException {
        dout.write(i);
        dout.flush();

    }

    public static void main(String... d) {
        new ClientUpload();
    }
}