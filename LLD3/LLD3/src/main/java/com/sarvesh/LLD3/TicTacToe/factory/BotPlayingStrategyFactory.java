package com.sarvesh.LLD3.TicTacToe.factory;

import com.sarvesh.LLD3.TicTacToe.model.BotDifficultyLevel;
import com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy.BotPlayingStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy.EasyBotPlayingStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy.HardBotPlayingStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyFactory(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else {
            return new HardBotPlayingStrategy();
        }

    }
}
