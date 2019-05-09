package com.wangmeng.doc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor     
@AllArgsConstructor
@Entity
@Table(name = "md_options" )
public class Options {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_id", nullable = false)
	private Integer optionId;
	@Column(nullable = false)
    private String optionTitle;
	@Column(nullable = false)
    private String optionName;
	@Column(nullable = false)
    private String optionValue;
	@Column(nullable = false)
    private String remark;
}
