package vic.test.jdk.misc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class NIO {

	public static void main(String[] args) {
		Path file = null;
		BufferedWriter bufferedWriter = null;
		try {
			file = Files.createFile(Paths.get("D:\\DeepakGaikwadNet\\Resources\\SimpleNIOData.txt"));

			Charset charset = Charset.forName("UTF-8");

			String line = "I am writing using NIO.";

			bufferedWriter = Files.newBufferedWriter(file, charset);
			bufferedWriter.write(line, 0, line.length());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static void copy() {
		Path source = Paths.get("E:/tmp/java/tutorial/nio/file/copy/source/sample text.txt");
		Path target = Paths.get("E:/tmp/java/tutorial/nio/file/copy/target/sample output.txt");
		try {
			Path p = Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void read() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("D:\\Deepak\\WebSite\\DeepakGaikwadNet\\Resources\\NIODataFile.txt", "r");
            FileChannel fileChannel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(24);
            int bytes = fileChannel.read(buffer);
            bytes = fileChannel.read(buffer);
            while (bytes != -1) {
                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
 
                buffer.clear();
                bytes = fileChannel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
 
    }	
}