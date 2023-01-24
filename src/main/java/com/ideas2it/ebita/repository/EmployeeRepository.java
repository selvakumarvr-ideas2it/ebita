package com.ideas2it.ebita.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.ideas2it.ebita.entity.Employee;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class EmployeeRepository {

    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
    Region region = Region.US_EAST_1;
    DynamoDbClient ddb = DynamoDbClient.builder()
            .credentialsProvider(credentialsProvider)
            .region(region)
            .build();

    DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddb)
            .build();


    private DynamoDbTable<Employee> EMPLOYEE_TABLE =
            enhancedClient.table("employee",
                    TableSchema.fromBean(Employee.class));

    public void saveEmployee(Employee emp) {
        EMPLOYEE_TABLE.putItem(emp);
    }

    public Employee getEmployee(String key) {

        Key key1 = Key.builder()
                .partitionValue(key)
                .build();

        return EMPLOYEE_TABLE.getItem(r -> r.key(key1));
    }

    public List<Employee> getAllEmployees() {
        Iterator<Employee> employees = EMPLOYEE_TABLE.scan().items().iterator();
        List<Employee> emps = new ArrayList<>();
        while (employees.hasNext()) {
            Employee emp = employees.next();
            emps.add(emp);
        }
        return emps;
    }

    public Employee deleteEmployee(String key) {
        Key key1 = Key.builder()
                .partitionValue(key)
                .build();

        return EMPLOYEE_TABLE.deleteItem(key1);
    }

    public void updateEmp(Employee emp) {
        EMPLOYEE_TABLE.putItem(emp);
    }

}