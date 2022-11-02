package nl.belastingdienst.voetbal_vereniging.controller.upload_download;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadWithFrontEndCode {

    // Make sure you have access in your browser
    @GetMapping("/files")
    ModelAndView fileUpload(){
        return new ModelAndView("index.html");
    }

}
