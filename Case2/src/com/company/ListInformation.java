package com.company;

import java.util.Objects;

public interface ListInformation<T> {
    void addInfo(T element);
    void showList();
    void editInfo();
    void deleteInfo();
}
