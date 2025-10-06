package org.pinetree.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(toBuilder = true)
public class Processor {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Name")
    @Nullable
    String name;

    @SerializedName("NumberOfCores")
    @Nullable
    Integer numberOfCores;

    @SerializedName("ThreadCount")
    @Nullable
    Integer threadCount;

    @SerializedName("NumberOfLogicalProcessors")
    @Nullable
    Integer numberOfLogicalProcessors;

    @SerializedName("Manufacturer")
    @Nullable
    String manufacturer;

    @SerializedName("AddressWidth")
    @Nullable
    Integer addressWidth;

    @SerializedName("L2CacheSize")
    @Nullable
    Integer l2CacheSize;

    @SerializedName("L3CacheSize")
    @Nullable
    Integer l3CacheSize;

    @SerializedName("MaxClockSpeed")
    @Nullable
    Integer maxClockSpeed;

    @SerializedName("ExtClock")
    @Nullable
    Integer extClock;

    @SerializedName("SocketDesignation")
    @Nullable
    String socketDesignation;

    @SerializedName("Version")
    @Nullable
    String version;

    @SerializedName("Caption")
    @Nullable
    String caption;

    @SerializedName("Family")
    @Nullable
    Integer family;

    @SerializedName("Stepping")
    @Nullable
    String stepping;

    @SerializedName("VirtualizationFirmwareEnabled")
    @Nullable
    Boolean virtualizationFirmwareEnabled;

    @SerializedName("ProcessorId")
    @Nullable
    String processorId;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
