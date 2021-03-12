package com.zhiwei.util;

/**
 * @Authot:Administrator
 * @create:2021-03-05-18:34
 */

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

/**
 * 基础数据类.这里的排序和excel里面的排序一致
 *
 * @author Jiaju Zhuang
 **/
@Data
public class ConverterData {
    /**
     * 我自定义 转换器，不管数据库传过来什么 。我给他加上“自定义：”
     */
//    @ExcelProperty(converter = CustomStringStringConverter.class)
//    private String string;
//    /**
//     * 这里用string 去接日期才能格式化。我想接收年月日格式
//     */
//    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
//    private String date;
//    /**
//     * 我想接收百分比的数字
//     */
//    @NumberFormat("#.##%")
//    private String doubleData;

//    @ExcelProperty("平台")
    @ExcelProperty(converter = CustomStringStringConverter.class)
    private String platForm;
    @ExcelProperty("来源")
    private String source;
    @ExcelProperty("渠道")
    private String channel;
    @ExcelProperty("时间")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String time;
    @ExcelProperty("标题")
    private String title;
    @ExcelProperty("文本")
    private String content;
    @ExcelProperty("地址")
    private String url;
    @ExcelProperty("是否原发")
    private Boolean isOriginal;
    @ExcelProperty("命中词")
    private String hitWord;
    @ExcelProperty("原创作者")
    private String originalReporter;
    @ExcelProperty("点赞数")
    private Integer likerNum;
    @ExcelProperty("浏览数")
    private Integer readNum;
    @ExcelProperty("收藏数")
    private Integer collectNum;
    @ExcelProperty("回复数")
    private Integer replyNum;
    @ExcelProperty("转发数")
    private Integer postNum;
}
