package api.sammycode.rich_and_morty_api_restart.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "characters")

public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cher_id")
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    private String image;
    @Lob
    @Column(length = 10000)
    private List<String> episode;
    private String url;
    private String created;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_id")
    private Origin origin;
   }

