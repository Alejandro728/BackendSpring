package Final.Project.Program3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

@Entity
@Table(name = "news")
@ComponentScan(basePackages = {"news,notifier"})
public class news {

	@Id
	@Column(name = "idnews")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idnews;
	@Column(name = "news_text")
	private String newstext;
	@Column(name = "id_notifier")
	private int idnotifierid;
	
	public int getIdnews() {
		return idnews;
	}
	public void setIdnews(int idnews) {
		this.idnews = idnews;
	}
	public String getNewstext() {
		return newstext;
	}
	public void setNewstext(String newstext) {
		this.newstext = newstext;
	}
	public int getIdnotifierid() {
		return idnotifierid;
	}
	public void setIdnotifierid(int idnotifierid) {
		this.idnotifierid = idnotifierid;
	}


	
	
}