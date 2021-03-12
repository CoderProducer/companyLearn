package com.zhiwei.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 要操作的实体类的监听器
 *
 * @Authot:zk
 * @create:2020-05-23-17:38
 */
public class ExcelDataListener extends AnalysisEventListener<DanMuTempSecondEntity> {

    /**
     * 日志
     */
    private Logger log= LoggerFactory.getLogger(ExcelDataListener.class);


    @Override
    public void invoke(DanMuTempSecondEntity excelData, AnalysisContext analysisContext){
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
