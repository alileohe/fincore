package com.zb.fincore.pms.service;

import com.zb.fincore.common.utils.DateUtils;
import com.zb.fincore.pms.common.Constants;
import com.zb.fincore.pms.service.dal.dao.SequenceDao;
import com.zb.fincore.pms.service.dal.model.Sequence;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自动获取 Sequence 的公用Service服务实现类
 *
 * @author zhangxin
 * @create 2017-02-23 10:11
 */
@Service
public class SequenceService {

    /**
     * 日志记录器
     */
    private static Logger logger = LoggerFactory.getLogger(SequenceService.class);

    @Autowired
    private SequenceDao sequenceDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public String generateBusinessCode(String codePrefix, String sequenceNamePrefix, int formatLength) throws Exception {
        String businessCode = null;
        try {
            String yearMonth = DateUtils.format(DateUtils.now(), DateUtils.DATE_FORMAT_YYMM);
            if (StringUtils.isBlank(yearMonth)) {
                throw new RuntimeException("格式化系统时间失败");
            }
            //序列名称,前缀加年月 01 + 1701
            String sequenceName = sequenceNamePrefix + codePrefix + yearMonth;
            Long sequence = getSequence(sequenceName);
            if (null == sequence || sequence.longValue()<=0) {
                throw new RuntimeException("获取序列失败");
            }
            businessCode = codePrefix + yearMonth + String.format("%0"+formatLength+"d", sequence);
        } catch (Exception e) {
            logger.error("生成业务编码错误", e);
            throw e;
        }
        return businessCode;
    }

    /**
     * 获取当前最新的sequence值
     *
     * @param sequenceName
     * @return
     * @throws Exception
     */
    private Long getSequence(String sequenceName) throws Exception {
        Long latestVal = null;
        Sequence sequence = sequenceDao.selectByName(sequenceName);
        if (sequence == null) {
            //未存在sequence
            sequence = new Sequence();
            sequence.setSequenceName(sequenceName);
            sequence.setCurrentVal(1L);
            sequence.setSequenceStep(1);
            sequenceDao.insertSelective(sequence);
            latestVal = sequence.getCurrentVal();
        } else {
            latestVal = sequence.getCurrentVal() + sequence.getSequenceStep();
            sequence.setCurrentVal(latestVal);
            int row = sequenceDao.updateValWithLock(sequence);
            if (row <= 0) {
                throw new RuntimeException("乐观锁更新数据库失败");
            }
        }
        return latestVal;
    }
}
