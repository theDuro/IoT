package pl.edu.pwsztar.domain.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.beans.ConstructorProperties;
import java.io.Serializable;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("RuleWithTime")
public class RuleWithTime implements Serializable {
    @Id
    private long id;
    private Float ledLimitedValue;
    private Float ledFrequency ;
    private Float enginePower;
    private Integer expireTime;



}
