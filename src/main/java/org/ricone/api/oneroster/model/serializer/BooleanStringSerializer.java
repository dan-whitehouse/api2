package org.ricone.api.oneroster.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class BooleanStringSerializer extends StdSerializer<Boolean> {

    public BooleanStringSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(bool ? "true" : "false");
    }
}