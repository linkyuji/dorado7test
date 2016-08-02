package sample.chapter08;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.sample.dao.ProductDao;
import com.bstek.dorado.sample.entity.Product;

@Component
public class ProductInterceptor {
    @Resource
    private ProductDao productDao;
 
    @DataResolver
    @Transactional
    public void saveAll(Collection<Product> products) {
    	for(Product product :  products){
            if(EntityState.DELETED.equals(EntityUtils.getState(product))){
                System.out.println("ɾ����Ʒ : " + product.getProductName());//ִ�в�Ʒɾ������
            }
            else if(EntityState.MODIFIED.equals(EntityUtils.getState(product))){
                System.out.println("�޸Ĳ�Ʒ : " + product.getProductName());//ִ�в�Ʒ�޸Ĳ���
                float oldValue = EntityUtils.getOldFloat(product, "unitPrice");
                System.out.println("�޸�ǰunitPrice=" + oldValue);
                System.out.println("�޸ĺ�unitPrice=" + product.getUnitPrice());
            }
            else if(EntityState.NEW .equals(EntityUtils.getState(product))){
                System.out.println("������Ʒ : " + product.getProductName());//ִ�в�Ʒ��������
            }
        }
        //productDao.persistEntities(products);
    }
}