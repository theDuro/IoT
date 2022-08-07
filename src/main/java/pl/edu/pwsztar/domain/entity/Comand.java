package pl.edu.pwsztar.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
////do przerobienia
@Entity
@AllArgsConstructor
@Setter
@Getter
@Table(name = "comands")
public class Comand implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comand_id")
    private long comandId;

    @Column(name = "led_limit_value")
    private Float ledLimitedValue;

    @Column(name = "led_frequency")
    private Float ledFrequency;

    @Column(name = "engine_power")
    private Float enginePower;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @Column(name ="expire")
    private Integer expire;

    public Comand() {
    }

    public Comand(Float ledLimitedValue, Float ledFrequency, Float enginePower, Long userId) {
        this.ledLimitedValue = ledLimitedValue;
        this.ledFrequency = ledFrequency;
        this.enginePower = enginePower;
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Comand{" +
                "comandId=" + comandId +
                ", ledLimitedValue=" + ledLimitedValue +
                ", ledFrequency=" + ledFrequency +
                ", enginePower=" + enginePower +
                ", userId=" + userId +
                '}';
    }
}
