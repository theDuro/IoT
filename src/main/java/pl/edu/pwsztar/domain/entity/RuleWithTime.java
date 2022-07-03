package pl.edu.pwsztar.domain.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.beans.ConstructorProperties;
import java.io.Serializable;
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
