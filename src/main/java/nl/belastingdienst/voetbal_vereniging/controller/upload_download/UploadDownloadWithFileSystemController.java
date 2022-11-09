package nl.belastingdienst.voetbal_vereniging.controller.upload_download;

import nl.belastingdienst.voetbal_vereniging.dto.FileUploadResponse;
import nl.belastingdienst.voetbal_vereniging.exception.RecordNotFoundException;
import nl.belastingdienst.voetbal_vereniging.service.DatabaseService;
import nl.belastingdienst.voetbal_vereniging.service.FileStorageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
public class UploadDownloadWithFileSystemController {

    private final FileStorageService fileStorageService;
    private final DatabaseService databaseService;

    public UploadDownloadWithFileSystemController(FileStorageService fileStorageService, DatabaseService databaseService) {
        this.fileStorageService = fileStorageService;
        this.databaseService = databaseService;
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @PostMapping("single/upload")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file){

        String fileName = fileStorageService.storeFile(file);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileName, contentType, url );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.downLoadFile(fileName);
        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @GetMapping("/download/allNames")
    List<String> downLoadMultipleFile() {
        return fileStorageService.downLoad();
    }

    @RolesAllowed({"ROLE_USER", "ROLE_TRAINER"})
    @PostMapping("/multiple/upload")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile[] files) {
        if(files.length > 7) {
            throw new RuntimeException("to many files");
        }
        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            String fileName = fileStorageService.storeFile(file);

            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();

            String contentType = file.getContentType();

            FileUploadResponse response = new FileUploadResponse(fileName, contentType, url );
            uploadResponseList.add(response);

        });
        return uploadResponseList;
    }

}
