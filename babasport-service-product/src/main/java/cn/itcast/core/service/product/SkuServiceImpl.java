package cn.itcast.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.dao.product.ColorDao;
import cn.itcast.core.dao.product.SkuDao;
import cn.itcast.core.pojo.product.Sku;
import cn.itcast.core.pojo.product.SkuQuery;

/**
 * 库存管理
 * @author 潘金鑫
 *
 */
@Service(value = "skuService")
@Transactional
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	
	//查询库存列表
	public List<Sku> selectSkuListByProduct(Long productId){
		//查询sku结果集 通过商品ID
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}
	
	//修改库存
	public void updateSkuById(Sku sku){
		skuDao.updateByPrimaryKeySelective(sku);
	}
	
	
}
