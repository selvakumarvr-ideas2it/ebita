package com.ideas2it.ebita.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ideas2it.ebita.entity.User;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class UserRepository {

    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    Region region = Region.US_EAST_1;
    DynamoDbClient ddb = DynamoDbClient.builder()
            .credentialsProvider(credentialsProvider)
            .region(region)
            .build();

    DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddb)
            .build();


    private DynamoDbTable<User> USER_TABLE =
            enhancedClient.table("user",
                    TableSchema.fromBean(User.class));

    public void saveUser(User user) {
        USER_TABLE.putItem(user);
    }

    public User getUser(String key) {

        Key key1 = Key.builder()
                .partitionValue(key)
                .build();

        return USER_TABLE.getItem(r -> r.key(key1));
    }

    public List<User> getAllUsers() {
        Iterator<User> Users = USER_TABLE.scan().items().iterator();
        List<User> usrs = new ArrayList<>();
        while (Users.hasNext()) {
            User usr = Users.next();
            usrs.add(usr);
        }
        return usrs;
    }

    public User deleteUser(String key) {
        Key key1 = Key.builder()
                .partitionValue(key)
                .build();

        return USER_TABLE.deleteItem(key1);
    }

    public void updateUser(User user) {
        USER_TABLE.putItem(user);
    }

}
