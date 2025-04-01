package com.example.demo.member.model.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Getter
@Builder // 객체 생성시 체이닝 방식으로 생성할 수 있게 해줌
public class VoMember {

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private Date enrollDate;

}
