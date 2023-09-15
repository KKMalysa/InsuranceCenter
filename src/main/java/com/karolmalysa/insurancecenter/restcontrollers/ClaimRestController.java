package com.karolmalysa.insurancecenter.restcontrollers;

import com.karolmalysa.insurancecenter.model.components.ClaimComponnent;
import com.karolmalysa.insurancecenter.model.dto.ClaimDto;
import com.karolmalysa.insurancecenter.model.entities.Claim;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimRestController {


    private final ClaimComponnent claimComponnent;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ClaimDto saveClaim(@RequestBody ClaimDto claimDto) {
        return claimComponnent.saveClaim(claimDto);
    }

    @GetMapping(value = "all", produces = "application/json")
    public List<ClaimDto> findAllClaims() {
        return  claimComponnent.findAll();
    }

    @GetMapping(produces = "application/json")
    public List<ClaimDto> findAllClaimsWithPagination(@PathParam("page") Integer pageNumber, @PathParam("size") Integer pageSize) {

        return  claimComponnent.findAll(pageNumber, pageSize);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Long id){
//        return new ResponseEntity<Claim>(claimComponnent.getClaimById(id), HttpStatus.OK); // zwraca 200 nawet jeśli claim jest nullem
        Claim claim = claimComponnent.getClaimById(id);
        if (claim == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(claim, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClaimById(@PathVariable("id") Long id){
        return new ResponseEntity<String>(claimComponnent.deleteClaim(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@Valid @RequestBody Claim claim, @PathVariable("id") Long id){
        return new ResponseEntity<Claim>(claimComponnent.updateClaim(claim, id),HttpStatus.OK);
    }

    @PostMapping(value = "/{claimId}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload a file for a claim")
    public ResponseEntity<?> uploadFile(
                @PathVariable Long claimId,
                @ApiParam(value = "File to upload", required = true, type = "file")
                @RequestParam("file") MultipartFile file) {

        Claim claim = claimComponnent.getClaimById(claimId);

        try {
            byte[] fileBytes = file.getBytes();
            claim.setFileContent(fileBytes);
            claimComponnent.saveClaim(new ClaimDto(claim));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);

    }

    @GetMapping("/{claimId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long claimId) {
        Claim claim = claimComponnent.getClaimById(claimId);

        if (claim.getFileContent() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN); // for txt files
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("claim_" + claimId + ".txt").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(claim.getFileContent());
    }

    @GetMapping("/{claimId}/view")
    public ResponseEntity<String> viewFile(@PathVariable Long claimId) {

        Claim claim = claimComponnent.getClaimById(claimId);
        String fileContent;


        if (claim.getFileContent() == null) {
            return ResponseEntity.notFound().build();
        }


        try {
            fileContent = new String(claim.getFileContent(),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading the file.");
        }

        return ResponseEntity.ok(fileContent);

    }

    @PostMapping(value = "/{claimId}/upload-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Upload an image for a claim")
    public ResponseEntity<?> uploadImage(
                    @PathVariable Long claimId,
                    @ApiParam(value = "Image to upload", required = true, type = "file")
                    @RequestParam("image") MultipartFile image) {

        Claim claim = claimComponnent.getClaimById(claimId);

        try {
            byte[] imageBytes = image.getBytes();
            claim.setImageContent(imageBytes);
            claimComponnent.saveClaim(new ClaimDto(claim));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);

    }

    @GetMapping("/{claimId}/download-image")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long claimId) {
        Claim claim = claimComponnent.getClaimById(claimId);

        if (claim.getImageContent() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);  // Założenie: zdjęcie to JPEG
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("claim_image_" + claimId + ".jpg").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(claim.getImageContent());
    }

    @GetMapping("/{claimId}/view-image")
    public ResponseEntity<byte[]> viewImage(@PathVariable Long claimId) {

        Claim claim = claimComponnent.getClaimById(claimId);
        HttpHeaders headers = new HttpHeaders();

        if (claim.getImageContent() == null) {
            return ResponseEntity.notFound().build();
        }

        headers.setContentType(MediaType.IMAGE_JPEG);  // Założenie: zdjęcie to JPEG

        return ResponseEntity.ok()
                .headers(headers)
                .body(claim.getImageContent());
    }
}
