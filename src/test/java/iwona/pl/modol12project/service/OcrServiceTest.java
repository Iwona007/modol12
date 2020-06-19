package iwona.pl.modol12project.service;

import iwona.pl.modol12project.dao.DaoRepo;
import iwona.pl.modol12project.model.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class OcrServiceTest {

    @Mock
    DaoRepo daoRepo;
    @InjectMocks
    OcrService ocrService;

    @BeforeEach
    public void init() {
        given(daoRepo.getAll()).willReturn(prepareMockData());
    }

    private final String testResourcesDataPath = "D:\\Iwona\\Pobrane\\translation";

    private List<Image> prepareMockData() {
        List<Image> images = new ArrayList<>();
        images.add(new Image("https://yourpositiveoasis.com/wp-content/uploads/2018/06/35429668_2050099711727846_1823917686845865984_n.jpg",
                "Knowing what you need to do to improve your life takes wisdom. Pushing yourself to do it takes courage. P i PA Te"));
        images.add(new Image("https://www.goalcast.com/wp-content/uploads/2017/06/Oprah-on-gratitude-Be-thankful-for-what-you-have-1280x720.jpg",
                "Be thankful for what you have; you‘ll end up having more. If you *) N 3 concentrate on what you don‘t have, Q " +
                        "you will never, ever have enough. | [— Oprah Winfrey * 1 oalcast "));
        return images;
    }

    @Test
    @DisplayName("Should read url image")
    void shouldReadUrlImage() throws IOException, TesseractException {
        System.out.println("doOCR on a URL image");
//     given
        URL imageFile = new URL("https://www.joshloe.com/wp-content/uploads/2019/09/strive-for-progress-not-perfection-quote-quoteoftheday-quotestoliveby-motiva-735x445.jpg");
        BufferedImage bi = ImageIO.read(imageFile);
        ITesseract instance = new Tesseract();
        instance.setDatapath(this.testResourcesDataPath);
//     when
        instance.setTessVariable("user_defined_dpi", "300");
        instance.setLanguage("eng");
        String result = instance.doOCR(bi);
        System.out.println(result);
        String expResult = "progress\n" +
                "not\n" +
                "perfection\n";
//       then
        assertEquals(expResult, result);
    }

    @Test
    @DisplayName("Should get all images")
    void shouldGetAll() {
        List<Image> images = ocrService.getAll();
        assertThat(images, Matchers.hasSize(2));
    }
}
