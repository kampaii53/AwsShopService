package ru.kampaii.shop.repositories.dynamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kampaii.shop.model.entities.dynamo.ProductDynamoEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDynamoRepository {

    @Autowired
    private DynamoDbEnhancedClient dynamoDbenhancedClient;

    // Store the order item in the database
    public void save(final ProductDynamoEntity product) {
        DynamoDbTable<ProductDynamoEntity> orderTable = getTable();
        orderTable.putItem(product);
    }

    public List<ProductDynamoEntity> findAll(){

        return getTable().scan().items()
                .stream()
                .collect(Collectors.toList());
    }

    // Retrieve a single order item from the database
    public ProductDynamoEntity findById(final String id) {
        DynamoDbTable<ProductDynamoEntity> orderTable = getTable();
        // Construct the key with partition and sort key
        Key key = Key.builder().partitionValue(id)
                .build();

        return orderTable.getItem(key);
    }


    private DynamoDbTable<ProductDynamoEntity> getTable() {
        // Create a tablescheme to scan our bean class order
        return dynamoDbenhancedClient.table("products",
                TableSchema.fromBean(ProductDynamoEntity.class));
    }
}