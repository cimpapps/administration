package com.example.administration.maintainance.repo;

import com.example.administration.maintainance.models.ColumnDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InMemoryMaintainanceRepo {

    public Map<String, LinkedHashMap<String, Object>> expensesByApartment = new HashMap<>();

    public void addExpenses(String apartment, Map<String, Object> expenses) {
        LinkedHashMap<String, Object> expensesFromAp = expensesByApartment.get(apartment);
        if (expensesFromAp == null) {
            expensesFromAp = new LinkedHashMap<>();
        }
        expensesFromAp.putAll(expenses);
        expensesByApartment.put(apartment, expensesFromAp);
    }


    public void addExpense(String apartment, String expense, Double amount) {
        LinkedHashMap<String, Object> expensesFromAp = expensesByApartment.get(apartment);
        if (expensesFromAp == null) {
            expensesFromAp = new LinkedHashMap<>();
            expensesFromAp.put("ap", apartment);
        }
        Double existingAmount = (Double) expensesFromAp.get(expense);
        if (existingAmount != null) {
            amount = existingAmount + amount;
        }
        expensesFromAp.put(expense, amount);
        expensesByApartment.put(apartment, expensesFromAp);
    }

    public Map<String, Object> getExpenseByAp(String apartment) {
        return expensesByApartment.getOrDefault(apartment, new LinkedHashMap<>());
    }

    public Collection<LinkedHashMap<String, Object>> getExpenses() {
        return expensesByApartment.values();
    }

    public List<ColumnDefinition> getColumns() {
        String first = expensesByApartment.keySet().stream().findFirst().get();
        return expensesByApartment.get(first).keySet().stream().map(key -> {
            if (Objects.equals(key, "ap")) {
                return new ColumnDefinition(key, "left");
            }
            return new ColumnDefinition(key, null);
        }).collect(Collectors.toList());
    }

    public void print() {
        expensesByApartment.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
