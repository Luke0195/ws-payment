package br.com.contasapagar.repositories;

import br.com.contasapagar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    @Query(value = "SELECT obj FROM Payment as obj where LOWER(obj.name) = LOWER(:name)")
    Optional<Payment> findByName(String name);
}
