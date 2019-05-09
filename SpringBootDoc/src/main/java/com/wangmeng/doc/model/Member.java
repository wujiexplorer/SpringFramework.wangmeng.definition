package com.wangmeng.doc.model;

import com.wangmeng.doc.common.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor     
@AllArgsConstructor
@Entity
@Table(name = "md_members" )
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id", nullable = false)
	private Integer memberId;
	@Column(nullable = false)
    private String account;
	@Column(nullable = false)
    private String password;
	@Column(nullable = false)
    private String authMethod="local";
	@Column(nullable = true)
    private String description="";
	
	@Column(nullable = false)
    private String email;
	@Column(nullable = false)
    private String phone;
	@Column(nullable = true)
    private String avatar="/images/headimgurl.jpg";
	@Column(nullable = false)
    private Integer role=2;
	
	@Column(nullable = false)
    private Integer status=1;
	@Column(nullable = false)
    private Timestamp createTime= DateUtil.getTimestamp();
	@Column(nullable = false)
    private Integer createAt=0;
	@Column(nullable = true)
    private Timestamp lastLoginTime;
}
