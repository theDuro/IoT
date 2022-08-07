package pl.edu.pwsztar.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
//do przerobienia
@Getter
@Setter
@AllArgsConstructor
public class ComandDto implements Serializable {

    private Long comandId;
    private Float ledLimitedValue;
    private Float ledFrequency ;
    private Float enginePower;
    private Long userId;
    private Integer expire;


    protected ComandDto(
             Long comandId
            ,Float ledLimitedValue
            ,Float enginePower
            ,Long userId
    ) {
        this.comandId =comandId;
        this.ledFrequency =ledLimitedValue;
        this.ledLimitedValue = ledLimitedValue;
        this.enginePower = enginePower;
        this.userId = userId;

    }

    public ComandDto() {

    }


    @Override
    public String toString() {
        return "ComandDto{" +
                "comandId=" + comandId +
                ", ledLimitedValue=" + ledLimitedValue +
                ", ledFrequency=" + ledFrequency +
                ", enginePower=" + enginePower +
                ", userId=" + userId +
                '}';
    }
}
