package com.zhiwei.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.log4j.Log4j2;

/**
 * 要操作的实体类的监听器
 *
 * @Authot:zk
 * @create:2020-05-23-17:38
 */
@Log4j2
public class ExcelDatasListener extends AnalysisEventListener<NowcoderEntity> {



    @Override
    public void invoke(NowcoderEntity excelData, AnalysisContext analysisContext){
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
