package ru.project.sergei.walletservice.service.transaction;

import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.sergei.walletservice.dataaccessobject.TransactionRepository;
import ru.project.sergei.walletservice.domainobject.PlayerDO;
import ru.project.sergei.walletservice.domainobject.TransactionDO;
import ru.project.sergei.walletservice.domainvalue.TransactionType;
import ru.project.sergei.walletservice.exception.InsufficientBalanceException;
import ru.project.sergei.walletservice.exception.PlayerNotFoundException;
import ru.project.sergei.walletservice.exception.TransactionAlreadyExistsException;
import ru.project.sergei.walletservice.service.player.PlayerService;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for transaction.
 * <p>
 *
 * @author ahmed.abdelmonem
 *
 */
@Service
public class DefaultTransactionService implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerService playerService;

    @Override
    @Transactional
    public TransactionDO addTransaction(TransactionDO transactionDO) throws PlayerNotFoundException,
            TransactionAlreadyExistsException, InsufficientBalanceException {

        logger.debug("Called DefaultTransactionService.addTransaction");

        // check unique transaction
        if (isPresent(transactionDO.getNumber())) {
            logger.error("Transaction with number {} already exists", transactionDO.getNumber());
            throw new TransactionAlreadyExistsException();
        }

        PlayerDO playerDO = transactionDO.getPlayer();

        if (transactionDO.getType().equals(TransactionType.DEBIT)) {

            // check sufficient funds
            if (playerDO.getBalance().subtract(transactionDO.getAmount()).compareTo(new BigDecimal(0)) < 0) {
                logger.error("Insufficient funds on the wallet of player with id: {}", playerDO.getId());
                throw new InsufficientBalanceException();
            }

            playerDO.setBalance(playerDO.getBalance().subtract(transactionDO.getAmount()));

        } else {
            // credit
            playerDO.setBalance(playerDO.getBalance().add(transactionDO.getAmount()));
        }

        // insert transaction
        transactionRepository.save(transactionDO);

        return transactionDO;
    }

    @Override
    public List<TransactionDO> getPlayerTransactions(Long playerId, Integer pageNo, Integer pageSize)
            throws PlayerNotFoundException {

        logger.debug("Called DefaultTransactionService.getPlayerTransactions with palyerId :{}", playerId);

        // create paging object based on pageNo, pageSize and sort by creation date
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("dateCreated").descending());

        return transactionRepository.findByPlayer(playerService.findPlayer(playerId), paging).getContent();
    }

    private boolean isPresent(String transactionNumber) {

        logger.debug("Called DefaultTransactionService.isPresent with transactionNumber :{}", transactionNumber);

        return transactionRepository.findByNumber(transactionNumber).isPresent();
    }

}