package com.springcli.console;

import com.springcli.model.Attribute;
import com.springcli.model.Entity;

import java.util.Objects;
import java.util.Scanner;

public class EntityConsole {
    static private EntityConsole instance;
    Scanner scanner = new Scanner(System.in);

    public static EntityConsole getInstance() {
        if(Objects.isNull(instance)) {
            instance = new EntityConsole();
        }
        return instance;
    }

    private EntityConsole() {
    }

    public void askForEntity() {
        String entityName;
        String entityQuestion = "What is your entity name (for your comfort use PascalCase)?";
        System.out.println(entityQuestion);
        while(!(entityName = scanner.nextLine()).isEmpty()) {
            if(!isPascalCase(entityName)) {
                System.out.println("Please use PascalCase");
                continue;
            }
            Entity entity = new Entity(entityName);
            boolean loop = true;
            while (loop) {
                try {
                    askForAttribute(entity);
                } catch (Exception e) {
                    System.out.println("entity finished");
                    loop = false;
                }
            }
            System.out.println(entity);
            System.out.println(entityQuestion);
        }
    }

    private boolean isPascalCase(String string) {
        return string.matches("[A-Z][a-z0-9A-Z]*");
    }

    private boolean isCamelCase(String string) {
        return string.matches("[a-z][a-z0-9A-Z]*");
    }

    public void askForAttribute(Entity entity) throws Exception {
        String attributeNameQuestion = "What attribute do you want to add to the " + entity.getName() + " class";
        String attributeName;
        System.out.println(attributeNameQuestion);
        while(!(attributeName = scanner.nextLine()).isEmpty()) {

            if(!isCamelCase(attributeName)) {
                System.out.println("Please use camelCase");
                System.out.println(attributeNameQuestion);
                continue;
            }
            String attributeType = null;
            try {
                entity.addAttribute(askForAttributeType(attributeName));
            } catch (Exception e) {
                System.out.println(attributeName + " does not have type. Operation aborted");
                System.out.println(attributeNameQuestion);
                continue;
            }
            System.out.println(attributeNameQuestion);
        }
        throw new Exception("attribute aborted");
    }

    public Attribute askForAttributeType(String attributeName) throws Exception {
        String attributeTypeQuestion = "What is the type of this attribute ?";
        String attributeType;
        System.out.println(attributeTypeQuestion);
        while (!(attributeType = scanner.nextLine()).isEmpty()) {

            if(!Attribute.getBasicTypeList().contains(attributeType)
                    && !Attribute.getNewTypeList().contains(attributeType)) {
                System.out.println(attributeType + " is an unknown type");
                System.out.println(attributeTypeQuestion);
                continue;
            }
            Attribute attribute = new Attribute(attributeName, attributeType);
            if(Attribute.getNewTypeList().contains(attributeType)) {
                //TODO ask for relationship
            }
            return attribute;
        }
        throw new Exception("abort attribute.");
    }

}
