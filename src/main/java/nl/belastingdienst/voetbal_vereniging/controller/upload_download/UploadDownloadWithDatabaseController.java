package nl.belastingdienst.voetbal_vereniging.controller.upload_download;

import nl.belastingdienst.voetbal_vereniging.dto.FileDocument;
import nl.belastingdienst.voetbal_vereniging.dto.FileUploadResponse;
import nl.belastingdienst.voetbal_vereniging.service.DatabaseService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class UploadDownloadWithDatabaseController {

    private final DatabaseService databaseService;

    public UploadDownloadWithDatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @PostMapping("single/uploadDb")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        FileDocument fileDocument = databaseService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        return databaseService.singleFileDownload(fileName, request);
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @PostMapping("/multiple/upload/db")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files selected");
        }

        return databaseService.createMultipleUpload(files);

    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @GetMapping("/getAll/db")
    public Collection<FileDocument> getAllFromDB(){
        return databaseService.getALlFromDB();
    }

}
