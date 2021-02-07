package de.vilkas.common;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;

public class IAMSupplier {

    private static AmazonIdentityManagement iam;

    public static AmazonIdentityManagement get() {
        if (iam == null) {
            iam = AmazonIdentityManagementClientBuilder
                    .standard()
                    .withRegion("eu-central-1")
                    .build();
        }
        return iam;
    }

}
