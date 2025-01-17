package com.springcli.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attribute {
    private static final Set<String> basicTypeList = new HashSet<>(List.of(new String[]{"Long", "int", "String"}));
    private static final Set<String> newTypeList = new HashSet<>();
    private final String name;
    private final String type;
    private String relationship;

    public Attribute(String pName, String pType) {
        name = pName;
        type = pType;
    }

    public static void addAttribute(String attribute) {
        newTypeList.add(attribute);
    }

    public static Set<String> getBasicTypeList() {
        return basicTypeList;
    }

    public static Set<String> getNewTypeList() {
        return newTypeList;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return (name + " => " + type);
    }
}
