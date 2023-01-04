package br.com.attornatus.enums;

public enum TypeAddress {
    ADDRESS(0, "principal");

    private Integer code;
    private String type;


    private TypeAddress(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
