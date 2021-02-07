package de.vilkas.controller;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserResult;

public class IAMUserController {

    private static final String SUFFIX = "-user";

    public CreateUserResult createCrossAccountUser(String processname) {
        String username = processname + SUFFIX;

        final AmazonIdentityManagement iam =
                AmazonIdentityManagementClientBuilder
                        .standard()
                        .withRegion("eu-central-1")
                        .build();

        CreateUserRequest request = new CreateUserRequest()
                .withUserName(username);

        CreateUserResult response = iam.createUser(request);

        System.out.println("Successfully created user: " +
                response.getUser().getUserName());

        return response;
    }

    public DeleteUserResult deleteCrossAccountUser(String processname) {
        String username = processname + SUFFIX;

        final AmazonIdentityManagement iam =
                AmazonIdentityManagementClientBuilder
                        .standard()
                        .withRegion("eu-central-1")
                        .build();

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest()
                .withUserName(username);



//        CreateUserRequest request = new CreateUserRequest()
//                .withUserName(username);

        DeleteUserResult response = iam.deleteUser(deleteUserRequest);

        System.out.println("Successfully deleted user: " +
                response.toString());

        return response;
    }

}
