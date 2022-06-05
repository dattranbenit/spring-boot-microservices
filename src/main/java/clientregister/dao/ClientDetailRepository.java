package clientregister.dao;

import clientregister.entity.ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDetailRepository extends JpaRepository<ClientDetail, String> {
}
