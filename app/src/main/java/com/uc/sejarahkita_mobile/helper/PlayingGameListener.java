package com.uc.sejarahkita_mobile.helper;

public interface PlayingGameListener {
    void onExitClicked();

    void onSubmitClicked(int page, int life);

    void onGameEnded();
}