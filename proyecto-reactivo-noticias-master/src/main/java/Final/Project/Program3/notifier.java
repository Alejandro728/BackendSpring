package Final.Project.Program3;

import javax.persistence.Column;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notifier")
public class notifier {

	@Id
	@Column(name = "idnotifier")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idnotifier;
	@Column(name = "notifier_name")
	private String notifiername;
	@Column(name = "notifier_surname")
	private String notifiersurname;
	@Column(name = "notifier_password")
	private String notifierpassword;
	@Column(name = "notifier_country")
	private String notifiercountry;
	@Column(name = "notifier_age")
	private int notifierage;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="idnotifierid")
	private List<news> news;
	
	
	public List<news> getNews() {
		return news;
	}
	public void setNews(List<news> news) {
		this.news = news;
	}
	public int getIdnotifier() {
		return idnotifier;
	}
	public void setIdnotifier(int idnotifier) {
		this.idnotifier = idnotifier;
	}
	public String getNotifiername() {
		return notifiername;
	}
	public void setNotifiername(String notifiername) {
		this.notifiername = notifiername;
	}
	public String getNotifiersurname() {
		return notifiersurname;
	}
	public void setNotifiersurname(String notifiersurname) {
		this.notifiersurname = notifiersurname;
	}
	public String getNotifierpassword() {
		return notifierpassword;
	}
	public void setNotifierpassword(String notifierpassword) {
		this.notifierpassword = notifierpassword;
	}
	public String getNotifiercountry() {
		return notifiercountry;
	}
	public void setNotifiercountry(String notifiercountry) {
		this.notifiercountry = notifiercountry;
	}
	public int getNotifierage() {
		return notifierage;
	}
	public void setNotifierage(int notifierage) {
		this.notifierage = notifierage;
	}
	
	
}