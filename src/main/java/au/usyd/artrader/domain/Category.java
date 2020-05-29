package au.usyd.artrader.domain;

public enum Category {
    ALL("All"),
    PAINTING("Painting"),
    SCULPTURE("Sculpture"),
    CERAMICS("Ceramics"),
    PHOTOGRAPHY("Photography");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Boolean isValid(String value) {
        return ALL.value.equals(value)
                || PAINTING.value.equals(value)
                || SCULPTURE.value.equals(value)
                || CERAMICS.value.equals(value)
                || PHOTOGRAPHY.value.equals(value);
    }
}
