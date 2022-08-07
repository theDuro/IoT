package pl.edu.pwsztar.domain.mapper;

public enum TimeForExpire {
    BASE_EXPIRE_VALUE(60);

    private Integer expireTime;
    TimeForExpire(Integer expireTime){
        this.expireTime = expireTime;
    }

}
