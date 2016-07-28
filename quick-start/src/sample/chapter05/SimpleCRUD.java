package sample.chapter05;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.sample.dao.ProductDao;
import com.bstek.dorado.sample.entity.Product;

@Component
public class SimpleCRUD {
    @Resource
    private ProductDao productDao;
     
    @DataProvider
    public void getAll(Page<Product> page){
        //page中包含：
        //每页加载的数据是多少条
        //page.getPageSize();
        //需要加载的是第几页
        //page.getPageNo();
         
        //Dao层的分页查询方法
        productDao.getAll(page);
    }
    
    @DataResolver
    @Transactional
    public void saveAll(Collection<Product> products){
        productDao.persistEntities(products);
    }
    
    @DataProvider
    public void query1(Page<Product> page, String productName) {
        if (null != productName)
            productDao.find(page, "from Product where productName like '%"
                    + productName + "%'");
        else
            productDao.getAll(page);
    }
    
    @DataProvider
    public void query2(Page<Product> page, Map<String, Object> params) {
        if (null != params) {
            String productName = (String) params.get("productName");
            String unitsInStock = (String) params.get("unitsInStock");

            String whereCase = "";
            if (null != productName) {
                whereCase += " productName like '%" + productName + "%' ";
            }
            if (null != unitsInStock) {
                if (StringUtils.isNotEmpty(whereCase))
                    whereCase += " AND ";
 
                whereCase += " unitsInStock >" + unitsInStock;
            }
            String queryStr = (StringUtils.isEmpty(whereCase)) ? "" : " where "+ whereCase;
            productDao.find(page, " from Product " + queryStr);
        } else
            productDao.getAll(page);
    }

}