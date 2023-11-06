package spo.ifsp.edu.br.projeto_lp2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spo.ifsp.edu.br.projeto_lp2.models.Region;
import spo.ifsp.edu.br.projeto_lp2.models.User;
import spo.ifsp.edu.br.projeto_lp2.models.UserType;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByTypeIn(Pageable pageable, List<UserType> types);

    Page<User> findAllByLocationRegionIn(Pageable pageable, List<Region> regions);

    Page<User> findAllByTypeInAndLocationRegionIn(Pageable pageable, List<UserType> types, List<Region> regions);
}
