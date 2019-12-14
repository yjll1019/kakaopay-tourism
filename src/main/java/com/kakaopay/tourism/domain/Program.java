package com.kakaopay.tourism.domain;

import java.util.List;
import javax.persistence.*;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String introduce;

    private String contents;

    @ManyToMany
    private List<Region> regions;

    @ManyToMany
    private List<Theme> themes;

    public Program() {
    }

    public Program(String name, String introduce, String contents, List<Region> regions, List<Theme> themes) {
        this.name = name;
        this.introduce = introduce;
        this.contents = contents;
        this.regions = regions;
        this.themes = themes;
    }
}
