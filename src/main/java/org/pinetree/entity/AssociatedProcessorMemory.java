package org.pinetree.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

@Value
@Builder (toBuilder = true)
public class AssociatedProcessorMemory {

    @SerializedName("Antecedent")
    @Nullable
    String antecedent;

    @SerializedName("Dependent")
    @Nullable
    String dependent;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
