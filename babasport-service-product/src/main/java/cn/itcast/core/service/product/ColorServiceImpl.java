package cn.itcast.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.core.dao.product.ColorDao;
import cn.itcast.core.pojo.product.Color;
import cn.itcast.core.pojo.product.ColorQuery;

/**
 * 颜色查询
 * @author 潘金鑫
 *
 */
@Service("colorService")
@Transactional
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorDao colorDao;
	

	@Override
	public List<Color> selectColors() {
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.createCriteria().andParentIdNotEqualTo(0L);
		
		return colorDao.selectByExample(colorQuery);
	}
}
