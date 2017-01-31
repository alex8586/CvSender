package com.alex.repositories;

import com.alex.domain.Company;
import javafx.collections.ObservableList;

import java.util.List;

public interface ObservableData {

    void add(Company company);

    void remove(Company company);

    void addAll(List<Company> companies);

    ObservableList<Company> getAll();
}
