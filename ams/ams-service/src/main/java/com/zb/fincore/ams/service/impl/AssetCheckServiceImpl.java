package com.zb.fincore.ams.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.facade.dto.req.QueryAssetTransactionRequest;
import com.zb.fincore.ams.facade.dto.req.QueryNotInProcessAssetRequest;
import com.zb.fincore.ams.facade.model.AssetTransactionModel;
import com.zb.fincore.ams.facade.model.InProcessAssetModel;
import com.zb.fincore.ams.facade.model.NotInProcessAssetModel;
import com.zb.fincore.ams.service.AssetCheckService;
import com.zb.fincore.ams.service.dal.dao.AssetCheckDao;

@Service
public class AssetCheckServiceImpl implements AssetCheckService {

    @Autowired
    private AssetCheckDao assetCheckDao;
    
	@Override
    @Transactional(readOnly = true)
	public PageQueryResponse<InProcessAssetModel> queryInProcessAssetList()
			throws Exception {
        PageQueryResponse<InProcessAssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        List<InProcessAssetModel> list= assetCheckDao.selectInProcessAsset();
        resp.setPageSize(list.size());
        resp.setPageNo(1);
        resp.setTotalCount(list.size());
        resp.setDataList(list);
		return resp;
	}
	
	@Override
    @Transactional(readOnly = true)
	public PageQueryResponse<NotInProcessAssetModel> queryNotInProcessAssetList(QueryNotInProcessAssetRequest req)
			throws Exception {
        PageQueryResponse<NotInProcessAssetModel> resp = BaseResponse.build(PageQueryResponse.class);
        List<NotInProcessAssetModel> list= assetCheckDao.selectNotInProcessAsset(req);
        resp.setPageSize(list.size());
        resp.setPageNo(1);
        Double total = 0.0;
        if (list!=null){
        	for (int i=0;i<list.size();i++){
        		total += list.get(i).getAssetAmount();
        	}
        }
        resp.setAddition(total.toString());
        resp.setTotalCount(list.size());
        resp.setDataList(list);
		return resp;
	}

	@Override
	public PageQueryResponse<AssetTransactionModel> queryAssetTransaction(QueryAssetTransactionRequest req) throws Exception {
        PageQueryResponse<AssetTransactionModel> resp = BaseResponse.build(PageQueryResponse.class);
        List<AssetTransactionModel> list= assetCheckDao.selectAssetTransaction();
        resp.setPageSize(req.getPageSize());
        resp.setPageNo(req.getPageNo());
        resp.setTotalCount(list.size());
        List<AssetTransactionModel> resultList = new ArrayList<AssetTransactionModel>();
        int start = req.getPageSize()*(req.getPageNo()-1);
        int end = req.getPageSize()*req.getPageNo();
        if (end>list.size()) end = list.size();
        for (int i=start;i<end;i++){
        	resultList.add(list.get(i));
        }
        resp.setDataList(resultList);
		return resp;
	}

}
