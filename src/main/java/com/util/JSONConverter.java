package com.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.TextFile;

public class JSONConverter {

    private TextFile textFile;

    public JSONConverter(TextFile textFile) {
        this.textFile = textFile;
    }

    /**
     * Generating and giving JSON view of object - textFile
     */
    public String getJSON(boolean includeMetaData) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
            .setPrettyPrinting()
            .setExclusionStrategies(new ExcludeStrategy(includeMetaData))
            .create();
        return gson.toJson(textFile);
    }
}
