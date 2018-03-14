package kz.kaznitu.srs4.my_srs.repositories;

import kz.kaznitu.srs4.my_srs.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
