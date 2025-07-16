package uz.pdp.startupproject.payload;

import lombok.*;
import uz.pdp.startupproject.entity.Attachment;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttachmentDTO implements Serializable {
    private Long id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String url;



    public AttachmentDTO(Attachment attachment){
        this.id = attachment.getId();
        this.fileName = attachment.getFileName();
        this.fileType = attachment.getFileType();
        this.filePath = attachment.getFilePath();
        this.fileSize = attachment.getFileSize();
        this.url = "/api/attachment/download/" + attachment.getId();
    }
}