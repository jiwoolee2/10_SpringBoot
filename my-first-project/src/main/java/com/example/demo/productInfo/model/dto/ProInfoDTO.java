package com.example.demo.productInfo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProInfoDTO {

	private String productId;
	private String productName;
	private String productType;
	private String productCategory;
	private int productPrice;
	private String productImage;
}
