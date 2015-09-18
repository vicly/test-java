package vic.test.jdk.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class PathAndFilesTrail {

	public static void main(String[] args) throws IOException {

		Path absolutePath = Paths.get("/home/vic/tt.txt");
		//Path absolutePath = Paths.get("/home/vic", "tt.txt");
		System.out.println(absolutePath);
		System.out.println("absolute: " + absolutePath.isAbsolute());
		System.out.println("exists: " + Files.exists(absolutePath, LinkOption.NOFOLLOW_LINKS));

		/*
			.
			false
			/home/vic/PHL/tech-test/jdk-test/.
			/home/vic/PHL/tech-test/jdk-test
		 */
		Path relativePath = Paths.get(".");
		System.out.println(relativePath);
		System.out.println(relativePath.isAbsolute());
		System.out.println(relativePath.toAbsolutePath());
		System.out.println(relativePath.toAbsolutePath().normalize());


		System.out.println("-------------------------------------");

		Path dir = Paths.get("/home/vic/Study");
		Files.walkFileTree(dir, new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("pre visit dir:  " + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("visit file: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.println("visit file failed: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				System.out.println("post visit dir: " + dir);
				return FileVisitResult.CONTINUE;
			}
		});


	}

}
