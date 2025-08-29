import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

        }

    }

    public List<Task> load() {

    }


}
