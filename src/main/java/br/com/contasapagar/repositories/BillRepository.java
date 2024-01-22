package br.com.contasapagar.repositories;

import br.com.contasapagar.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {

    @Query(value="SELECT bill from Bill as bill where lower(bill.description)  like %:description%")
    List<Bill> findAllByDescription(@Param("description") String description);
}
