package com.costinel.StudentByGrade.DAO.dao;

import java.util.List;

public interface HeapDao {

    void setSize(int size);
    void insert(List<String> list);
    int getParent(int index);
    int getChild(int index, boolean left);
    void fixHeapAbove(int index);
    void fixHeapBelow(int index, int lastHeapIndex);
    void heapSort();
}
