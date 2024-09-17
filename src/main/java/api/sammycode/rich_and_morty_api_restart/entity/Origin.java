package api.sammycode.rich_and_morty_api_restart.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "origin_id")
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String url;

    public Origin() {
    }
    public Origin(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
