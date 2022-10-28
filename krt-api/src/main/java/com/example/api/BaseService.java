package com.example.api;

import com.example.pojo.entity.BaseDevice;
import com.example.pojo.entity.Jl900;

public interface BaseService<BaseDevice> {
    BaseDevice getNewRecord();
    boolean saveRecord(BaseDevice o);

    BaseDevice parseData(byte[] data, Long devId, String location);

}
