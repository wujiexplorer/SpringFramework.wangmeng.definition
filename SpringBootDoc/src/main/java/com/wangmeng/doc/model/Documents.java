package com.wangmeng.doc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor     
@AllArgsConstructor
@Entity
@Table(name = "md_documents" )
public class Documents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "document_id", nullable = false)
	private Integer documentId;
	@Column(nullable = false)
    private String documentName;
	@Column(nullable = false)
    private String identify;
	@Column(nullable = false)
    private Integer bookId;
	@Column(nullable = true)
    private Integer parentId;
	
	@Column(nullable = false)
    private Integer orderSort;
	@Column(nullable = false)
    private String markdown;
	@Column(nullable = true)
    private String release;
	@Column(nullable = false)
    private String content;
	
	@Column(nullable = false)
    private Timestamp createTime;
	@Column(nullable = false)
    private Integer memberId;
	@Column(nullable = false)
    private Timestamp modifyTime;
	@Column(nullable = true)
    private Integer modify_at;
	@Column(nullable = true)
    private Integer version;
	
	
}
