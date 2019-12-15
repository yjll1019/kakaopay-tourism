package com.kakaopay.tourism.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String introduce;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;

    @ManyToMany
    private List<Region> regions;

    @ManyToMany
    private List<Theme> themes;

    public Program() {
    }

    public Program(String name, String introduce, String contents) {
        this.name = name;
        this.introduce = introduce;
        this.contents = contents;
    }

    public Program(String name, String introduce, String contents, List<Region> regions, List<Theme> themes) {
        this.name = name;
        this.introduce = introduce;
        this.contents = contents;
        this.regions = regions;
        this.themes = themes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getContents() {
        return contents;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void addThemes(List<Theme> themes) {
        this.themes.addAll(themes);
    }

    public void addRegions(List<Region> regions) {
        this.regions.addAll(regions);
    }

    public void updateThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public void updateRegions(List<Region> regions) {
        this.regions = regions;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }

    public int countOfKeyword(String contentsKeyword) {
        return contents.split(contentsKeyword).length - 1;
    }

    public int countKeyOfIntroduce(String keyword) {
        return introduce.split(keyword).length - 1;
    }

    public int countKeywordOfTheme(String keyword) {
        return (int) themes.stream()
                .filter(theme -> theme.containsKeyword(keyword))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Program)) {
            return false;
        }
        final Program program = (Program) o;
        return Objects.equals(getId(), program.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

