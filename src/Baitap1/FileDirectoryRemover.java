package Baitap1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileDirectoryRemover {
    public static void main(String[] args) {
    	String path = FileDirectoryRemover.getPath();
    	FileDirectoryRemover.removeFileOrDirectory(path);
    }

    private static String getPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn file hoặc thư mục cần xóa: ");
        String path = scanner.nextLine();
        scanner.close();
        return path;
    }

    private static void removeFileOrDirectory(String path) {
        try {
            Path pathToRemove = Paths.get(path);
            if (Files.exists(pathToRemove)) {
                if (Files.isDirectory(pathToRemove)) {
                    removeDirectory(pathToRemove);
                    System.out.println("Đã xóa thư mục: " + path);
                } else {
                    Files.delete(pathToRemove);
                    System.out.println("Đã xóa file: " + path);
                }
            } else {
                System.out.println("Đường dẫn không hợp lệ: " + path);
            }
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    private static void removeDirectory(Path directory) throws IOException {
        File[] files = directory.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    removeDirectory(file.toPath());
                } else {
                    Files.delete(file.toPath());
                }
            }
        }
        Files.delete(directory);
    }
}