package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwsztar.domain.repository.RedisLogerDao;
import pl.edu.pwsztar.logic.LogCreator;

import java.util.List;
@Service
public class LogerService {
    private final RedisLogerDao redisLogerDao;
    private final LogCreator logCreator;
    private final String LOGGER_TXT_REFERENCE ="src/main/resources/logs.txt";



    public LogerService(RedisLogerDao redisLogerDao, LogCreator logCreator){
        this.redisLogerDao = redisLogerDao;
        this.logCreator = logCreator;
    }
    public void saveLog(String log){
        redisLogerDao.save(logCreator.crateLog(log,LOGGER_TXT_REFERENCE ));
    }
    public List<String> getAllLogs(){
        return redisLogerDao.findAll();
    }
    public void removeLogs() {
        redisLogerDao.removeLogList();

    }

}
