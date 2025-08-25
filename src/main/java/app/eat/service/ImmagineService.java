package app.eat.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ImmagineService {

    private final Cloudinary cloudinary;

    public ImmagineService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    // Caricamento singolo file
    public String caricaImmagine(MultipartFile file) throws IOException {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "recipes"));
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new IOException("Errore durante il caricamento dell'immagine su Cloudinary", e);
        }
    }

    // Caricamento multiplo
    public List<String> caricaImmagini(List<MultipartFile> files) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "recipes"));
            urls.add((String) uploadResult.get("secure_url"));
        }
        return urls;
    }
}
