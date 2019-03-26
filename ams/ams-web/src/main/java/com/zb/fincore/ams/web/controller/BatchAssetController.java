package com.zb.fincore.ams.web.controller;

import com.zb.fincore.ams.common.dto.BaseResponse;
import com.zb.fincore.ams.common.dto.PageQueryResponse;
import com.zb.fincore.ams.common.dto.QueryResponse;
import com.zb.fincore.ams.common.utils.ExportExcel;
import com.zb.fincore.ams.facade.BatchAssetFacade;
import com.zb.fincore.ams.facade.dto.req.*;
import com.zb.fincore.ams.facade.dto.resp.BatchCreateAssetResponse;
import com.zb.fincore.ams.facade.dto.resp.ExportBusinessCreditResponse;
import com.zb.fincore.ams.facade.dto.resp.RegisterExchangeAssetResponse;
import com.zb.fincore.ams.facade.model.*;
import com.zb.fincore.common.utils.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 批量录入资产
 * Created by MABIAO on 2017/6/15 0015.
 */
@RestController
@RequestMapping(value = "batchAssetService")
public class BatchAssetController {

    @Autowired
    private BatchAssetFacade batchAssetFacade;

    /**
     * getStringValue:读取excel文本. <br/>
     * @param cell
     * @return
     * @throws Exception
     */
    private static String getStringValue(Cell cell) throws Exception{
        if(cell == null) return null;

        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)){
            Date date = cell.getDateCellValue();
            Date date2 = new Date();
            String dateFormat = DateUtils.format(date, "yyyy-MM-dd");
            return DateUtils.format(cell.getDateCellValue(), "yyyy-MM-dd");//日期类型
        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            return NumberToTextConverter.toText(cell.getNumericCellValue());//数值类型
        }else{
            return cell.getStringCellValue().trim();//字符串类型
        }
    }

    /**
     * getStringValue:读取excel金额. <br/>
     * @param cell
     * @return
     * @throws Exception
     */
    private static BigDecimal getBigDecimalValue(Cell cell) throws Exception{
        if(cell == null) return null;

        BigDecimal bg = null;
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){//数值类型
            try {
                bg = new BigDecimal(NumberToTextConverter.toText(cell.getNumericCellValue()));
                return bg;
            } catch (Exception e) {
                return null;
            }
        }else{//字符串类型
            return null;
        }
    }

    /**
     * 文件导入
     * @return
     */
    @RequestMapping(value = "uploadBatchAsset",method = RequestMethod.POST)
//    @ResponseBody
    public BaseResponse uploadBatchAsset(HttpServletRequest request){
        InputStream is = null;
        Workbook wb = null;
        ImportBatchAssetRequest req = new ImportBatchAssetRequest();
        //计划表
        List<IssuePlanModel> planModelList = new ArrayList<IssuePlanModel>();
        try{
            is = request.getInputStream();
            wb = WorkbookFactory.create(is);//POI读取excel文件流

            Sheet sheet1 = wb.getSheetAt(0);//第一个sheet页

            int sheetNum = sheet1.getLastRowNum() + 1;
            for(int i = 1;i < sheetNum; i ++){
                Row row = sheet1.getRow(i);//获取第i行

                IssuePlanModel planModel = new IssuePlanModel();
                String str = getStringValue(row.getCell(0));
                planModel.setProductDayCount(Integer.valueOf(str));//产品天数
                planModel.setContractDayCount(Integer.valueOf(getStringValue(row.getCell(1))));//合同天数

                if(row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(row.getCell(2))){
                    planModel.setValueStartTime(row.getCell(2).getDateCellValue());//合同起息时间
                }
                planModel.setValueEndTime(row.getCell(3).getDateCellValue());//合同结息时间
                planModel.setFreeStartTime(row.getCell(4).getDateCellValue());//空档开始时间
                planModel.setFreeEndTime(row.getCell(4).getDateCellValue());//空档结束时间
                planModel.setSaleStartTime(row.getCell(5).getDateCellValue());//募集开始时间
                planModel.setSaleEndTime(row.getCell(6).getDateCellValue());//募集结束时间
                planModel.setProductValueStartTime(row.getCell(7).getDateCellValue());//产品计息开始时间
                planModel.setProductValueEndTime(row.getCell(8).getDateCellValue());//产品结息时间
                planModel.setSingleAmount(new BigDecimal(getStringValue(row.getCell(9))));//单包金额
                planModel.setAssetCount(Integer.valueOf(getStringValue(row.getCell(10))));//个数
                planModel.setTotalAmount(new BigDecimal(getStringValue(row.getCell(11))));//总金额
                planModel.setSubjectCode(getStringValue(row.getCell(12)));
                planModel.setCreditBusinessCode("");//授信业务编码

                planModelList.add(planModel);
            }
            req.setPlanModelList(planModelList);

            Sheet sheet2 = wb.getSheetAt(1);//第二个sheet页
            for(int j = 1;j < 2;j ++){
                Row row = sheet2.getRow(j);
                //授信
                BusinessCreditModel creditModel = new BusinessCreditModel();
                creditModel.setCreditBusinessCode(getStringValue(row.getCell(0)));//授信业务编号
                creditModel.setCreditLimitAmount(new BigDecimal(getStringValue(row.getCell(1))));//授信额度
                creditModel.setFinancingName(getStringValue(row.getCell(2)));//融资方
                creditModel.setCertNo(getStringValue(row.getCell(3)));//证件号码
                creditModel.setLegalPersonName(getStringValue(row.getCell(4)));//法人代表
                creditModel.setContactWay(getStringValue(row.getCell(5)));//联系方式
                creditModel.setAddress(getStringValue(row.getCell(6)));//地址
                creditModel.setFinancingPurpose(getStringValue(row.getCell(7)));//用途
                creditModel.setYieldRate(new BigDecimal(getStringValue(row.getCell(8))));//收益率
                creditModel.setValueStartTime(row.getCell(9).getDateCellValue());//起息日
                creditModel.setValueEndTime(row.getCell(10).getDateCellValue());//结息日
                creditModel.setDayCount(Integer.valueOf(getStringValue(row.getCell(11))));//天数
                creditModel.setRepaymentType(getStringValue(row.getCell(12)));//还款方式

                req.setCreditModel(creditModel);
            }
        }catch (Exception e){

        }

        return batchAssetFacade.importBatchAsset(req);
    }

    /**
     * 文件导入
     * @param req
     * @return
     */
    @RequestMapping(value = "importBatchAsset",method = RequestMethod.POST)
    public BatchCreateAssetResponse importBatchAsset(ImportBatchAssetRequest req){
        return batchAssetFacade.importBatchAsset(req);
    }

    /**
     * 合同模板录入
     */
    @RequestMapping(value = "createFileTemplate",method = RequestMethod.POST)
    public BaseResponse createFileTemplate(@RequestBody CreateFileTemplateRequest req){
        return batchAssetFacade.createFileTemplate(req);
    }

    /**
     * 合同模板详情
     */
    @RequestMapping(value = "queryFileTemplate",method = RequestMethod.POST)
    public QueryResponse<FileTemplateModel> queryFileTemplate(@RequestBody QueryFileTemplateRequest req){
        return batchAssetFacade.queryFileTemplate(req);
    }

    /**
     * 资产合同参数录入
     */
    @RequestMapping(value = "createFileTemplateParam",method = RequestMethod.POST)
    public BaseResponse createFileTemplateParam(@RequestBody CreateFileTemplateParamRequest req){
        return batchAssetFacade.createFileTemplateParam(req);
    }

    /**
     * 资产合同参数更新
     */
    @RequestMapping(value = "updateFileTemplateParam",method = RequestMethod.POST)
    public BaseResponse updateFileTemplateParam(@RequestBody CreateFileTemplateParamRequest req){
        return batchAssetFacade.updateFileTemplateParam(req);
    }

    /**
     * 资产合同参数详情
     */
    @RequestMapping(value = "queryFileTemplateParam",method = RequestMethod.POST)
    public QueryResponse<FileTemplateParamModel> queryFileTemplateParam(@RequestBody QueryFileTemplateParamRequest req){
        return batchAssetFacade.queryFileTemplateParam(req);
    }

    /**
     * 授信表列表
     */
    @RequestMapping(value = "exportBusinessCredit",method = RequestMethod.POST)
    public ExportBusinessCreditResponse exportBusinessCredit(@RequestBody ExportBusinessCreditRequest req){
        return batchAssetFacade.exportBusinessCredit(req);
    }

    /**
     * 授信表导出
     */
    @RequestMapping(value = "exportBusinessCreditExcel",method = RequestMethod.GET)
    public void exportBusinessCreditExcel(ExportBusinessCreditRequest req,HttpServletResponse response) throws Exception{
        String fileName = "授信列表.xls";
        // 设置头信息
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition","attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        ExportBusinessCreditResponse resp = batchAssetFacade.exportBusinessCredit(req);
        List<ExportBusinessCreditModel> modelList = resp.getModelList();
        ExportExcel<ExportBusinessCreditModel> ex = new ExportExcel<ExportBusinessCreditModel>();
        String[] headers = {"授信业务编号","授信额度","录入批次号","资产编号","资产金额","合同天数","合同起息(备案开始)","备案完成","空档期开始",
                "空档期结束","募集开始","募集结束","产品计息","产品结息(合同结息)"};
        try {
            ex.exportExcel("授信列表", headers, modelList, response.getOutputStream(), "yyyy-MM-dd");
        }catch (Exception e){

        }
    }

    /**
     * 资产合同列表
     */
    @RequestMapping(value = "queryAssetContractList",method = RequestMethod.POST)
    public PageQueryResponse<FileTemplateParamModel> queryAssetContractList(@RequestBody QueryAssetContractListRequest req){
        return batchAssetFacade.queryAssetContractList(req);
    }

    /**
     * 资产备案
     */
    @RequestMapping(value = "registerExchangeAsset",method = RequestMethod.POST)
    public RegisterExchangeAssetResponse registerExchangeAsset(@RequestBody RegisterExchangeAssetRequest req){
        return batchAssetFacade.registerExchangeAsset(req);
    }

    /**
     * 资产备案更新
     */
    @RequestMapping(value = "updateRegisterExchangeInfo",method = RequestMethod.POST)
    public BaseResponse updateRegisterExchangeInfo(@RequestBody UpdateRegisterExchangeInfoRequest req){
        return batchAssetFacade.updateRegisterExchangeInfo(req);
    }

    /**
     * 授信资产录入审核列表
     */
    @RequestMapping(value = "queryCreditAssetListForApproval",method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryCreditAssetListForApproval(@RequestBody QueryCreditAssetListRequest req){
        return batchAssetFacade.queryCreditAssetListForApproval(req);
    }

    /**
     * 授信资产录入审核
     */
    @RequestMapping(value = "approvalCreditAsset",method = RequestMethod.POST)
    public BaseResponse approvalCreditAsset(@RequestBody ApprovalCreditAssetRequest req){
        return batchAssetFacade.approvalCreditAsset(req);
    }

    /**
     * 备案资产备案详情
     */
    @RequestMapping(value = "queryAssetRegisterExchange",method = RequestMethod.POST)
    public QueryResponse<AssetExchangeRegisterModel> queryAssetRegisterExchange(@RequestBody QueryAssetRegisterExchangeRequest req){
        return batchAssetFacade.queryAssetRegisterExchange(req);
    }

    /**
     * 授信资产生成合同列表
     */
    @RequestMapping(value = "queryUnPackageAssetList",method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryUnPackageAssetList(@RequestBody QueryUnPackageAssetRequest req){
        return batchAssetFacade.queryUnPackageAssetList(req);
    }

    /**
     * 授信资产生成合同详情
     */
    @RequestMapping(value = "queryUnPackageAssetDetail",method = RequestMethod.POST)
    public QueryResponse<UnPackageAssetDetailModel> queryUnPackageAssetDetail(@RequestBody QueryUnPackageAssetDetailRequest req){
        return batchAssetFacade.queryUnPackageAssetDetail(req);
    }

    /**
     * 交易所备案资产查询列表
     */
    @RequestMapping(value = "queryRecordAssetList",method = RequestMethod.POST)
    public PageQueryResponse<AssetModel> queryRecordAssetList(@RequestBody QueryRecordAssetListRequest req){
        return batchAssetFacade.queryRecordAssetList(req);
    }

    /**
     * 生成合同
     */
    @RequestMapping(value = "generateContract",method = RequestMethod.POST)
    public BaseResponse generateContract(@RequestBody GenerateContractRequest req){
        return batchAssetFacade.generateContract(req);
    }

    /**
     * 查询备案记录列表
     */
    @RequestMapping(value = "queryExchangeRegisterRecordList",method = RequestMethod.POST)
    public PageQueryResponse<AssetExchangeRegisterModel> queryExchangeRegisterRecordList(@RequestBody QueryExchangeRegisterRecordListRequest req){
        return batchAssetFacade.queryExchangeRegisterRecordList(req);
    }

    /**
     * 查询备案记录详情
     */
    @RequestMapping(value = "queryExchangeRegisterRecordDetail",method = RequestMethod.POST)
    public QueryResponse<UnPackageAssetDetailModel> queryExchangeRegisterRecordDetail(@RequestBody QueryExchangeRegisterDetailRequest req){
        return batchAssetFacade.queryExchangeRegisterRecordDetail(req);
    }
}
