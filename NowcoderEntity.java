package com.zhiwei.entity;

import lombok.Data;

/**
 * 牛客网浏览数等数据的实体
 *
 * @Authot:Administrator
 * @create:2021-02-25-14:57
 */
@Data
public class NowcoderEntity {
//平台	来源	渠道	时间	标题	文本	地址	是否原发	命中词	原创作者 点赞数 浏览数 收藏数 回复数 转发数
//    @ExcelProperty("平台")
    private String platForm;
//    @ExcelProperty("来源")
    private String source;
//    @ExcelProperty("渠道")
    private String channel;
//    @ExcelProperty("时间")
    private String time;
//    @ExcelProperty("标题")
    private String title;
//    @ExcelProperty("文本")
    private String content;
//    @ExcelProperty("地址")
    private String url;
//    @ExcelProperty("是否原发")
    private Boolean isOriginal;
//    @ExcelProperty("命中词")
    private String hitWord;
//    @ExcelProperty("原创作者")
    private String originalReporter;
//    @ExcelProperty("点赞数")
    private Integer likerNum;
//    @ExcelProperty("浏览数")
    private Integer readNum;
//    @ExcelProperty("收藏数")
    private Integer collectNum;
//    @ExcelProperty("回复数")
    private Integer replyNum;
//    @ExcelProperty("转发数")
    private Integer postNum;
}
