package pl.edu.pwsztar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
public class CreateComandDto implements Serializable {
    private Long comandId;
    private Float ledLimitedValue;
    private Float ledFrequency ;
    private Float enginePower;
    private Long userId;
    private Integer expire;

    public CreateComandDto() {
    }
    public CreateComandDto( float ledLimitedValue,float ledFrequency,float enginePower,int expire,long userId) {
        this.expire = expire;
        this.ledLimitedValue = ledLimitedValue;
        this.ledFrequency = ledFrequency;
        this.enginePower = enginePower;
        this.userId= userId;
    }
    @Override
    public String toString() {
        return "CreateCmandDto{" +
                ",ledLimitedValue='" + ledLimitedValue + '\'' +
                ", ledFrequency='" + ledFrequency + '\'' +
                ", enginePower=" + enginePower +
                ", userId=" + userId +
                '}';
    }

}
