package cn.itcast.core.service.product;

import java.util.List;

import cn.itcast.core.pojo.product.Sku;

public interface SkuService {

	//查询库存列表
	public List<Sku> selectSkuListByProduct(Long productId);
	
	//修改库存
	public void updateSkuById(Sku sku);
}
