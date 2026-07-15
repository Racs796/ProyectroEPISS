package episs.unaj.com.SRCAG.Repository;

import episs.unaj.com.SRCAG.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

}