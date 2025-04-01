package com.example.demo.productInfo.model.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.productInfo.model.dto.ProInfoDTO;

@Mapper
public interface ProInfoMapper {

	@Select("SELECT PRODUCT_ID productId,PRODUCT_NAME productName,PRODUCT_TYPE productType,PRODUCT_CATEGORY productCategory,PRODUCT_PRICE productPrice,PRODUCT_IMAGE productImage FROM PRODUCT_INFO WHERE PRODUCT_TYPE = #{productType} AND PRODUCT_CATEGORY = #{productCategory}")
	List<ProInfoDTO> selectProduct(ProInfoDTO proInfoDTO);
	
}
