package de.vilkas.controller;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import software.amazon.awssdk.services.iam.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;

public class IAMUserController {

    private static final String SUFFIX = "-user" ;

    public void createCrossAccountUser(String processname) {
        String username = processname + SUFFIX;
        final AmazonIdentityManagement iam =
                AmazonIdentityManagementClientBuilder.defaultClient();

        CreateUserRequest request = new CreateUserRequest()
                .withUserName(username);

        CreateUserResult response = iam.createUser(request);

        System.out.println("Successfully created user: " +
                response.getUser().getUserName());
    }

}
