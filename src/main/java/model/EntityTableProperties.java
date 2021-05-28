package model;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class EntityTableProperties {
    Class<?> entityPanelClass;
    String entityName;
    Integer[] nonEditableColumns;
    public static final Map<String, EntityTableProperties> tablePropertiesMap = new HashMap<>();

    static {
        try {
            tablePropertiesMap.put("Сотрудники", new EntityTableProperties(
                    Class.forName("forms.tables.EmployeeForm"),
                    "employee",
                    new Integer[]{0, 4, 6, 7}
            ));

            tablePropertiesMap.put("Животные", new EntityTableProperties(
                    Class.forName("forms.tables.AnimalForm"),
                    "animal",
                    new Integer[]{0}
            ));

            tablePropertiesMap.put("Карточки животных", new EntityTableProperties(
                    Class.forName("forms.tables.AnimalCardForm"),
                    "animal_card",
                    new Integer[]{0, 1, 5, 6, 7, 10}
            ));

            tablePropertiesMap.put("Корма", new EntityTableProperties(
                    Class.forName("forms.tables.FeedForm"),
                    "feed",
                    new Integer[]{}
            ));

            tablePropertiesMap.put("Поставщики", new EntityTableProperties(
                    Class.forName("forms.tables.FeedSupplierForm"),
                    "feed_supplier",
                    new Integer[]{0}
            ));

            tablePropertiesMap.put("Ответственные за вид", new EntityTableProperties(
                    Class.forName("forms.tables.EmployeeAnimalForm"),
                    "Employee_animal_view",
                    new Integer[]{0, 1, 2, 4, 5}
            ));

            tablePropertiesMap.put("Ответственные за особи", new EntityTableProperties(
                    Class.forName("forms.tables.EmployeeAnimalCardForm"),
                    "Employee_animal_card_view",
                    new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}
            ));

            tablePropertiesMap.put("Совместимые животные", new EntityTableProperties(
                    Class.forName("forms.tables.AnimalAnimalForm"),
                    "Animal_animal",
                    new Integer[]{}
            ));

            tablePropertiesMap.put("Пользователи", new EntityTableProperties(
                    Class.forName("forms.tables.UserForm"),
                    "zoo_user",
                    new Integer[]{1}
            ));

            tablePropertiesMap.put("Поставки", new EntityTableProperties(
                    Class.forName("forms.tables.SupplyForm"),
                    "supply",
                    new Integer[]{0}
            ));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public EntityTableProperties(Class<?> entityPanelClass, String entityName, Integer[] nonEditableColumns) {
        this.entityPanelClass = entityPanelClass;
        this.entityName = entityName;
        this.nonEditableColumns = nonEditableColumns;
    }

    public JPanel getEntityPanel() throws IllegalAccessException, InstantiationException {
        return (JPanel) entityPanelClass.newInstance();
    }

    public String getEntityName() {
        return entityName;
    }

    public Integer[] getNonEditableColumns() {
        return nonEditableColumns;
    }
}
