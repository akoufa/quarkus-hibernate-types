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
package org.lorislab.quarkus.hibernate.types.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.lorislab.quarkus.hibernate.types.json.impl.JsonBinarySqlTypeDescriptor;
import org.lorislab.quarkus.hibernate.types.json.impl.JsonNodeTypeDescriptor;
import org.lorislab.quarkus.hibernate.types.json.impl.ObjectMapperWrapper;

/**
 * Maps a Jackson {@link JsonNode} object on a JSON column type that is managed via {@link java.sql.PreparedStatement#setObject(int, Object)} at JDBC Driver level. For instance, if you are using PostgreSQL, you should be using {@link JsonNodeBinaryType} to map both {@code jsonb} and {@code json} column types to a Jackson {@link JsonNode} object.
 *
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/how-to-store-schema-less-eav-entity-attribute-value-data-using-json-and-hibernate/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 *
 * Remove unused methods.
 * @author Andrej Petras
 */
public class JsonNodeBinaryType extends AbstractSingleColumnStandardBasicType<JsonNode> {

    public JsonNodeBinaryType() {
        super(JsonBinarySqlTypeDescriptor.INSTANCE, new JsonNodeTypeDescriptor(ObjectMapperWrapper.INSTANCE));
    }

    public String getName() {
        return JsonTypes.JSON_NODE_BIN;
    }
}