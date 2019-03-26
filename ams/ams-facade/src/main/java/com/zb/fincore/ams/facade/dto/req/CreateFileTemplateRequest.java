package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 创建文件模板请求
 */
public class CreateFileTemplateRequest extends BaseRequest {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 7179945016299648993L;

    @NotBlank(message = "模板编码不能为空")
    private String templateCode;

    @NotBlank(message = "文件序列号不能为空")
    private String fileSerialNo;

    @NotBlank(message = "文件名不能为空")
    private String fileName;

    @NotBlank(message = "文件路径不能为空")
    private String filePath;

    @NotBlank(message = "创建人不能为空")
    private String createBy;

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getFileSerialNo() {
        return fileSerialNo;
    }

    public void setFileSerialNo(String fileSerialNo) {
        this.fileSerialNo = fileSerialNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
