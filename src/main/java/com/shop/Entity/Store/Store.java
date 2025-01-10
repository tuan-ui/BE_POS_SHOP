package com.shop.Entity.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Transient
    public static final String SEQUENCE_NAME = "store_sequence";
    @Id
    private Long id;
    private Integer quantity;
}
