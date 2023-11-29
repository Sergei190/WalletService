package ru.project.sergei.walletservice.controller.mapper;

import ru.project.sergei.walletservice.datatransferobject.TransactionDTO;
import ru.project.sergei.walletservice.domainobject.PlayerDO;
import ru.project.sergei.walletservice.domainobject.TransactionDO;
import ru.project.sergei.walletservice.domainvalue.TransactionType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ahmed.abdelmonem
 *
 */
public class TransactionMapper {

    /**
     * private constructor to hide the implicit public one
     */
    private TransactionMapper() {
    }

    public static TransactionDO makeTransactionDO(PlayerDO player, String transactionNumber, TransactionType type,
                                                  BigDecimal amount) {
        return new TransactionDO(transactionNumber, amount, type, player);
    }

    public static TransactionDTO makeTransactionDTO(TransactionDO transactionDO) {

        return new TransactionDTO(transactionDO.getNumber(), transactionDO.getPlayer().getId(),
                transactionDO.getAmount(), transactionDO.getType(), transactionDO.getNewBalance(), transactionDO.getDateCreated());
    }

    public static List<TransactionDTO> makeTransactionDTOList(Collection<TransactionDO> transactions) {

        return transactions.stream()
                .map(TransactionMapper::makeTransactionDTO)
                .collect(Collectors.toList());
    }

}