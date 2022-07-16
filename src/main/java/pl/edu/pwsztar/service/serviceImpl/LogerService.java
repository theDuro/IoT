package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pwsztar.domain.repository.RedisLogerDao;
import pl.edu.pwsztar.logic.LogCreator;

import java.util.List;

public class LogerService {
    private final RedisLogerDao redisLogerDao;
    private final LogCreator logCreator;


    @Autowired
    public LogerService(RedisLogerDao redisLogerDao, LogCreator logCreator){
        this.redisLogerDao = redisLogerDao;
        this.logCreator = logCreator;
    }
    public void saveLog(String log){
        redisLogerDao.save(logCreator.crateLog(log));
    }
    public List<String> getAllLogs(){
        return redisLogerDao.findAll();
    }

}
