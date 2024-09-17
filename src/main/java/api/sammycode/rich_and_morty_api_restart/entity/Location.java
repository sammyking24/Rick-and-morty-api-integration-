package api.sammycode.rich_and_morty_api_restart.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Controller;

@Entity
@Data
@Table(name = "location_id")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String url;

    public Location() {
    }
    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
