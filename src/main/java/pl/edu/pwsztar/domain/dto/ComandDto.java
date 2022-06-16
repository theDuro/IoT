package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;
//do przerobienia
public class ComandDto implements Serializable {

    private Long comandId;
    private Float ledLimitedValue;
    private Float ledFrequency ;
    private Float enginePower;
    private Long userId;


    public ComandDto() {
    }

    public Long getComandId() {
        return comandId;
    }

    public void setComandId(Long comandId) {
        this.comandId = comandId;
    }

    public Float getLedLimitedValue() {
        return ledLimitedValue;
    }

    public void setLedLimitedValue(Float ledLimitedValue) {
        this.ledLimitedValue = ledLimitedValue;
    }

    public Float getLedFrequency() {
        return ledFrequency;
    }

    public void setLedFrequency(Float ledFrequency) {
        this.ledFrequency = ledFrequency;
    }

    public Float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
