package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.User_;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_, Long>, CrudRepository<User_, Long> {


    Optional<User_> findByFirstName(String name);

    @Modifying
    @Query("SELECT COUNT(u) FROM User_ u" +
            " WHERE u.firstName" +
            " like :firstName")
    long checkIsLoginUniqe(@Param("firstName") String firstName);

    @Modifying
    @Query("UPDATE User_ u SET u.token = :token Where u.firstName  like :firstName"
            )
    void updateNewToken(@Param("firstName") String firstName,
                        @Param("token") String token
    );



    @Modifying
    @Query("SELECT  u.password FROM User_ u WHERE u.firstName LIKE :firstName")
    String getPasword(
            @Param("firstName") String firstName

    );



    @Modifying
    @Query("SELECT  u.firstName FROM User_ u WHERE u.userId = :iD")
    String getNickByID(
            @Param("iD") long iD

    );
    /*
    @Modifying
    @Query("SELECT COUNT(u.firstName) FROM User_ u WHERE u.firstName = :firstName ")
    int itNickIsFrea(
            @Param("firstName") String firstName
    );




     */

    @Modifying
    @Query("SELECT u.firstName FROM User_ u WHERE u.userId = 1 ")
    String itNickIsFrea(
            @Param("firstName") String firstName
    );

    @Modifying
    @Query("Select u.password  FROM User_ u WHERE u.firstName LIKE :firstName")
    Boolean getPasswordByFirstnane(
            @Param("firstName") String firstName

    );
    //todo do naprawy




   // SELECT * FROM tabela ORDER BY ID DESC LIMIT 1
//ToDO id -> Nick
   /* @Modifying
    @Query("SELECT  u.firstName FROM User_ u WHERE u.userId like :iD")
    String getUsserNameById(
            @Param("iD") Long iD

    );*/



}
