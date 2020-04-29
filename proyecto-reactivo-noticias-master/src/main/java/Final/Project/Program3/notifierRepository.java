package Final.Project.Program3;


import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface notifierRepository extends JpaRepository<notifier,Serializable> {

	List<notifier> findByNotifiername(String notifiername);
}
