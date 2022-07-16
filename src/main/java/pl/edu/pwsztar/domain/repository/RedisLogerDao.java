package pl.edu.pwsztar.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.RuleWithTime;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class RedisLogerDao {
    public static final String LIST_KEY = "LogerInRedis";

    private  final RedisTemplate template;
    @Autowired
    RedisLogerDao(RedisTemplate template){
        this.template = template;
    }

    public void save(String logerr){
        template.opsForList().leftPush(LIST_KEY,logerr);

    }
    public List<String> findAll(){
        return template.opsForList().range(LIST_KEY,0,-1);
    }
}
