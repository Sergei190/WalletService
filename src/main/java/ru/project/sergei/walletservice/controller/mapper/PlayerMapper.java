package ru.project.sergei.walletservice.controller.mapper;

import ru.project.sergei.walletservice.datatransferobject.BalanceDTO;

import java.math.BigDecimal;

/**
 *
 * @author ahmed.abdelmonem
 *
 */
public class PlayerMapper {

    /**
     * private constructor to hide the implicit public one
     */
    private PlayerMapper() {
    }

    public static BalanceDTO makeBalanceDTO(Long playerId, BigDecimal balance) {

        return new BalanceDTO(playerId, balance);
    }

}