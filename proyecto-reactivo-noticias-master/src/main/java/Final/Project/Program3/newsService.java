package Final.Project.Program3;


import java.time.Duration;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;




@RestController
@RequestMapping("/news")
@CrossOrigin(origins = "http://localhost:4200", maxAge=3600)
public class newsService {

	@Autowired
	newsRepository Repositoryn;
	

	@GetMapping
	public List<news> findAll(){
		return Repositoryn.findAll();
	}
	
	@GetMapping(path = {"/idnotifierid/{idnotifierid}"})
	public List<news> newsnotitier(@PathVariable("idnotifierid") int idnotifierid){
		return Repositoryn.findByIdnotifierid(idnotifierid);
	}
	
	@GetMapping(path = {"/{id}"})
	public news getbyid(@PathVariable("id") int idnews) {
		return Repositoryn.findById(idnews).get();
	}
	
	
	@PostMapping
	public news save(@RequestBody news newnews) {
		
		notificationProcessor.onNext(newnews);
		return Repositoryn.save(newnews);
	}
	
	
	
	
	@PutMapping(path = {"/{id}"})
	public news edit(@RequestBody news ne, @PathVariable("id") int idnews) {
		ne.setIdnews(idnews);
		return Repositoryn.save(ne);
	}
	@DeleteMapping(path = {"/{id}"})
	public news delete(@PathVariable("id")int idnews) {
		news n = Repositoryn.findById(idnews).get();
		if(n != null) {
			Repositoryn.delete(n);
		}
		return n;
		}
	
	 
    private EmitterProcessor<news> notificationProcessor;

  
    @PostConstruct
    private void createProcessor() {
        notificationProcessor = EmitterProcessor.<news>create();
    }
	
	
    private Flux<ServerSentEvent<news>> getNewsSSE() {


        return notificationProcessor
                .log().map(
                        (newz) -> {
                            System.out.println("Sending News:" + newz.getIdnews());
                            return ServerSentEvent.<news>builder()
                                    .id(UUID.randomUUID().toString())
                                    .event("news-result")
                                    .data(newz) 
                                    .build();
                        }).concatWith(Flux.never());
    }


    private Flux<ServerSentEvent<news>> getNotificationHeartbeat() {
        return Flux.interval(Duration.ofSeconds(15))
                .map(i -> {
                    System.out.println(String.format("HeartBeat [%s] ...", i.toString()));
                                      return ServerSentEvent.<news>builder()
                            .id(String.valueOf(i))
                            .event("HeartBeat-Result")
                            .data(null)
                            .build();
                });
    }


    @GetMapping(
            value = "/notification/sse"
    )
    public Flux<ServerSentEvent<news>>
            getJobResultNotification() {

        return Flux.merge(
                getNotificationHeartbeat(),
                getNewsSSE()
        );

    }



}


