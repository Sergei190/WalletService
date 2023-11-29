package ru.project.sergei.walletservice.service.player;

import ru.project.sergei.walletservice.domainobject.PlayerDO;
import ru.project.sergei.walletservice.exception.PlayerNotFoundException;

public interface PlayerService {

    PlayerDO findPlayer(Long playerId) throws PlayerNotFoundException;

}