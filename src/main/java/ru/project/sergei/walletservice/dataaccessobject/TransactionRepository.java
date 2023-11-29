package ru.project.sergei.walletservice.dataaccessobject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.sergei.walletservice.domainobject.PlayerDO;
import ru.project.sergei.walletservice.domainobject.TransactionDO;

import java.util.Optional;

/**
 * Database Access Object for transaction table.
 *
 * @author ahmed.abdelmonem
 */
public interface TransactionRepository extends JpaRepository<TransactionDO, Long> {

    Page<TransactionDO> findByPlayer(PlayerDO playerDO, Pageable pageable);

    Optional<TransactionDO> findByNumber(String transactionNumber);

}