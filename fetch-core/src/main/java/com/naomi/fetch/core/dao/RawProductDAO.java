/**
 * 
 */
package com.naomi.fetch.core.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.naomi.fetch.core.model.RawProduct;

/**
 * @author liufei
 * 2014年3月17日 下午3:37:36
 */
@DAO(catalog="test")
public interface RawProductDAO {
	
	String TABLE = "raw_product";
	String FIELDS = "data_id,name,img,category,json";
	String ALLFIELDS = "id," + FIELDS;
	
	@SQL("INSERT INTO $TABLE($FIELDS) VALUES(:1.dataId,:1.name,:1.img,:1.category,:1.json)")
	@ReturnGeneratedKeys
	public int save(RawProduct product);
	
	@SQL("UPDATE $TABLE SET json=:1 WHERE id=:2")
	public int updateJson(String json,int id);

	@SQL("SELECT COUNT(1) FROM $TABLE")
	public int getCount();
	
	@SQL("SELECT $ALLFIELDS FROM $TABLE LIMIT :1,:2")
	public List<RawProduct> getRawProducts(int offset,int len);

	@SQL("DELETE FROM $TABLE WHERE id=:1")
	public int delete(int id);
	
}
