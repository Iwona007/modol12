package iwona.pl.modol12project.service;

import iwona.pl.modol12project.dao.DaoRepo;
import iwona.pl.modol12project.model.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OcrService {

    @Value("${datapath}")
    private String dataPath;
    private DaoRepo daoRepo;

    @Autowired
    public OcrService(DaoRepo daoRepo) {
        this.daoRepo = daoRepo;
    }

    public String read(String url) {
        try {
            URL imageFile = new URL(url);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ITesseract instance = new Tesseract();
            instance.setDatapath(dataPath);
            instance.setTessVariable("user_defined_dpi", "300");
            instance.setLanguage("eng");
            System.out.println(bufferedImage);

            return instance.doOCR(bufferedImage);

        } catch (IOException | TesseractException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String adToDb(Image image) {
        String ocr = read(image.getUrl());
        image.setContent(ocr);
        daoRepo.addImage(image);
        return ocr;
    }

    public List<Image> getAll() {
        return daoRepo.getAll();
    }

    public void delete(Long id) {
        daoRepo.delete(id);
    }
}

