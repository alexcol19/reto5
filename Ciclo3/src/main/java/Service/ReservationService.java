package Service;

import Model.DTO.CompletedAndCancelled;
import Model.DTO.TotalAndClient;
import Model.Reservation;
import Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
  @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationRepository.getAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> reservation1 = reservationRepository.getReservation(reservation.getIdReservation());
            if(reservation1.isEmpty()){
                return reservationRepository.save(reservation);
            }else {
                return reservation;
            }
        }
    }
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() != null){
            Optional<Reservation> reservation1 = reservationRepository.getReservation(reservation.getIdReservation());
            if(!reservation1.isEmpty()){
                if(reservation.getIdReservation() != null){
                    reservation1.get().setIdReservation(reservation.getIdReservation());
                }
                if(reservation.getClient() != null){
                    reservation1.get().setClient(reservation.getClient());
                }
                if(reservation.getCostume() != null){
                    reservation1.get().setCostume(reservation.getCostume());
                }
                if(reservation.getDevolutionDate() != null){
                    reservation1.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getScore() != null){
                    reservation1.get().setScore(reservation.getScore());
                }
                if(reservation.getStartDate() != null){
                    reservation1.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getStatus() != null){
                    reservation1.get().setStatus(reservation.getStatus());
                }
                if (reservation.getEndDate() != null){
                    reservation1.get().setEndDate(reservation.getEndDate());
                }
                return reservationRepository.save(reservation1.get());
            }
        }
        return reservation;

    }
    public boolean deleteReservation(int id){
        Boolean resultado = getReservation(id).map(reservation ->{
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);

        return resultado;
    }

    //Reto5

    public List<Reservation> getReservationsBetweenDatesReport(String fechaA, String fechaB){
        SimpleDateFormat parser = new SimpleDateFormat("yyy -MM-dd");
        Date a = new Date();
        Date b = new Date();
        try{
            a = parser.parse(fechaA);
            b = parser.parse(fechaB);
        }catch (ParseException exception){
            exception.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationsBetweenDates(a, b);
        }else {
            return new ArrayList<>();
        }

    }
    public CompletedAndCancelled getReservationStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationsByStatus("cancelled");

        int cantidadCompletas = completed.size();
        int cantidadCanceladas = cancelled.size();

        return new CompletedAndCancelled( (long) cantidadCompletas, (long) cantidadCanceladas);

    }
    public List<TotalAndClient> getTopClientsReport(){
        return reservationRepository.getTopClients();
    }
}

