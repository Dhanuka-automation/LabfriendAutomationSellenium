package contexts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Labfriend_LoginContext {

	private String email;
	private String password;
	public FileInputStream fis =null;

	public Labfriend_LoginContext() throws IOException {
		Properties prop = new Properties();
		 String osName = System.getProperty("os.name");
		 
		 if(osName.equalsIgnoreCase("Linux")) {
			  fis = new FileInputStream(
						System.getProperty("user.dir") + "/data/LabFriendLogin/Login.properties"); 
		 }else {
			  fis = new FileInputStream(
						System.getProperty("user.dir") + "\\data\\LabFriendLogin\\Login.properties");
		 }

		prop.load(fis);
		email = prop.getProperty("email");
		password = prop.getProperty("password");

	}

	public String getemail() {
		return email;
	}

	public String getpassword() {
		return password;
	}
}
