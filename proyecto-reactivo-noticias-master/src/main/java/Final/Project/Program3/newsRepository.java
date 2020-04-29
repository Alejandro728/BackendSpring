package Final.Project.Program3;

import java.io.Serializable;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
public interface newsRepository extends JpaRepository<news,Serializable>  {

	List<news> findByIdnotifierid(int idnotifierid);
}
