package com.geekbrains.july.market.julymarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Catergory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "incl_prod_in_categ",
            joinColumns = @JoinColumn(name = "id_category"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private List<Product> products;

    public Catergory(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
