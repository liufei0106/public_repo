/**
 * 
 */
package com.naomi.fetch.core.model;

/**
 * @author liufei TODO com.naomi.fetch.core.model RawProduct.java 2014年10月21日
 *         下午2:00:51
 */
public class RawProduct {

	private int id;
	private long dataId;
	private String name;
	private String img;
	private String category;
	private String json;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDataId() {
		return dataId;
	}

	public void setDataId(long dataId) {
		this.dataId = dataId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
