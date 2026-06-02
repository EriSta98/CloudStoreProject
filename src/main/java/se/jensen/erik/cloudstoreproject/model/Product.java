package se.jensen.erik.cloudstoreproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    private long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product() {
    }
}
