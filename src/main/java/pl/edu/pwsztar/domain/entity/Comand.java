package pl.edu.pwsztar.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
////do przerobienia
@Entity
@Table(name = "comands")
public class Comand implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comand_id")
    private Long comandId;

    @Column(name = "led_limit_value")
    private Float ledLimitedValue;

    @Column(name = "led_frequency")
    private Float ledFrequency;

    @Column(name = "engine_power")
    private Float enginePower;

    @Column(name = "user_id")
    private Long userId;

    public Comand() {
    }

    public Comand(Float ledLimitedValue, Float ledFrequency, Float enginePower, Long userId) {
        this.ledLimitedValue = ledLimitedValue;
        this.ledFrequency = ledFrequency;
        this.enginePower = enginePower;
        this.userId = userId;
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
