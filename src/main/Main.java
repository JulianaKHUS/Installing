import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        File gamesDir = new File("Games");
        if (!gamesDir.exists()) {
            if (gamesDir.mkdir()) {
                log.append("Директория Games была успешно создана\n");

                File game1 = new File("Games/Game1.txt");
                File game2 = new File("Games/Game2.txt");

                try {
                    if (game1.createNewFile()) {
                        log.append("Файл Game1.txt был успешно создан\n");
                    }
                    if (game2.createNewFile()) {
                        log.append("Файл Game2.txt был успешно создан\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File temp = new File("Games/temp.txt");
                try (FileWriter writer = new FileWriter(temp)) {
                    writer.write(log.toString());
                    log.append("Файл temp.txt был успешно создан и заполнен логом\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(log.toString());
            } else {
                System.out.println("Не удалось создать директорию Games");
            }
        } else {
            System.out.println("Директория Games уже существует");
        }
    }
}
