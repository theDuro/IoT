package pl.edu.pwsztar.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pwsztar.domain.entity.Comand;

import java.util.List;

@Repository
public interface ComandRepository extends JpaRepository<Comand, Long>, CrudRepository<Comand, Long> {
    //@Modifying
    @Query( "SELECT  c FROM Comand c  WHERE  c.comandId = :iD")
    Comand getComandToIoT(@Param("iD") long iD

    );
}
