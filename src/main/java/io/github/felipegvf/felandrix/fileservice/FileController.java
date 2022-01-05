package io.github.felipegvf.felandrix.fileservice;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {

    @GetMapping("/file")
    public ResponseEntity<Resource> file() throws IOException, InvocationTargetException, IllegalAccessException {
        FileService service = new FileService();
        Example e = new Example();
        e.setCampo("Campo");
        e.setValor(1.2);
        e.setValorLong(1L);
        List<Example> examples = new ArrayList<>();
        examples.add(e);
        BlaBla bla = service.listToResource(examples, Example.class);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok().headers(header).contentLength(bla.length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(bla.resource);
    }

}
