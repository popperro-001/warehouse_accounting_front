package com.warehouse_accounting.components.sales.grids;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.warehouse_accounting.models.dto.CustomerOrdersDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SalesGridLayout extends HorizontalLayout {

    private final TextField selectedTextField;
    private final Grid<CustomerOrdersDto> customerOrdersDtoGrid = new Grid<>(CustomerOrdersDto.class, false);

    public SalesGridLayout(TextField selectedTextField) {
        this.selectedTextField = selectedTextField;
        add(initGrid());
    }

    private Grid<CustomerOrdersDto> initGrid() {
        customerOrdersDtoGrid.setColumns(getVisibleColumn().keySet().toArray(String[]::new));
        customerOrdersDtoGrid.setSelectionMode(Grid.SelectionMode.MULTI);

        getVisibleColumn().forEach((key, value) -> customerOrdersDtoGrid.getColumnByKey(key).setHeader(value));
        customerOrdersDtoGrid.asMultiSelect().addSelectionListener(listener -> {
            int selectSize = listener.getAllSelectedItems().size();
            selectedTextField.setValue(String.valueOf(selectSize));
        });
        customerOrdersDtoGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        return customerOrdersDtoGrid;
    }

    public Grid<CustomerOrdersDto> getProductGrid() {
        return customerOrdersDtoGrid;
    }

    private HashMap<String, String> getVisibleColumn() {
        HashMap<String, String> fieldNameColumnName = new LinkedHashMap<>();
        fieldNameColumnName.put("id", "Id");
        fieldNameColumnName.put("customer", "Контрагент");
        fieldNameColumnName.put("summa", "Сумма");
        fieldNameColumnName.put("bill", "Выставлено счетов");
        fieldNameColumnName.put("summaPaid", "Оплачено");
        fieldNameColumnName.put("shipped", "Отгружено");
        fieldNameColumnName.put("reserved", "Зарезервировано");
        fieldNameColumnName.put("status", "Статус");
        fieldNameColumnName.put("sent", "Отправлено");
        fieldNameColumnName.put("print", "Напечатано");
        fieldNameColumnName.put("comment", "Комментарий");
        return fieldNameColumnName;
    }
}
