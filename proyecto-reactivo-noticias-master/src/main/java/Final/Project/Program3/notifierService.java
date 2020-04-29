package Final.Project.Program3;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/notifier")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class notifierService {

	@Autowired
	notifierRepository repository;
	
	@Autowired
	newsRepository nrepository;


	@GetMapping
	public List<notifier> findAll(){
		return repository.findAll();
	}
	
	@PostMapping(path= {"/save"})
	public notifier add(@RequestBody notifier newnoti) {
		
		List<news> newss = newnoti.getNews();
		newnoti.setNews(null);	

		notifier noti = repository.save(newnoti);
		
		for(news n: newss) {
			n.setIdnotifierid(noti.getIdnotifier());
			nrepository.save(n);
		}

		noti.setNews(newss);
		
		return noti;
	}
	
	@GetMapping(path= {"/{id}"})
	public notifier list(@PathVariable("id") int idnotifier) {
		return repository.findById(idnotifier).get();
	}
	
	@PutMapping(path = {"/{id}"})
	public notifier edit(@RequestBody notifier newnoti,@PathVariable("id") int idnotifier) {
		newnoti.setIdnotifier(idnotifier);
		return repository.save(newnoti);
	}
	
	@DeleteMapping(path= {"/{id}"})
	public notifier delet(@PathVariable("id") int idnotifier) {
		notifier n = repository.findById(idnotifier).get();
		if(n != null) {
			repository.delete(n);
		}
		return n;
	}
	
	 
	
}


