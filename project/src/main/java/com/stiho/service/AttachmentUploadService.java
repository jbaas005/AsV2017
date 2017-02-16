package com.stiho.service;

import com.stiho.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author IS204_1
 */
public interface AttachmentUploadService {
      public Attachment upload(MultipartFile file) throws Exception;
}
