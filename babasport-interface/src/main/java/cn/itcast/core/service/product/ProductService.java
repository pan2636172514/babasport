package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.pojo.product.Product;

public interface ProductService {
	
	//通过上面的条件  及 当前页   查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow);

	//保存商品
	public void insertProduct(Product product);

}
