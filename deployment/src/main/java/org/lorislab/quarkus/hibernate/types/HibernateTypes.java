package org.lorislab.quarkus.hibernate.types;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class HibernateTypes {

    static final String FEATURE_NAME = "hibernate-types";

    @BuildStep
    FeatureBuildItem createFeatureItem() {
        return new FeatureBuildItem(FEATURE_NAME);
    }

}
