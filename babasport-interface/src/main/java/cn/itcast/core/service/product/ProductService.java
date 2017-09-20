package cn.itcast.core.service.product;

import cn.itcast.common.page.Pagination;

public interface ProductService {
	
	//通过上面的条件  及 当前页   查询分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow);

}
