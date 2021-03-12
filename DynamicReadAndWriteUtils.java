package com.zhiwei.util;

import com.alibaba.fastjson.JSON;
import com.zhiwei.entity.NowcoderEntity;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动态读取excel和写excel用到的重复的工具类
 * @Authot:Administrator
 * @create:2021-03-12-12:56
 */
@Log4j2
public class DynamicReadAndWriteUtils {

    /**
     * 将动态读取表格中的无注解实体类，并动态生成表头数据的测试
     * @param mapList
     * @return
     */
    public static List<NowcoderEntity> listMapToListJavaType(List<Map<Integer, Object>> mapList){
        List<NowcoderEntity> entities = new ArrayList<>();
        for (Map<Integer, Object> data : mapList) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            //平台	来源	渠道	时间	标题	文本	地址	是否原发	命中词	原创作者 点赞数 浏览数 收藏数 回复数 转发数
            log.info("读取到数据dataMap:{}", JSON.toJSONString(data));
            NowcoderEntity nowcoderEntity = new NowcoderEntity();
            nowcoderEntity.setPlatForm((String) data.get(0));
            nowcoderEntity.setSource((String) data.get(1));
            nowcoderEntity.setChannel((String) data.get(2));
            nowcoderEntity.setTime((String) data.get(3));
            nowcoderEntity.setTitle((String) data.get(4));
            nowcoderEntity.setContent((String) data.get(5));
            nowcoderEntity.setUrl((String) data.get(6));
            String isOriginal = (String) data.get(7);
            if ("TRUE".equals(isOriginal) || "true".equals(isOriginal)){
                nowcoderEntity.setIsOriginal(true);
            } else if ("False".equals(isOriginal) || "false".equals(isOriginal)){
                nowcoderEntity.setIsOriginal(false);
            }else {
                nowcoderEntity.setIsOriginal(null);
            }
            log.info("isOriginal1：{}",data.get(7));

            nowcoderEntity.setHitWord((String) data.get(8));
            nowcoderEntity.setOriginalReporter((String) data.get(9));
            entities.add(nowcoderEntity);
        }
        return entities;
    }


    //实时生成动态表头
    public static List<List<String>> head() {
        //平台	来源	渠道	时间	标题	文本	地址	是否原发	命中词	原创作者
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<String>();
        head0.add("平台");
        List<String> head1 = new ArrayList<String>();
        head1.add("来源");
        List<String> head2 = new ArrayList<String>();
        head2.add("渠道");
        List<String> head3 = new ArrayList<String>();
        head3.add("时间");
        List<String> head4 = new ArrayList<String>();
        head4.add("标题");
        List<String> head5 = new ArrayList<String>();
        head5.add("文本");
        List<String> head6= new ArrayList<String>();
        head6.add("地址");
        List<String> head7= new ArrayList<String>();
        head7.add("是否原发");
        List<String> head8= new ArrayList<String>();
        head8.add("命中词");
        List<String> head9 = new ArrayList<String>();
        head9.add("原创作者");
        List<String> head10 = new ArrayList<String>();
        head10.add("点赞数");
        List<String> head11 = new ArrayList<String>();
        head11.add("浏览数");
        List<String> head12 = new ArrayList<String>();
        head12.add("收藏数");
        List<String> head13 = new ArrayList<String>();
        head13.add("回复数");
        List<String> head14 = new ArrayList<String>();
        head14.add("转发数");
//        List<String> head10= new ArrayList<String>();
//        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        list.add(head12);
        list.add(head13);
        list.add(head14);

        return list;
    }

}
