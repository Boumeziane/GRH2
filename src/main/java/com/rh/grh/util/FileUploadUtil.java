package com.rh.grh.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

public class FileUploadUtil {

    // Sauvegarder un fichier uploadé dans un dossier
    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        // Créer le dossier s'il n'existe pas
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Impossible d'enregistrer le fichier : " + fileName, ioe);
        }
    }

    // Nettoyer le nom du fichier pour éviter les caractères dangereux
    public static String cleanFileName(String fileName) {
        return StringUtils.cleanPath(fileName);
    }

    // Supprimer un fichier existant
    public static void deleteFile(String uploadDir, String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        Files.deleteIfExists(filePath);
    }
}
