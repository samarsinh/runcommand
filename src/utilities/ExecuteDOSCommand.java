package utilities;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ExecuteDOSCommand {


	
	public static void main(String[] args) {
//		String cmd[]={"\"C:\\Program Files\\Java\\jdk1.6.0_27\\bin\\javac.exe\" \"E:/Test.java\""};
		
		String cmd[]={"\"C:\\Program Files\\Java\\jdk1.6.0_27\\bin\\java.exe\" -classpath E:/ Test"};
		
//		System.out.println(ExecuteDOSCommand.getData("pclist", "Yes...its working"));
		getCommandOutput(cmd[0], 1);
//		ExecuteDOSCommand.getData("message","knl", "hiee")	;
		//System.out.println(" Command "+);
		// following commands are for executing application as stand alone
		// application
		// System.out.println(ExecuteDOSCommand.getData("roots","null"));
		// System.out.println(ExecuteDOSCommand.getData("path","C:\\"));
		// new ExecuteDOSCommand().getDrives();
		// new ExecuteDOSCommand().getFilesList("C:\\");
	}

	public String[] getDrives() {
		// getting drive list from server
		File file[] = File.listRoots();
		String[] f = new String[file.length];
		int cnt = -1;
		for (File files : file) {
			f[++cnt] = files.getPath().replace("\\", "");

			System.out.println("drive list " + f[cnt]);
		}
		return f;

	}

	public String[] getFilesList(String path) {
		File dir = new File(path);

		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				// Get filename of file or directory
				String filename = children[i];
				System.out.println("file list " + filename);
			}
		}
		// The list of files can also be retrieved as File objects
		String[] files = dir.list();
		return files;
	}


	// functiion is overloaded for pclist and process list
	public static StringBuffer getData(String key) {
		System.out.println("key " + key);
		System.out.println("now it is in overload");
		StringBuffer arr = new StringBuffer();

		if (key.equals("processlist"))
			arr = getCommandOutput("tasklist /FO CSV ", 2);
		else if (key.equals("pclist")) {
			int temp1;
			StringBuffer line = new StringBuffer();
			StringBuffer array = new StringBuffer();
			arr = getCommandOutput("net view", 3);// for skipping first 3 lines
													// from output

			String str = "The command completed successfully.";
			temp1 = arr.indexOf(str);
			arr = arr.delete(temp1, (temp1 + str.length()));
			System.out.println("---------------------------------------");
			System.out.println("Output here is \n" + arr);
			System.out.print("---------------------------------------");
			String a[] = arr.toString().split("\n");
			System.out.println(" Length is " + a.length);

			int i = 0;
			String f = "";
			String ip = "";
			for (i = 0; i < a.length; i++) {
				// getting ipaddress from computer name using following code
				String s = a[i];
				try {
					ip = InetAddress.getAllByName(s.trim())[0].getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

				a[i] = a[i] + "," + ip;
				f += a[i] + '\n';

				System.out.println("Inetaddress : " + a[i] + "  ip address " + ip);

				arr = new StringBuffer(f);
			}
		}
		return arr;
	}

	public static StringBuffer getCommandOutput(String cmd, int skipLine) {
		StringBuffer sb = new StringBuffer();
		ArrayList arr = new ArrayList();

		try {
			Process p = Runtime.getRuntime().exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));

			// read the output from the command

			String s = null;
			//System.out.println("Here is the standard output of the
			 //command:\n");

			for (int i = 0; i < skipLine; i++) {
				s = stdInput.readLine();
				System.out.println("skipline o/p:" + s);
			}
			while ((s = stdInput.readLine()) != null) {
				if (s.trim().length() > 0) {
					s = s.replace("\\", "");
					sb.append(s);
					System.out.println("Command Output " + s);
					sb.append('\n');
				}
			}
			// read any errors from the attempted command
			System.out.println("Execute cmd Done");
			// System.out.println("Here is the standard error of the command (if
			// any):\n");

			while ((s = stdError.readLine()) != null) {
				if (s.trim().length() > 0) {
					System.out.println("ERROR Output " + s);
				}
			}
			
		}

		catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return sb;
	}
}
