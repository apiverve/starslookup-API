// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     StarsLookupData data = Converter.fromJsonString(jsonString);

package com.apiverve.starslookup.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static StarsLookupData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(StarsLookupData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(StarsLookupData.class);
        writer = mapper.writerFor(StarsLookupData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// StarsLookupData.java

package com.apiverve.starslookup.data;

import com.fasterxml.jackson.annotation.*;

public class StarsLookupData {
    private String starName;
    private double mass;
    private long diameter;
    private double galX;
    private double galY;
    private double galZ;
    private double dist;
    private String starType;
    private long temp;
    private String color;

    @JsonProperty("starName")
    public String getStarName() { return starName; }
    @JsonProperty("starName")
    public void setStarName(String value) { this.starName = value; }

    @JsonProperty("mass")
    public double getMass() { return mass; }
    @JsonProperty("mass")
    public void setMass(double value) { this.mass = value; }

    @JsonProperty("diameter")
    public long getDiameter() { return diameter; }
    @JsonProperty("diameter")
    public void setDiameter(long value) { this.diameter = value; }

    @JsonProperty("galX")
    public double getGalX() { return galX; }
    @JsonProperty("galX")
    public void setGalX(double value) { this.galX = value; }

    @JsonProperty("galY")
    public double getGalY() { return galY; }
    @JsonProperty("galY")
    public void setGalY(double value) { this.galY = value; }

    @JsonProperty("galZ")
    public double getGalZ() { return galZ; }
    @JsonProperty("galZ")
    public void setGalZ(double value) { this.galZ = value; }

    @JsonProperty("dist")
    public double getDist() { return dist; }
    @JsonProperty("dist")
    public void setDist(double value) { this.dist = value; }

    @JsonProperty("starType")
    public String getStarType() { return starType; }
    @JsonProperty("starType")
    public void setStarType(String value) { this.starType = value; }

    @JsonProperty("temp")
    public long getTemp() { return temp; }
    @JsonProperty("temp")
    public void setTemp(long value) { this.temp = value; }

    @JsonProperty("color")
    public String getColor() { return color; }
    @JsonProperty("color")
    public void setColor(String value) { this.color = value; }
}