package com.screens.product.dao.mapper;

import com.common.dao.BaseDAO;
import com.screens.product.dto.ProductDTO;
import com.screens.product.form.ResponseProductDetailForm;
import com.screens.product.form.ResponseProductListForm;
import com.screens.store.dto.StoreDTO;
import com.screens.store.form.ResponseStoreListForm;
import com.util.IDBHelper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductMapper extends BaseDAO {

    public ProductMapper(IDBHelper idbHelper) {
        super(idbHelper);
    }

    public ResponseProductDetailForm getProductDetail(ProductDTO productDTO) {
        return sqlSession.selectOne("com.screens.product.dao.sql.ProductDAO.getProductDetail",productDTO);
    }

    public ResponseProductListForm getProductList(ProductDTO productDTO) {
        return sqlSession.selectOne("com.screens.product.dao.sql.ProductDAO.getProductList",productDTO);
    }

    public boolean createProduct(ProductDTO productDTO) {
        if(sqlSession.insert("com.screens.product.dao.sql.ProductDAO.createProduct",productDTO) > 0){
            System.out.println("id =========== "+productDTO.getProductId());
//            this.sqlSession.commit();
            if(sqlSession.insert("com.screens.product.dao.sql.ProductDAO.productAddCategory",productDTO) > 0){
                this.sqlSession.commit();
                return true;
            }
        }
        return false;
    }

}