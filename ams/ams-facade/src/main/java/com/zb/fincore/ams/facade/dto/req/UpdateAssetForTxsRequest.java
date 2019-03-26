package com.zb.fincore.ams.facade.dto.req;

import com.zb.fincore.ams.common.dto.BaseRequest;

import java.util.List;

/**
 * Created by MABIAO on 2017/7/7 0007.
 */
public class UpdateAssetForTxsRequest extends BaseRequest{

    private List<String> assetCodes;

    public List<String> getAssetCodes() {
        return assetCodes;
    }

    public void setAssetCodes(List<String> assetCodes) {
        this.assetCodes = assetCodes;
    }
}
