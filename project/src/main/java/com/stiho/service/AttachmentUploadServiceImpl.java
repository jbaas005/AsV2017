package com.stiho.service;

import com.stiho.model.Attachment;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author IS204_1
 */
@Service

/**
 *
 * uploads attachmentfile that is applied with the complaint.
 */
public class AttachmentUploadServiceImpl implements AttachmentUploadService {

    private String uploadPath;

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public Attachment upload(MultipartFile file) throws Exception {
        String name = file.getOriginalFilename();
        String path = uploadPath + hash(name) + "." + FilenameUtils.getExtension(name);
        Attachment attachment = new Attachment();
        attachment.setContentType(file.getContentType());
        attachment.setFileName(name);
        attachment.setPath(path);

        String[] acceptedMimeTypes = {"image/jpeg", "image/png", "image/gif", "image/bmp", "application/pdf", "application/x-pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/msword"};

        if (!Arrays.asList(acceptedMimeTypes).contains(file.getContentType())) {
            throw new Exception("Bestand is geen afbeelding");
        }

        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream
                    = new BufferedOutputStream(new FileOutputStream(new File(path)));
            stream.write(bytes);
            stream.close();
            return attachment;
        } catch (Exception e) {
            throw new Exception("You failed to upload " + path + " => " + e.getMessage());
        }
    }

    /**
     *
     * generates a unique hash so the upload can be followed.
     *
     * @param value
     * @return
     */
    private String hash(String value) {
        return UUID.randomUUID().toString();
    }

}
