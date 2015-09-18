package vic.test.jsch;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;

import com.google.common.io.ByteStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;
import com.jcraft.jsch.Logger;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

public class Sftp {
	
	public static void main(String[] args) throws Exception {
		final String privateKeyFullPath = "/home/vic/Temp/test_id_rsa";
		
//		final byte[] privateKey = Files.toByteArray(new File(privateKeyFullPath));

		JSch.setLogger(new SimpleLogger());
		JSch jsch = new JSch();

/*		KeyPair kpair=KeyPair.load(jsch, privateKeyFullPath);
		System.out.println(kpair.getFingerPrint());
		boolean ok = kpair.decrypt("123456");
		System.out.println("decrypt: " + ok);
		if (true) return;
*/
		// No key passphrase
//		jsch.addIdentity(privateKeyFullPath);
//		jsch.addIdentity("test_private_key", privateKey, null, "123456".getBytes());
		jsch.addIdentity(privateKeyFullPath, "123456");
		
		Session session = jsch.getSession("test", "localhost", 22);
		session.setConfig("StrictHostKeyChecking", "no");
		//session.setUserInfo(new MyUserInfo());
		session.connect();

		Channel c = session.openChannel("sftp");
		c.setInputStream(System.in);
		c.setOutputStream(System.out);
		ChannelSftp sftp = (ChannelSftp) c;
		sftp.connect();
		
		System.out.println("---------------------------------------");
		Vector v = sftp.ls(".");
		Enumeration e = v.elements();
		while (e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
		
		System.out.println("---------------------------------------");
		InputStream in = sftp.get("./.ssh/id_rsa");
		ByteStreams.copy(in, System.out);
		
		sftp.disconnect();
		session.disconnect();
		
	}

}

class SimpleLogger implements Logger {

	@Override
	public boolean isEnabled(int level) {
		return true;
	}

	@Override
	public void log(int level, String message) {
		System.out.println(String.format("[%s] %s", level, message));
	}
	
}

class MyUserInfoX implements UserInfo, UIKeyboardInteractive {
	@Override
	public void showMessage(String message) {				
		System.out.println("showMessage() - " + message);
	}
	
	@Override
	public boolean promptYesNo(String message) {
		System.out.println("promptYesNo() - " + message);
		return true;
	}
	
	@Override
	public boolean promptPassword(String message) {
		System.out.println("promptPassword() - " + message);
		return true;
	}
	
	@Override
	public boolean promptPassphrase(String message) {
		System.out.println("promptPassphrase() - " + message);
		return true;
	}
	
	@Override
	public String getPassword() {
		return null;
	}
	
	@Override
	public String getPassphrase() {
		return "123456x";
	}

	@Override
	public String[] promptKeyboardInteractive(String destination, String name,
			String instruction, String[] prompt, boolean[] echo) {
		System.out.println("promptKeyboardInteractive() - destination=" + destination 
				+ ", name=" + name + ", instruction=" + instruction);
		if (prompt != null) {
			for (String p : prompt)
				System.out.println("prompt: " + p);
		}
		return null;
	}
}
