package pl.edu.pwsztar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;

@RestController
public class IotControler {
    private final  RedisTemplate template;

    @Autowired
    public  IotControler(RedisTemplate template){
        this.template = template;
    }
    @CrossOrigin
    @GetMapping(value = "/Test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> getComandForIot() {
        Object o = template.opsForValue().get( "ValueOfActualIot");
        System.out.print(o.getClass()+" Działa /n \n \n");

        return new ResponseEntity<>(o, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping(value = "/T", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getComand(@RequestHeader  String token) {


        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
