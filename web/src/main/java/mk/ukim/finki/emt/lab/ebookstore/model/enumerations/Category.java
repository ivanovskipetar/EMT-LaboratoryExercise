package mk.ukim.finki.emt.lab.ebookstore.model.enumerations;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Category {
    NOVEL("NOVEL"),
    THRILER("THRILER"),
    HISTORY("HISTORY"),
    FANTASY("FANTASY"),
    BIOGRAPHY("BIOGRAPHY"),
    CLASSICS("CLASSICS"),
    DRAMA("DRAMA");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }}