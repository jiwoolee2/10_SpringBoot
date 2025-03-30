package com.example.demo.productInfo.model.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.productInfo.model.dto.ProInfoDTO;

@Mapper
public interface ProInfoMapper {

	@Select("SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_TYPE,PRODUCT_CATEGORY,PRODUCT_PRICE,PRODUCT_IMAGE FROM PRODUCT_INFO WHERE PRODUCT_TYPE=#{productType} AND PRODUCT_CATEGORY=#{productCategory}")
	List<ProInfoDTO> selectProduct(ProInfoDTO proInfoDTO);
	

}
