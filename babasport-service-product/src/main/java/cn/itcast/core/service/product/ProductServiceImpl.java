package cn.itcast.core.service.product;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.dao.product.ProductDao;
import cn.itcast.core.dao.product.SkuDao;
import cn.itcast.core.pojo.product.Product;
import cn.itcast.core.pojo.product.ProductQuery;
import cn.itcast.core.pojo.product.ProductQuery.Criteria;
import cn.itcast.core.pojo.product.Sku;
import redis.clients.jedis.Jedis;

/**
 * 商品管理
 * @author 
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductDao productDao;
	
	//通过上面的条件  及 当前页   查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow){
		//创建商品条件对象
		ProductQuery productQuery = new ProductQuery();
		
		Criteria createCriteria = productQuery.createCriteria();//Shift + Alt + L
		
		StringBuilder params = new StringBuilder();
		//判断
		if(null != name){
			createCriteria.andNameLike("%" + name +"%");
			params.append("name=").append(name);
		}
		if(null != brandId){
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		if(null != isShow){
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			//默认 下架
			createCriteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		//当前页
		productQuery.setPageNo(Pagination.cpn(pageNo));
		//每页数
		productQuery.setPageSize(5);
		//创建分页对象
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				productDao.countByExample(productQuery)
				);
		//结果集
		pagination.setList(productDao.selectByExample(productQuery));
		//分页在页面上展示
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		
		return pagination;
	}
	
	@Autowired
	private SkuDao skuDao;
	
	@Autowired
	private Jedis jedis;
	
	//保存商品信息
	public void insertProduct(Product product){
		//商品id 在redis中取到
		Long id = jedis.incr("pno");
		product.setId(id);
		
		//商品的字段没有在页面输入
		//下架
		product.setIsShow(false);
		//不删除
		product.setIsDel(true);
		//时间
		product.setCreateTime(new Date());
		//保存商品的同时返回商品ID
		productDao.insertSelective(product);
		
		//库存的数据
		for(String color : product.getColors().split(",")){
			for(String size : product.getSizes().split(",")){
				Sku sku = new Sku();
				//商品的id
				sku.setProductId(product.getId());
				//颜色的id
				sku.setColorId(Long.parseLong(color));
				//尺码
				sku.setSize(size);
				//市场价格
				sku.setMarketPrice(0f);
				//售价
				sku.setPrice(0f);
				//运费
				sku.setDeliveFee(10f);
				//库存
				sku.setStock(0);
				//购买的限制
				sku.setUpperLimit(200);
				//时间
				sku.setCreateTime(new Date());
				//保存库存到数据库
				skuDao.insertSelective(sku);
			}
		}
			
		
	}
	
	//商品的上架
	public void isShow(Long[] ids){
		Product product = new Product();
		//上架的状态
		product.setIsShow(true);
		
		for (Long id : ids) {
			product.setId(id);
			productDao.updateByPrimaryKeySelective(product);
			//TODO slore保存
			
		}
	}
	
	//商品的下架
	public void isHide(Long[] ids){
		Product product = new Product();
		//上架的状态
		product.setIsShow(false);
		
		for (Long id : ids) {
			product.setId(id);
			productDao.updateByPrimaryKeySelective(product);
			
		}
	}
	
}
