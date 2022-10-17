package Repository.RepositoryCrudRepository;

import Model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {

    /*Reporte1
     *SELECT * FROM reservation WHERE startDate AFTER fechaA AND devolutionDate BEFORE fechaB;
     */

    public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date fechaA, Date fechaB);
    /*Reporte2
     * SELECT * FROM Reservation WHERE status = "valorQueNecesito";
     */
    public List<Reservation> findAllByStatus(String status);

    /*Reporte3
     * SELECT COUNT(client). client FROM Reservation GROUP BY client ORDER BY COUNT(client) DESC;
     *      [valor1.      valor2.] list<Object[]>
     *      [valor3.      valor4.]
     */
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC")
    public List<Object[]> getTotalReservationsByClient();

}