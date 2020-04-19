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

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.MutableMutabilityPlan;

import java.io.Serializable;

/**
 * @author Vlad Mihalcea
 *
 * Remove unused methods.
 * @author Andrej Petras
 */
public class JsonNodeTypeDescriptor extends AbstractTypeDescriptor<JsonNode> {

    private ObjectMapperWrapper objectMapperWrapper;

    public JsonNodeTypeDescriptor(final ObjectMapperWrapper objectMapperWrapper) {
        super(JsonNode.class, new MutableMutabilityPlan<JsonNode>() {
            @Override
            public Serializable disassemble(JsonNode value) {
                return objectMapperWrapper.writeValueAsString(value);
            }

            @Override
            public JsonNode assemble(Serializable cached) {
                return objectMapperWrapper.readTree((String) cached);
            }

            @Override
            protected JsonNode deepCopyNotNull(JsonNode value) {
                return objectMapperWrapper.clone(value);
            }
        });
        this.objectMapperWrapper = objectMapperWrapper;
    }

    @Override
    public boolean areEqual(JsonNode one, JsonNode another) {
        if (one == another) {
            return true;
        }
        if (one == null || another == null) {
            return false;
        }
        return objectMapperWrapper.readTree(objectMapperWrapper.writeValueAsString(one)).equals(
                objectMapperWrapper.readTree(objectMapperWrapper.writeValueAsString(another)));
    }

    @Override
    public String toString(JsonNode value) {
        return objectMapperWrapper.writeValueAsString(value);
    }

    @Override
    public JsonNode fromString(String string) {
        return objectMapperWrapper.readTree(string);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public <X> X unwrap(JsonNode value, Class<X> type, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        if (String.class.isAssignableFrom(type)) {
            return (X) toString(value);
        }
        if (JsonNode.class.isAssignableFrom(type)) {
            return (X) objectMapperWrapper.readTree(toString(value));
        }
        throw unknownUnwrap(type);
    }

    @Override
    public <X> JsonNode wrap(X value, WrapperOptions options) {
        if (value == null) {
            return null;
        }
        return fromString(value.toString());
    }

}
