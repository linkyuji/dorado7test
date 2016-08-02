package sample.chapter10;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.sample.dao.CategoryDao;
import com.bstek.dorado.sample.dao.ProductDao;
import com.bstek.dorado.sample.entity.Category;
import com.bstek.dorado.sample.entity.Product;

@Component
public class DataTree {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    public ProductDao productDao;
    @DataProvider
    public Collection<Category> getAll(){
        return categoryDao.getAll();
    }
    @DataProvider
    public Collection<Product> getProductsByCategoryId(long categoryId){
        return productDao.find("from Product where category.id=" + categoryId);
    }
}