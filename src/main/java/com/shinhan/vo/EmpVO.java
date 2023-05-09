package com.shinhan.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JavaBeans기술 : 변수 접근지정자는 private, setter/getter, 기본생성자
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class EmpVO {
	 private Integer employee_id;
	 private String first_name;
	 private String last_name;
	 private String email;
	 private String phone_number;
	 private Date hire_date;
	 private String job_id;
	 private Double salary;	
	 //Wrapper class로 변경한 이유 : jsp 페이지에서 값 입력안함 null => 기본형넣으면 오류
 	 private Double commission_pct;
	 private Integer manager_id;
	 private Integer department_id;
}
