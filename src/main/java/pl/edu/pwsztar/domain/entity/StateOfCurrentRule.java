package pl.edu.pwsztar.domain.entity;

import lombok.*;

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
    private Integer expireTime;

}
