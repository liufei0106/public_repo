/**
 * 
 */
package com.naomi.fetch.core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.naomi.fetch.core.model.Product;

/**
 * @author liufei
 * TODO
 * com.naomi.fetch.core.util ExcelUtil.java
 * 2014年10月22日 下午2:54:54
 */
public class ExcelUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	
	public static void writeExcel(List<Product> list,String file) throws IOException{
		// 获取总列数
        int countColumnNum = 10;
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        // sheet 对应一个工作页
        HSSFSheet sheet = hwb.createSheet("datasheet");
        HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
        HSSFCell[] firstcell = new HSSFCell[countColumnNum];
        String[] names = new String[countColumnNum];
        names[0] = "商品ID";
        names[1] = "商品名称";
        names[2] = "分类";
        names[3] = "是否有货";
        names[4] = "价格";
        names[5] = "品牌";
        names[6] = "型号";
        names[7] = "商品链接";
        names[8] = "商品图片链接";
        names[9] = "商品属性";
        for (int j = 0; j < countColumnNum; j++) {
            firstcell[j] = firstrow.createCell(j);
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));
        }
        for (int i = 0; i < list.size(); i++) {
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            Product product = list.get(i);
            HSSFCell cellItemId = row.createCell(0);
            cellItemId.setCellValue(product.getItemId());
            
            HSSFCell celltitle = row.createCell(1);
            celltitle.setCellValue(new HSSFRichTextString(product.getTitle()));
            
            HSSFCell cellCategory = row.createCell(2);
            cellCategory.setCellValue(product.getCategory());
            
            HSSFCell cellAvailable = row.createCell(3);
            cellAvailable.setCellValue(product.isAvailable()?"有货":"无货");
            
            HSSFCell cellPrice = row.createCell(4);
            cellPrice.setCellValue(product.getPrice());
            
            HSSFCell cellBrand = row.createCell(5);
            cellBrand.setCellValue(product.getBrand());
            
            HSSFCell cellModel = row.createCell(6);
            cellModel.setCellValue(product.getModel());
            
            HSSFCell cellLink = row.createCell(7);
            cellLink.setCellValue(product.getLink());
            
            HSSFCell cellImgLink = row.createCell(8);
            cellImgLink.setCellValue(product.getImgLink());
            
            HSSFCell cellProps = row.createCell(9);
            if(MapUtils.isNotEmpty(product.getProps())){
            	StringBuffer sb = new StringBuffer();
            	for(Map.Entry<String, String> me : product.getProps().entrySet()){
            		sb.append(me.getKey()+":"+me.getValue()+"\r\n");
            	}
            	cellProps.setCellValue(new HSSFRichTextString(sb.toString()));
            }
            logger.info("write excel dataId:{},category:{},title:{}", new Object[]{product.getItemId(),product.getCategory(),product.getTitle()});
        }
        // 创建文件输出流，准备输出电子表格
        OutputStream out = new FileOutputStream(file);
        hwb.write(out);
        out.close();
        logger.info("数据库导出成功");
	}
	
}
