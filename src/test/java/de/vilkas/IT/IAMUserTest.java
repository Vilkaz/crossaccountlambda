package de.vilkas.IT;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.User;
import de.vilkas.common.IAMSupplier;
import de.vilkas.controller.IAMUserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.iam.model.GetUserRequest;

import java.util.Optional;
import java.util.function.Predicate;

public class IAMUserTest {

    @Test
    public void createAndDeleteUserByName() {
        String name = "Testuser1337";
        IAMUserController controller = new IAMUserController();
        //create a new iam user
        controller.createCrossAccountUser(name);
        //search for iam user by name on the AWS account
        GetUserRequest getUserRequest = GetUserRequest
                .builder()
                .userName(name)
                .build();

        final AmazonIdentityManagement iam = IAMSupplier.get();

        ListUsersRequest request = new ListUsersRequest();
        ListUsersResult response = iam.listUsers(request);
        Optional<User> testUser = response.getUsers().stream()
                .filter(testuserExists(name))
                .findAny();

        Assertions.assertTrue(testUser.isPresent());

        controller.deleteCrossAccountUser(name);

        request = new ListUsersRequest();
        response = iam.listUsers(request);
        testUser = response.getUsers().stream()
                .filter(testuserExists(name))
                .findAny();

        Assertions.assertFalse(testUser.isPresent());
    }

    private Predicate<User> testuserExists(String s) {
        String testUser = s + "-user";
        return u -> testUser.equals(u.getUserName());
    }
}
