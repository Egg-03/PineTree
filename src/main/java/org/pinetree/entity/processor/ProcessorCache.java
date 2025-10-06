package org.pinetree.entity.processor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder(toBuilder = true)
public class ProcessorCache {

    @SerializedName("DeviceID")
    @Nullable
    String deviceId;

    @SerializedName("Purpose")
    @Nullable
    String purpose;

    @SerializedName("InstalledSize")
    @Nullable
    Integer installedSize;

    @SerializedName("Associativity")
    @Nullable
    Integer associativity;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
