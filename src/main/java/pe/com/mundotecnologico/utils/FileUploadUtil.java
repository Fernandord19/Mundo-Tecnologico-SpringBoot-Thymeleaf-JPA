package pe.com.mundotecnologico.utils;

import java.io.*;
import java.nio.file.*;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {

	public static void saveFile(String nombreArchivo, MultipartFile multipartFile) throws IOException {

		String directorioBase = getDirectorioBase();
		
		Path uploadPath = Paths.get(directorioBase + "\\src\\main\\resources\\static\\img");

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(nombreArchivo);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + nombreArchivo, ioe);
		}
	}

	private static String getDirectorioBase() {
		// Obtener el directorio
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			// nothing to do
		}
		if (path == null || !path.exists()) {
			path = new File("");
		}

		String pathStr = path.getAbsolutePath();
		// Si se está ejecutando en eclipse, está al mismo nivel que el directorio de
		// destino. Si se implementa en el servidor, está al mismo nivel que el paquete
		// jar por defecto
		pathStr = pathStr.replace("\\target\\classes", "");

		return pathStr;
	}
}
