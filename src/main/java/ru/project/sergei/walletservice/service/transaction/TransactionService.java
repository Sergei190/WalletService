package ru.project.sergei.walletservice.service.transaction;

import ru.project.sergei.walletservice.domainobject.TransactionDO;
import ru.project.sergei.walletservice.exception.InsufficientBalanceException;
import ru.project.sergei.walletservice.exception.PlayerNotFoundException;
import ru.project.sergei.walletservice.exception.TransactionAlreadyExistsException;

import java.util.List;

public interface TransactionService {

    TransactionDO addTransaction(TransactionDO transactionDO) throws PlayerNotFoundException, TransactionAlreadyExistsException, InsufficientBalanceException;

    List<TransactionDO> getPlayerTransactions(Long playerId, Integer pageNo, Integer pageSize) throws PlayerNotFoundException;

}