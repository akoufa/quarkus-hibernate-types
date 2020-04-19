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
import org.lorislab.quarkus.hibernate.types.json.impl.JsonTypeDescriptor;
import org.lorislab.quarkus.hibernate.types.json.impl.ObjectMapperWrapper;

import java.util.Properties;

/**
 * Maps any given Java object on a JSON column type that is managed via {@link java.sql.PreparedStatement#setBlob(int, Blob)} at JDBC Driver level.
 * <p>
 * If you are using Oracle, you should use this {@link JsonBlobType} to map a {@code BLOB} column type storing JSON.
 * <p>
 * For more details about how to use it, check out <a href="https://vladmihalcea.com/oracle-json-jpa-hibernate/">this article</a> on <a href="https://vladmihalcea.com/">vladmihalcea.com</a>.
 *
 * @author Vlad Mihalcea
 *
 * Remove unused methods.
 * @author Andrej Petras
 */
public class JsonBlobType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType {

    public JsonBlobType() {
        super(org.hibernate.type.descriptor.sql.BlobTypeDescriptor.DEFAULT, new JsonTypeDescriptor(ObjectMapperWrapper.INSTANCE));
    }

    public String getName() {
        return JsonTypes.JSON_BLOB;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        ((JsonTypeDescriptor) getJavaTypeDescriptor()).setParameterValues(parameters);
    }

}