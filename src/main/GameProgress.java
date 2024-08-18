package main;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress {

    private int level;
    private int health;
    private int weapons;

    public GameProgress(int level, int health, int weapons) {
        this.level = level;
        this.health = health;
        this.weapons = weapons;
    }


    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(gameProgress);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void zipFiles(String zipFilePath, String[] filesToZip) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            for (String fileToZip : filesToZip) {
                File file = new File(fileToZip);
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }

                    zipOutputStream.closeEntry();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (String fileToZip : filesToZip) {
            File file = new File(fileToZip);
            file.delete();
        }
    }

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(1, 100, 3);
        GameProgress game2 = new GameProgress(2, 90, 5);
        GameProgress game3 = new GameProgress(3, 80, 7);

        saveGame("/Users/admin/Games/GunRunner/savegames/save1.dat", game1);
        saveGame("/Users/admin/Games/GunRunner/savegames/save2.dat", game2);
        saveGame("/Users/admin/Games/GunRunner/savegames/save3.dat", game3);

        String[] filesToZip = {
                "/Users/admin/Games/GunRunner/savegames/save1.dat",
                "/Users/admin/Games/GunRunner/savegames/save2.dat",
                "/Users/admin/Games/GunRunner/savegames/save3.dat"
        };
        zipFiles("/Users/admin/Games/GunRunner/savegames/zip.zip", filesToZip);
    }
}
