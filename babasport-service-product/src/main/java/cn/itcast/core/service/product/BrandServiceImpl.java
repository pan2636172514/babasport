package cn.itcast.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.dao.product.BrandDao;
import cn.itcast.core.pojo.product.Brand;
import cn.itcast.core.pojo.product.BrandQuery;

/**
 * 品牌管量
 * @author lx
 *
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;
//	名称及是否可见 查询品牌结果集
	public List<Brand> selectBrandListByQuery(String name,Integer isDisplay){
		BrandQuery brandQuery = new BrandQuery();
		if(null != name){
			brandQuery.setName(name);
		}
		//是否可见
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
		}else{
			//默认的值 是
			brandQuery.setIsDisplay(1);
		}
		return brandDao.selectBrandListByQuery(brandQuery);
		
	}
	//通过品牌名称及是否可见 当前页 查询分页对象
	public  Pagination selectPaginationByQuery(Integer pageNo,String name,Integer isDisplay){
		BrandQuery brandQuery = new BrandQuery();
		
		StringBuilder params = new StringBuilder();
		
		if(null != name){
			brandQuery.setName(name);
			params.append("name=").append(name);
		}
		//是否可见
		if(null != isDisplay){
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay=").append(isDisplay);
		}else{
			//默认的值 是
			brandQuery.setIsDisplay(1);
			params.append("&isDisplay=").append(1);
		}
		//每页数
		brandQuery.setPageSize(3);
		//当前页
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		
		//创建分页对象
		Pagination pagination = new Pagination(
				brandQuery.getPageNo(),
				brandQuery.getPageSize(),
				brandDao.countBrandByQuery(brandQuery)
				);
		
		//结果集 
		pagination.setList(brandDao.selectBrandListByQuery(brandQuery));
		
		//上面分页数据完整     /brand/list.do?name=依&isDisplay=1
		//分页在页面上展示
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		
		return pagination;
	}
	//通过品牌ID查询品牌对象
	public Brand selectBrandById(Long id){
		return brandDao.selectBrandById(id);
	}
	@Override
	public void updateBrandById(Brand brand) {
		// TODO Auto-generated method stub
		brandDao.updateBrandById(brand);
	}
	
	//批量删除
	public void deletes(Long[] ids){
		brandDao.deletes(ids);
	}
}
