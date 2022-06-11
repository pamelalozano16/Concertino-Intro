package concertinoSonata;
import java.io.*;

public class CmdTest {
    public void callRunCmd(String path, String program, String mode) {
    	 String deafultProgramPath = System.getProperty("user.dir")+"/desktop/concertinoSonata/concertino";
    	 System.out.println(deafultProgramPath+" -f "+path+" -m "+mode);
    	 ProcessBuilder pb = new ProcessBuilder(deafultProgramPath, "-f", path, "-m", mode);
    	 //ProcessBuilder pb = new ProcessBuilder("ls",System.getProperty("user.dir")+"/src/concertinoSonata/assets");
    	 pb.directory(new File(System.getProperty("user.dir")));
    	 try {
			Process p = pb.start();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String readline;
		    int i = 0;
		    while ((readline = reader.readLine()) != null) {
		        System.out.println(++i + " " + readline);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}