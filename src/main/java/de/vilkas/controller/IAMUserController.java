package de.vilkas.controller;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.DeleteUserResult;
import de.vilkas.common.IAMSupplier;

public class IAMUserController {

    private static final String SUFFIX = "-user";

    public CreateUserResult createCrossAccountUser(String processname) {
        String username = processname + SUFFIX;

        final AmazonIdentityManagement iam = IAMSupplier.get();

        CreateUserRequest request = new CreateUserRequest()
                .withUserName(username);

        CreateUserResult response = iam.createUser(request);

        System.out.println("Successfully created user: " +
                response.getUser().getUserName());

        return response;
    }

    public DeleteUserResult deleteCrossAccountUser(String processname) {
        String username = processname + SUFFIX;

        final AmazonIdentityManagement iam = IAMSupplier.get();

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest()
                .withUserName(username);
        DeleteUserResult response = iam.deleteUser(deleteUserRequest);

        System.out.println("Successfully deleted user: " +
                response.toString());

        return response;
    }

}
