package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Builder.Default
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate=new Date();
	@Builder.Default
	private Long cnt=0L;
	
	public void update(Board board) {
		if (board.getTitle()!=null) title = board.getTitle();
		if (board.getWriter()!=null) title = board.getWriter();
		if (board.getContent()!=null) title = board.getContent();
	}
}
