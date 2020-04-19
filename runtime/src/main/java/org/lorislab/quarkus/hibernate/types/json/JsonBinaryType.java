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

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;
import org.lorislab.quarkus.hibernate.types.json.impl.JsonBinarySqlTypeDescriptor;
import org.lorislab.quarkus.hibernate.types.json.impl.JsonTypeDescriptor;
import org.lorislab.quarkus.hibernate.types.json.impl.ObjectMapperWrapper;

import java.util.Properties;

/**
 * Maps any given Java object on a JSON column type that is managed via {@link java.sql.PreparedStatement#setObject(int, Object)} at JDBC Driver level.
 * <p>
 * If you are using <strong>PostgreSQL</strong>, you should use this {@link JsonBinaryType} to map both <strong>{@code jsonb}</strong> and <strong>{@code json}</strong> column types.
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 *
 * Remove unused methods.
 * @author Andrej Petras
 */
public class JsonBinaryType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType {

    public JsonBinaryType() {
        super(JsonBinarySqlTypeDescriptor.INSTANCE, new JsonTypeDescriptor(ObjectMapperWrapper.INSTANCE));
    }

    public String getName() {
        return JsonTypes.JSON_BIN;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((JsonTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
    }

}
