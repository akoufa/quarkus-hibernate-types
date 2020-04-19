/*
 * Copyright 2020 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.quarkus.hibernate.types.json.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ArcContainer;

import java.lang.reflect.Type;

import java.io.IOException;

/**
 * Wraps a Jackson {@link ObjectMapper} so that you can supply your own {@link ObjectMapper} reference.
 *
 * @author Vlad Mihalcea
 * @since 2.1.0
 *
 * Gets Jackson object mapper instance from the ARC container.
 * Clone method implemented with Jackson.
 * Refactoring method names.
 *
 * @author Andrej Petras
 */
public class ObjectMapperWrapper {

    public static final ObjectMapperWrapper INSTANCE = new ObjectMapperWrapper();

    private final ObjectMapper objectMapper;

    /**
     * Gets the {@code ObjectMapper} from the ARC container.
     * @return Jackson ObjectMapper instance.
     */
    public static ObjectMapper get() {
        ObjectMapper objectMapper = null;
        ArcContainer container = Arc.container();
        if (container != null) {
            objectMapper = container.instance(ObjectMapper.class).get();
        }
        return objectMapper != null ? objectMapper : new ObjectMapper();
    }

    public ObjectMapperWrapper() {
        this.objectMapper = get();
    }

    public <T> T readValue(String string, Class<T> clazz) {
        try {
            return objectMapper.readValue(string, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given string value: " + string + " cannot be transformed to Json object", e);
        }
    }

    public <T> T readValue(String string, Type type) {
        try {
            return objectMapper.readValue(string, objectMapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            throw new IllegalArgumentException("The given string value: " + string + " cannot be transformed to Json object", e);
        }
    }

    public String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: " + value + " cannot be transformed to a String", e);
        }
    }

    public JsonNode readTree(String value) {
        try {
            return objectMapper.readTree(value);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T clone(T value) {
        try {
            return objectMapper.treeToValue(objectMapper.valueToTree(value), (Class<T>) value.getClass());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: " + value + " cannot be clone.", e);
        }
    }
}