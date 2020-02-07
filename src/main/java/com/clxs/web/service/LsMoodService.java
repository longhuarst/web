package com.clxs.web.service;

import com.clxs.web.Bean.LsMood;

public interface LsMoodService {
    LsMood save(LsMood lsMood);

    String asynSave(LsMood lsMood);
}
