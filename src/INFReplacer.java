import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class INFReplacer {

    public static void main(String[] args) {
        String inputFolder = "inputs/";
        String outputFolder = "outputs/";
        File folder = new File(inputFolder);
        File[] files = folder.listFiles();

        for (File file : files != null ? files : new File[0]) {
            if (file.isFile() && file.getName().endsWith(".json")) {
                try {
                    String content = Files.readString(Paths.get(file.getAbsolutePath()));
                    String fileName = file.getName().replace(".json", "");
                    String copyFile = outputFolder + file.getName();
                    String[] lines = content.split("\\r?\\n");

                    for (int i = 0; i < lines.length; i++) {
                        if (lines[i].contains("\"0\": \"")) {
                            lines[i] = "\t\t\"0\": \"block_letters:" + fileName + "\",";
                        }
                        if (lines[i].contains("\"particle\": \"")) {
                            if (i == lines.length - 1) {
                                lines[i] = "\t\t\"particle\": \"block_letters:" + fileName + "\"";
                            } else {
                                lines[i] = "\t\t\"particle\": \"block_letters:" + fileName + "\"";
                            }
                        }
                    }
                    Files.write(Paths.get(copyFile), String.join("\n", lines).getBytes());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
