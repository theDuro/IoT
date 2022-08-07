package pl.edu.pwsztar.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.dto.ComandDto;
import pl.edu.pwsztar.domain.entity.StateOfCurrentRule;
import pl.edu.pwsztar.domain.mapper.ComandDtoToStateOfCurrentRule;
import pl.edu.pwsztar.domain.repository.RuleWithTimeVairebleDao;

@Repository
public class RedisComandSerwiceImpl implements RedisComandService{
    private final RuleWithTimeVairebleDao ruleWithTimeVairebleDao;
    private final ComandDtoToStateOfCurrentRule mapComandDtoToStateOfCurrentRule;

    @Autowired
    public RedisComandSerwiceImpl( RuleWithTimeVairebleDao ruleWithTimeVairebleDao
            , ComandDtoToStateOfCurrentRule mapComandDtoToStateOfCurrentRule
    ){
        this.ruleWithTimeVairebleDao = ruleWithTimeVairebleDao;
        this.mapComandDtoToStateOfCurrentRule = mapComandDtoToStateOfCurrentRule;
    }

    @Override
    public Long getExpireTime() {
        return ruleWithTimeVairebleDao.getTimeExpire();
    }

    @Override
    public StateOfCurrentRule getCurentRoleWithExpireTime() {
       return ruleWithTimeVairebleDao.getActualRule();
    }

    @Override
    public void activateIotTimer(ComandDto comandDto) {
        ruleWithTimeVairebleDao.activateIoTVariable(comandDto);
    }


}


