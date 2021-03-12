package com.zhiwei.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 直接用map接收数据
 * @Authot:Administrator
 * @create:2021-03-05-19:03
 */
@Log4j2
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, Object>> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Map<Integer, Object>> list = new ArrayList<Map<Integer, Object>>();

    @Override
    public void invoke(Map<Integer, Object> data, AnalysisContext context) {
        //获取了表格多少列
//        Integer columns = data.size();
        //获取了表格哪一行
//        Integer row = context.readRowHolder().getRowIndex();
        data.entrySet().forEach(entity ->{
            Object value = entity.getValue();
            if (Objects.isNull(value)){
                entity.setValue(null);
            }
//            log.info("index:{},value:{}",entity.getKey(),entity.getValue());
        });
//        log.info("解析到一条数据:{}", JSON.toJSONString(data));
//        list.add(data);
//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            list.clear();
//        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }

}
