package concertinoSonata;
import java.io.*;

public class CmdTest {
    public void callRunCmd(String path, String program) {
    	 String deafultProgramPath = System.getProperty("user.dir")+"/desktop/concertinoSonata/concertino";
    	 System.out.println(System.getProperty("user.dir"));
    	 ProcessBuilder pb = new ProcessBuilder(deafultProgramPath, "-f", path, "-m", "1");
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
//    	 ProcessBuilder builder = new ProcessBuilder("Terminal", "ls");
//    	 builder.redirectErrorStream(true);
//    	 Process p;
//		try {
//			p = builder.start();
//
//	        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	        String line;
//	        while (true) {
//	            try {
//					line = r.readLine();
//    	            System.out.println(line);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	        }
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}