package com.zb.fincore.ams.facade.dto.resp;

import com.zb.fincore.ams.common.dto.BaseResponse;

import java.util.List;

/**
 * 功能: 借款人还款状态查询For P2P
 */
public class RepaymentStatusQueryResponse<T> extends BaseResponse {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 2469709391450351325L;


    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
