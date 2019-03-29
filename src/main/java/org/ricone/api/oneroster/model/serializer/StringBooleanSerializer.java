package org.ricone.api.oneroster.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StringBooleanSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(bool ? "true" : "false");
    }
}