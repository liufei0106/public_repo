/**
 * 
 */
package com.naomi.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.naomi.fetch.core.dao.RawProductDAO;
import com.naomi.fetch.core.model.Product;
import com.naomi.fetch.core.model.RawProduct;
import com.naomi.fetch.core.util.ExcelUtil;
import com.wzzx.rose.junit.RoseTestCase;

/**
 * @author liufei TODO com.naomi.test TestJsoup.java 2014年10月21日 上午11:14:42
 */
public class TestJsoup extends RoseTestCase{
	@Autowired
	private RawProductDAO dao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(TestJsoup.class);

	@Test
	@Ignore
	public void testJsoup() throws IOException, InterruptedException {
		String cameraUrl = "http://list.tmall.com//search_product.htm"
				+ "?spm=3.7396704.20000025.4.4Mkvzs&cat=50024402&auction_tag=7809&sort=s&acm=tt-1143110-39123.1003.8.110802&vmarket=0&theme=285&tab=mall"
				+ "&from=sn_1_cat&pos=3&style=g&search_condition=48&uuid=110802&hybrid=true&abtest=&scm=1003.8.tt-1143110-39123.OTHER_1412669006897_110802";
		fetchData(cameraUrl,"相机");
//		
		Thread.sleep(5000);
		//笔记本
		String notebookUrl = "http://list.tmall.com/search_product.htm?spm=3.7396704.20000024.4.4Mkvzs&cat=50024399&sort=s&auction_tag=7809&acm=tt-1143110-39121.1003.8.110812&"
				+ "style=g&vmarket=0&search_condition=48&active=1&uuid=110812&abtest=&scm=1003.8.tt-1143110-39121.OTHER_1414663350847_110812&pos=1";
		fetchData(notebookUrl,"笔记本");
		Thread.sleep(5000);
		
		//台式机
		String pcUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.17.q5wCxs&cat=50047396&sort=s&"
				+ "style=g&search_condition=7&from=sn_1_rightnav&industryCatId=50047396#J_crumbs";
		fetchData(pcUrl,"台式机");
		Thread.sleep(5000);
		
		//服务器
		String serverUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.23.q5wCxs&cat=50047398&sort=s&style=g&"
				+ "search_condition=7&from=sn_1_cat&active=1&industryCatId=50047396#J_crumbs";
		fetchData(serverUrl,"服务器");
		Thread.sleep(5000);
		//手机
		String mobileUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.12.q5wCxs&auction_tag=7809&search_condition=48&cat=50024400&sort=s&style=g&vmarket=0";
		fetchData(mobileUrl,"手机");
		Thread.sleep(5000);
		//空调
		String airConditionUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.3.q5wCxs&cat=50930001&sort=s&style=g&vmarket=40898&search_condition=7&from=sn_1_prop&industryCatId=50900004#J_crumbs";
		fetchData(airConditionUrl,"空调");
		Thread.sleep(5000);
		//冰箱
		String friUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.1.q5wCxs&cat=50918004&sort=s&style=g&vmarket=40898&search_condition=4&from=sn_1_prop&industryCatId=50900004#J_crumbs";
		fetchData(friUrl,"冰箱");
		Thread.sleep(5000);
		//电视
		String tvUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.4.q5wCxs&cat=50928001&sort=s&style=g&vmarket=40898&search_condition=23&from=sn_1_rightnav&industryCatId=50900004";
		fetchData(tvUrl,"电视");
		Thread.sleep(5000);
		//洗衣机
		String washUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.2.q5wCxs&cat=50918005&sort=s&style=g&vmarket=40898&search_condition=7&from=sn_1_prop&industryCatId=50900004#J_crumbs";
		fetchData(washUrl,"洗衣机");
		Thread.sleep(5000);
		//油烟机
		String smokeUrl = "http://list.tmall.com/search_product.htm?spm=141.3067357.1.8.q5wCxs&auction_tag=7809&search_condition=71&cat=50924003&sort=s&style=g&vmarket=40898&from=sn_1_rightnav&suggest=0_1&active=1";
		fetchData(smokeUrl,"油烟机");
		Thread.sleep(5000);
		//排气扇
		String paiqiUrl = "http://list.tmall.com/search_product.htm?spm=a220m.1000858.0.0.PsGMxw&cat=51248028&q=%C5%C5%C6%F8%C9%C8&sort=s&style=g&search_condition=7&from=sn_1_rightnav#J_crumbs";
		fetchData(paiqiUrl,"排气扇");
		Thread.sleep(5000);
		
	}
	
	
	public void fetchData(String url,String category) throws IOException, InterruptedException{
		long start = System.currentTimeMillis();
		Document doc = Jsoup.connect(url).timeout(10000).get();
		int totalPage = Integer
				.parseInt(doc.select(".ui-page-skip").first()
						.getElementsByAttributeValue("name", "totalPage")
						.attr("value"));
		doProcess(url,category);
		if (totalPage > 1) {
			for (int i = 1; i < 7; i++) {
				String nextUrl = url+"&s="+((i+1)*60);
				doProcess(nextUrl,category);
				Thread.sleep(5000);
			}
		}
		long end = System.currentTimeMillis();
		logger.info("category {} fetch cost :{}s", new Object[]{category,(end-start)/1000});
	}
	
	public void doProcess(String url,String category) throws IOException{
		Document doc = Jsoup.connect(url).timeout(10000).get();
		Elements elements = doc.select(".product");
		if (elements != null && elements.size() > 0) {
			for (Element element : elements) {
				String idStr = element.attr("data-id");
				if (StringUtils.isNotBlank(idStr)) {
					long id = Long.parseLong(idStr.trim());
					Elements titleElements = element.select(".productTitle a");
					StringBuilder titleSb = new StringBuilder();
					if (titleElements != null && titleElements.size() > 0) {
						for (Element titleElement : titleElements) {
							titleSb.append(titleElement.html() + " ");
						}
					}
					RawProduct product = new RawProduct();
					product.setDataId(id);
					product.setName(titleSb.toString());
					product.setCategory(category);
					dao.save(product);
					logger.info("product id:{},product title:{}", new Object[] {
							id, titleSb.toString()});
				}
			}
		}
	}
	
	@Test
	@Ignore
	public void getItemDetail(){
		String urlFormat = "http://hws.m.taobao.com/cache/wdetail/5.0/?id=%s&ttid=2013@taobao_h5_1.0.0";
		List<RawProduct> list = dao.getRawProducts(0, Integer.MAX_VALUE);
		if(CollectionUtils.isNotEmpty(list)){
			for(RawProduct product : list){
				String url = String.format(urlFormat, product.getDataId()+"");
				try {
					String response = Jsoup.connect(url).timeout(10000).get().text();
					dao.updateJson(response, product.getId());
					logger.info("update product id:{},data_id:{},json:{}", new Object[]{product.getId(),product.getDataId(),response});
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("product fetch error " + product.getId() +e.getMessage(), e);
				}
			}
		}
	}
	
	@Test
	@Ignore
	public void testJson() throws IOException{
		String response = Jsoup.connect("http://hws.m.taobao.com/cache/wdetail/5.0/?id=41486485933&ttid=2013@taobao_h5_1.0.0").get().text();
		logger.info("response is {}", response);
	}
	
	@Test
	public void parseJson() throws IOException{
		List<RawProduct> rawList = dao.getRawProducts(0, Integer.MAX_VALUE);
		List<Product> list = new ArrayList<Product>();
		if(CollectionUtils.isNotEmpty(rawList)){
			for(RawProduct rp : rawList){
				try {
					String jsonStr = rp.getJson();
					JSONObject json = JSONObject.parseObject(jsonStr);
					Product product = new Product();
					JSONObject itemInfo = json.getJSONObject("data").getJSONObject("itemInfoModel");
					product.setItemId(itemInfo.getLongValue("itemId"));
					product.setTitle(itemInfo.getString("title"));
					product.setCategory(rp.getCategory());
					product.setLink("http://detail.tmall.com/item.htm?id="+itemInfo.getLongValue("itemId"));
					product.setImgLink(itemInfo.getJSONArray("picsPath").getString(0));
					product.setAvailable(true);
					JSONArray propsArray = json.getJSONObject("data").getJSONArray("props");
					if(propsArray!=null && propsArray.size()>0){
						Map<String,String> propMaps = new HashMap<String,String>();
						for(int i=0;i<propsArray.size();i++){
							JSONObject obj = propsArray.getJSONObject(i);
							String name = obj.getString("name");
							String value = obj.getString("value");
							propMaps.put(name, value);
							if(StringUtils.contains(name, "品牌")){
								product.setBrand(value);
							}
							if(StringUtils.contains(name, "型号")){
								product.setModel(value);
							}
						}
						product.setProps(propMaps);
					}
					//处理价格
					String priceJsonStr = json.getJSONObject("data").getJSONArray("apiStack").getJSONObject(0).getString("value");
					JSONArray priceUnitArray = JSONObject.parseObject(priceJsonStr).getJSONObject("data").getJSONObject("itemInfoModel").getJSONArray("priceUnits");
					if(priceUnitArray!=null && priceUnitArray.size()>0){
						for(int i=0;i<priceUnitArray.size();i++){
							JSONObject jo = priceUnitArray.getJSONObject(i);
							String name = jo.getString("name");
							if(StringUtils.equalsIgnoreCase(name, "价格")){
								String price = jo.getString("price");
								product.setPrice(price);
								break;
							}
						}
					}
					if(StringUtils.isNotBlank(product.getModel())){
						list.add(product);
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.error(e.getMessage(), rp.getDataId()+"not exist");
				}
			}
		}
		ExcelUtil.writeExcel(list, "product.xls");
	}

}
