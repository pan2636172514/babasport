package cn.itcast.core.dao.product;

import java.util.List;

import cn.itcast.core.pojo.product.Brand;
import cn.itcast.core.pojo.product.BrandQuery;

public interface BrandDao {
	
	
	//查询品牌结果集  根据条件  
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);
	
//	总条数（根据条件）
	public Integer countBrandByQuery(BrandQuery brandQuery);
	
	//通过品牌ID查询品牌对象
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrandById(Brand brand);
	
	//批量删除
	public void deletes(Long[] ids);

}
