package iwona.pl.modol12project.controller;

import iwona.pl.modol12project.model.Image;
import iwona.pl.modol12project.service.OcrService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    private OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/quote-add")
    public String addImage(@RequestBody Image image) {
        return ocrService.adToDb(image);
    }

    @GetMapping("/quote-all")
    public List<Image> getAll() {
        return ocrService.getAll();
    }

    @DeleteMapping("/quote-all/{id}")
    public void delete(@PathVariable Long id){
        ocrService.delete(id);
    }
}
