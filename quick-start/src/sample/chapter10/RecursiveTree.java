package sample.chapter10;



import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.sample.dao.ExampleCategoryDao;
import com.bstek.dorado.sample.entity.ExampleCategory;

@Component
public class RecursiveTree {
    @Resource
    private ExampleCategoryDao exampleCategoryDao;
    
    @DataProvider
    public Collection<ExampleCategory> getCategories() {
        // ·µ»Ø¶¥²ãÊý¾Ý
        return exampleCategoryDao.find("from ExampleCategory where categoryId is null");
    }
    @DataProvider
    public Collection<ExampleCategory> getChildCategories(Long parentCategoryId) {
        return exampleCategoryDao.find("from ExampleCategory where categoryId=" + parentCategoryId);
    }
}