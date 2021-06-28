package web.service.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(EndPoint.FILE)
public class FileResource {
    // define location
    private static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/";
    private static final byte ERROR_STATUS = -1;
    private List<String> filenames = new ArrayList<>();

    @PostMapping(EndPoint.UPLOAD)
    public Object uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles) {
        try {
            for (MultipartFile file : multipartFiles) {
                String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                if (!filename.endsWith(".wav")) {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
                }
                Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
                copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
                filenames.add(filename);
            }
            return ResponseEntity.ok().body(filenames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(EndPoint.DOWNLOAD + EndPoint.FILENAME)
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @GetMapping(EndPoint.DOWNLOAD + EndPoint.LIST)
    public List<String> downloadAllFiles() {
        return filenames;
    }


    @GetMapping(EndPoint.DOWNLOAD + EndPoint.FILENAME + EndPoint.SIZE)
    public long getFileSize(@PathVariable("filename") String filename) {
        Path path = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if (!Files.exists(path)) {
            return ERROR_STATUS;
        }

        try {
            return Files.size(path);
        } catch (IOException e) {
            return ERROR_STATUS;
        }
    }
}
