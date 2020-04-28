package iwona.pl.modol12project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import iwona.pl.modol12project.model.Image;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OcrControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DirtiesContext
    void addImage() throws Exception {
        URL url = new URL("https://yourpositiveoasis.com/wp-content/uploads/2018/06/35145383_2037359446335206_7995205548490358784_n.jpg");
        Image image = new Image(String.valueOf(url), "");
        String jsonRequest =objectMapper.writeValueAsString(image);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/ocr/add")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void should_get_all_images_test() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/ocr/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].url")
                        .value("https://yourpositiveoasis.com/wp-content/uploads/2018/06/35429668_2050099711727846_1823917686845865984_n.jpg"));
    }
}
