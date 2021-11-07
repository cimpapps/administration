package com.example.administration.maintainance.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnDefinition {
    private String field;
    private String pinned;
}
