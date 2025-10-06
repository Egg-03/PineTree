package org.pinetree.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(toBuilder = true)
public class Processor {

    @Nullable
    String deviceId;

    @Nullable
    String name;

    @Nullable
    Integer numberOfCores;

    @Nullable
    Integer threadCount;

    @Nullable
    Integer numberOfLogicalProcessors;

    @Nullable
    String manufacturer;

    @Nullable
    Integer addressWidth;

    @Nullable
    Integer l2CacheSize;

    @Nullable
    Integer l3CacheSize;

    @Nullable
    Integer maxClockSpeed;

    @Nullable
    Integer extClock;

    @Nullable
    String socketDesignation;

    @Nullable
    String version;

    @Nullable
    String caption;

    @Nullable
    Integer family;

    @Nullable
    String stepping;

    @Nullable
    Boolean virtualizationFirmwareEnabled;

    @Nullable
    String processorId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
