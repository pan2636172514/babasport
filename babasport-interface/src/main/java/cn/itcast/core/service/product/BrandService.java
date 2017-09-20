package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.pojo.product.Brand;

public interface BrandService {
	
	//	名称及是否可见 查询品牌结果集
	public List<Brand> selectBrandListByQuery(String name,Integer isDisplay);
	
	//通过品牌名称及是否可见 当前页 查询分页对象
	public  Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay);
	
	//通过品牌ID查询品牌对象
	public Brand selectBrandById(Long id);
	
	//修改
	public void updateBrandById(Brand brand);
	
	
	//批量删除
	public void deletes(Long[] ids);

}
