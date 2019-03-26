package com.zb.fincore.ams.facade.model;

import java.io.Serializable;
import java.util.List;

/**
 * 批量导入错误信息
 * Created by MABIAO on 2017/6/21 0021.
 */
public class ErrorModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1540941558779303619L;

    List<String> errorList ;

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
