package Service;

import Model.Score;
import Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return (List<Score>) scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id) {
        return scoreRepository.getScore(id);
    }

    public Score save(Score score) {
        if (score.getId() == null) {
            return scoreRepository.save(score);
        } else {
            Optional<Score> costume1 = scoreRepository.getScore(score.getId());
            if (costume1.isEmpty()) {
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }

    public Score update(Score score) {
        if (score.getId() != null) {
            Optional<Score> score1 = scoreRepository.getScore(score.getId());
            if (!score1.isEmpty()) {
                if (score.getIdScore() != null) {
                    score1.get().setIdScore(score.getIdScore());
                }
                if (score.getReservation() != null) {
                    score1.get().setReservation(score.getReservation());
                }
                if (score.getMessageText() != null) {
                    score1.get().setMessageText(score.getMessageText());
                }
                return scoreRepository.save(score1.get());
            }
        }
        return score;

    }
    public boolean deleteScore(int id){
        Boolean resultado = getScore(id).map(score ->{
            scoreRepository.delete(score);
            return true;
        }).orElse(false);

        return resultado;
    }
}





