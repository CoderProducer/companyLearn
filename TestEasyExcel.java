import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.converters.DefaultConverterLoader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.zhiwei.crawler.NewCoderCrawler;
import com.zhiwei.crawler.tools.util.ProxyInit;
import com.zhiwei.entity.ExcelDatasListener;
import com.zhiwei.entity.NowcoderEntity;
import com.zhiwei.util.ConverterData;
import com.zhiwei.util.DynamicReadAndWriteUtils;
import com.zhiwei.util.NoModelDataListener;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Authot:Administrator
 * @create:2021-03-05-18:32
 */
@Log4j2
public class TestEasyExcel {



    /**
     * 正常用注解读写excel
     */
    @Test
    public void testNormalRead() {
        ProxyInit.initProxy();
        String path = "牛客.xlsx";
        //1.创建读的工作簿对象
//        ExcelReaderBuilder readExcel = EasyExcel.read(path, NowcoderEntity.class,new ExcelDatasListener());
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
//        List<ConverterData> converterDatas = EasyExcel.read(path, ConverterData.class, new ConverterDataListener()).registerConverter(new CustomStringStringConverter()).sheet().doReadSync();

        //1.创建读的工作簿对象
        ExcelReaderBuilder readExcel = EasyExcel.read(path, NowcoderEntity.class,new ExcelDatasListener());
        //2.创建读的工作表对象
        ExcelReaderSheetBuilder sheet = readExcel.sheet();
        //3.读取工作表中的内容
        List<NowcoderEntity> nowCoderExcels = sheet.doReadSync();
        List<NowcoderEntity> entities = new ArrayList<>();
        if (!nowCoderExcels.isEmpty()){
            List<NowcoderEntity> list = NewCoderCrawler.crawlerAllDatas(nowCoderExcels);
            if (!list.isEmpty()){
                String writePath = "牛客-write.xlsx";
                EasyExcel.write(writePath).head(NowcoderEntity.class).sheet().doWrite(list);
            }
        }
    }


    /**
     * 动态写自定义表头的数据
     */
    @Test
    public void testEasyExcel(){
        ProxyInit.initProxy();
        String path = "牛客.xlsx";

        //1.创建读的工作簿对象
        ExcelReaderBuilder readExcel = EasyExcel.read(path, NowcoderEntity.class,new ExcelDatasListener());
        //2.创建读的工作表对象
        ExcelReaderSheetBuilder sheet = readExcel.sheet();
        //3.读取工作表中的内容
        List<NowcoderEntity> nowCoderExcels = sheet.doReadSync();
        List<NowcoderEntity> list = new ArrayList<>();

        long startTime = System.currentTimeMillis()/1000;
        log.info("startTime:{}",startTime);
        if (Objects.nonNull(nowCoderExcels) && !nowCoderExcels.isEmpty()) {
//            nowCoderExcels.forEach(data ->{//单条采集excel中url对应的数据
//                NowcoderEntity nowcoderEntity = NewCoderCrawler.crawlerNewCoderData(data);
//                list.add(nowcoderEntity);
//            });
            // 批量采集excel中url对应的数据
            list = NewCoderCrawler.crawlerAllDatas(nowCoderExcels);
        }
        log.info("list的大小:{}",list.size());
//        String fileName = TestFileUtil.getPath() + "nowCoder" + System.currentTimeMillis() + ".xlsx";
        String fileName = "牛客-write.xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(DynamicReadAndWriteUtils.head()).sheet("牛客网")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(list);
    }


    /**
     * 日期、数字或者自定义格式转换
     * <p>
     * 默认读的转换器{@link DefaultConverterLoader#loadDefaultReadConverter()}
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ConverterData}.里面可以使用注解{@link DateTimeFormat}、{@link NumberFormat}或者自定义注解
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void converterRead() {
        ProxyInit.initProxy();
        String path = "牛客.xlsx";
        //1.创建读的工作簿对象
//        ExcelReaderBuilder readExcel = EasyExcel.read(path, NowcoderEntity.class,new ExcelDatasListener());
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
//        List<ConverterData> converterDatas = EasyExcel.read(path, ConverterData.class, new ConverterDataListener()).registerConverter(new CustomStringStringConverter()).sheet().doReadSync();
        List<Map<Integer, Object>> listMap = EasyExcel.read(path, new NoModelDataListener()).sheet().doReadSync();

        // 这里注意 我们也可以registerConverter来指定自定义转换器， 但是这个转换变成全局了， 所有java为string,excel为string的都会用这个转换器。
        // 如果就想单个字段使用请使用@ExcelProperty 指定converter
        // .registerConverter(new CustomStringStringConverter())
        // 读取sheet
//                .sheet().doRead();
        List<NowcoderEntity> entities;
        if (!listMap.isEmpty()){
            log.info("size:{}",listMap.size());
            entities = DynamicReadAndWriteUtils.listMapToListJavaType(listMap);
            List<NowcoderEntity> list = NewCoderCrawler.crawlerAllDatas(entities);
            if (!list.isEmpty()){
                String writePath = "牛客-write.xlsx";
//                EasyExcel.write(writePath).head(NowcoderEntity.class).sheet().doWrite(list);
                EasyExcel.write(writePath)
                        // 这里放入动态头
                        .head(DynamicReadAndWriteUtils.head()).sheet("牛客网")
                        // 当然这里数据也可以用 List<List<String>> 去传入
                        .doWrite(list);
            }
        }
    }



    /**
     * 将动态读取表格中的无注解实体类，并动态生成表头数据的测试
     */
    @Test
    public void testWriteDynamic() {
        ProxyInit.initProxy();
        String path = "牛客.xlsx";
        //1.创建读的工作簿对象
//        ExcelReaderBuilder readExcel = EasyExcel.read(path, NowcoderEntity.class,new ExcelDatasListener());
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet
        List<Map<Integer, Object>> listMap = EasyExcel.read(path, new NoModelDataListener()).sheet().doReadSync();
        // 读取sheet
        List<NowcoderEntity> entities;
        if (!listMap.isEmpty()){
            log.info("size:{}",listMap.size());
            entities = DynamicReadAndWriteUtils.listMapToListJavaType(listMap);
            List<NowcoderEntity> list = NewCoderCrawler.crawlerAllDatas(entities);
            if (!list.isEmpty()){
                String writePath = "牛客-write.xlsx";
//                EasyExcel.write(writePath).head(NowcoderEntity.class).sheet().doWrite(list);
                EasyExcel.write(writePath)
                        // 这里放入动态头
                        .head(DynamicReadAndWriteUtils.head()).sheet("牛客网")
                        // 当然这里数据也可以用 List<List<String>> 去传入
                        .doWrite(list);
            }
        }

    }


}
