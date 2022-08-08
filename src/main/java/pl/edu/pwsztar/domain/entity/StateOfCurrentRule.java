package pl.edu.pwsztar.domain.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StateOfCurrentRule implements Serializable {
    private Long userId;
    private Float ledLimitedValue;
    private Float ledFrequency ;
    private Float enginePower;
    private Long commandid;
    private Integer expireTime;

}
