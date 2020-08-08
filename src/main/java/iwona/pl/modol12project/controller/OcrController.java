package iwona.pl.modol12project.controller;

import io.swagger.annotations.ApiOperation;
import iwona.pl.modol12project.model.Image;
import iwona.pl.modol12project.service.OcrService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://modol12-angular3.herokuapp.com")
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/ocr")
public class OcrController {

    private OcrService ocrService;
    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @ApiOperation(value="Add a new quote")
    @PostMapping("/add")
    public String addImage(@RequestBody Image image) {
        return ocrService.adToDb(image);
    }

    @ApiOperation(value= "Find all quotes")
    @GetMapping("/all")
    public List<Image> getAll() {
        return ocrService.getAll();
    }

    @ApiOperation(value= "Delete quote by id")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        ocrService.delete(id);
    }
}
