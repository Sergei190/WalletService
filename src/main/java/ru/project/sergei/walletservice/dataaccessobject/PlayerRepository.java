package ru.project.sergei.walletservice.dataaccessobject;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.sergei.walletservice.domainobject.PlayerDO;

/**
 * Database Access Object for player table.
 *
 * @author ahmed.abdelmonem
 */
public interface PlayerRepository extends JpaRepository<PlayerDO, Long> {

}