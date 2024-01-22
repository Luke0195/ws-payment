package br.com.contasapagar.repositories;

import br.com.contasapagar.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByEmail(String email);
    Optional<Client> findByCode(String code);

    @Query(value="SELECT obj FROM Client as obj WHERE lower(obj.name) like  %:name%")
    List<Client> searchClientsByName(@Param("name")String name);
}
