# quarkus-hibernate-types

[![License](https://img.shields.io/github/license/lorislab/quarkus-hibernate-types?style=for-the-badge&logo=apache)](https://www.apache.org/licenses/LICENSE-2.0)
[![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/lorislab/quarkus-hibernate-types/build/master?logo=github&style=for-the-badge)](https://github.com/lorislab/quarkus-hibernate-types/actions?query=workflow%3Abuild)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/lorislab/quarkus-hibernate-types?logo=github&style=for-the-badge)](https://github.com/lorislab/quarkus-hibernate-types/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/org.lorislab.quarkus/quarkus-hibernate-types?logo=java&style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/org.lorislab.quarkus/quarkus-hibernate-types)

This [Quarkus](https://quarkus.io/) Hibernate types extension is based on the [https://github.com/vladmihalcea/hibernate-types](https://github.com/vladmihalcea/hibernate-types).

Changes:
* remove unused methods
* inject `ObjectMapper` from `Arc` container
* defined `String` constants for JSON types.
* remove hibernate clone method and reflection for the native image.

## How to

Quarkus Hibernate types
* JSON

Add this maven dependency to you project.
```xml
<dependency>
    <groupId>org.lorislab.quarkus</groupId>
    <artifactId>quarkus-hibernate-types</artifactId>
    <version>0.1.1</version>
</dependency>
```
Add the `@TypeDef` annotation to your entity class and to entity field `@Type` annotation.

```java
import org.lorislab.quarkus.hibernate.types.json.JsonBinaryType;
import org.lorislab.quarkus.hibernate.types.json.JsonTypes;
import javax.persistence.*;

@TypeDef(name = JsonTypes.JSON_BIN, typeClass = JsonBinaryType.class)
@Entity
public class Data {

    @Type(type = JsonTypes.JSON_BIN)
    @Column(columnDefinition = JsonTypes.JSON_BIN)
    private Parameters data = new Parameters();

}
```

Current implemented JSON types

| Name   | Value  | typeClass |
|---|---|---|
| JsonTypes.JSON_STRING  | json  | JsonStringType.class |
| JsonTypes.JSON_BIN  | jsonb | JsonBinaryType.class |
| JsonTypes.JSON_BLOB  | jsonb-lob | JsonBlobType.class |
| JsonTypes.JSON_NODE_STRING  | jsonb-node | JsonNodeStringType.class |
| JsonTypes.JSON_NODE_BIN  | jsonb-node | JsonNodeBinaryType.class |



### Create a release

```bash
mvn semver-release:release-create
```

### Create a patch branch
```bash
mvn semver-release:patch-create -DpatchVersion=x.x.0
