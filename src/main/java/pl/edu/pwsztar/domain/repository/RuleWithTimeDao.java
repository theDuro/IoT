package pl.edu.pwsztar.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.config.RedisConfig;
import pl.edu.pwsztar.domain.entity.RuleWithTime;

import java.util.concurrent.TimeUnit;

@Repository
public class RuleWithTimeDao {
    public static final String HASH_KEY = "RuleWithTime";
    @Autowired
    private RedisTemplate template;
public RuleWithTime save(RuleWithTime ruleWithTime){
    template.opsForHash().put(HASH_KEY,ruleWithTime.getId(),ruleWithTime);
    return  ruleWithTime;
}
    public RuleWithTime findRuleById(long id){
    return (RuleWithTime) template.opsForHash().get(HASH_KEY,id);
    }
    public void deleteRule(long id){
        template.opsForHash().delete(HASH_KEY,id);
    }





}
