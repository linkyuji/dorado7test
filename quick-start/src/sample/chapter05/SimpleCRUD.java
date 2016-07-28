package sample.chapter05;

import java.util.Collection;

import javax.annotation.Resource;

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

}