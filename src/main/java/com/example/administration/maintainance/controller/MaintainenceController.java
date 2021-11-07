package com.example.administration.maintainance.controller;

import com.example.administration.maintainance.models.ColumnDefinition;
import com.example.administration.maintainance.repo.InMemoryMaintainanceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MaintainenceController {

    @Autowired
    private final InMemoryMaintainanceRepo inMemoryMaintainanceRepo;

    @GetMapping("/expenses/{apartment}")
    public Map<String, Object> expenses(@PathVariable String apartment) {
        return this.inMemoryMaintainanceRepo.getExpenseByAp(apartment);
    }
    @GetMapping("/columns")
    public List<ColumnDefinition> columns() {
        return this.inMemoryMaintainanceRepo.getColumns();
    }
    @GetMapping("/data")
    public Collection<LinkedHashMap<String, Object>> data() {
        return this.inMemoryMaintainanceRepo.getExpenses();
    }
}
