package com.vther.orm.support.core.type;


public interface OrmType {

    String JDBC = "JDBC";

    String HIBERNATE = "HIBERNATE";

    String MYBATIS = "MYBATIS";

    String JPA = "JPA";

    String JPA_ECLIPSELINK = "JPA_ECLIPSELINK";

    String JPA_HIBERNATE = "JPA_HIBERNATE";

}


/*
  public enum OrmType {

    JDBC(1, "JDBC"),

    HIBERNATE(2, "HIBERNATE"),

    MYBATIS(3, "MYBATIS"),

    JPA_ECLIPSELINK(4, "JPA_ECLIPSELINK"),

    JPA_HIBERNATE(5, "JPA_HIBERNATE");

    private int index;
    private String type;

    OrmType(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }
}
 */
