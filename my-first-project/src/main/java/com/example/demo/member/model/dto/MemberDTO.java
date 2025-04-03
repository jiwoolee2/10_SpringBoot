package com.example.demo.member.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MemberDTO {
	
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어,숫자만 입력해주세요.")
	@Size( min = 4, max = 15, message = "아이디는 최소 4글자 이상, 15글자 이하로 작성해주세요.")
	@NotBlank(message = "아이디를 입력해주세요")
	private String memberId;
	
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어,숫자만 입력해주세요.")
	@Size( min = 4, max = 15, message = "비밀번호는 최소 4글자 이상, 15글자 이하로 작성해주세요.")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String memberPw;
		
	@Pattern(regexp = "^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]*$", message = "영어,한글만 입력해주세요.")
	@NotBlank(message = "이름을 입력해주세요.")
	private String memberName;
	
	@Pattern(regexp = "^[0-9]*$", message = "숫자만 입력해주세요.")
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String memberPhone;
	private String role;
	private Date enrollDate;
	

}
