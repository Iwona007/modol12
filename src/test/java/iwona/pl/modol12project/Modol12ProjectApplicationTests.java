package iwona.pl.modol12project;

import iwona.pl.modol12project.controller.OcrController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Modol12ProjectApplicationTests {

    @Autowired
    private OcrController ocrController;

    @Test
    void contextLoads() {
        assertThat(ocrController).isNotNull();
    }

}
