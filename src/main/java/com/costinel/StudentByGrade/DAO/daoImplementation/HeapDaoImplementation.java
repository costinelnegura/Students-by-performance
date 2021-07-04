package com.costinel.StudentByGrade.DAO.daoImplementation;

import com.costinel.StudentByGrade.DAO.dao.HeapDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeapDaoImplementation implements HeapDao {

    private int size = 0;
    private List<String> temp;

    @Override
    public void setSize(int size){
        temp = new ArrayList<>(size);
    }

    @Override
    public void insert(List<String> list) {

        for(var string : list){
            temp.add(size, string);
            fixHeapAbove(size);
            size++;
        }

        heapSort();
            size = 0;
    }

    @Override
    public int getParent(int index) {
        return (index - 1) / 2;
    }

    @Override
    public int getChild(int index, boolean left) {
        return 2 * index + (left ? 1 : 2);
    }

    @Override
    public void fixHeapAbove(int index) {
        var newValue = temp.get(index);
        while (index > 0 && Double.parseDouble(newValue.substring(newValue.indexOf(",") + 1)) >
                Double.parseDouble(temp.get(getParent(index)).substring(temp.get(getParent(index)).indexOf(",") + 1))) {

            temp.set(index, temp.get(getParent(index)));
            index = getParent(index);
        }

        temp.set(index, newValue);
    }

    @Override
    public void fixHeapBelow(int index, int lastHeapIndex) {
        int childToSwapIndex;

        while (index <= lastHeapIndex) {
            var leftChildIndex = getChild(index, true);
            var rightChildIndex = getChild(index, false);

            if (leftChildIndex <= lastHeapIndex) {
                if (rightChildIndex > lastHeapIndex) {
                    childToSwapIndex = leftChildIndex;
                } else {
                    childToSwapIndex = (Double.parseDouble(temp.get(leftChildIndex).substring(temp.get(leftChildIndex).indexOf(",") + 1)) >
                            Double.parseDouble(temp.get(rightChildIndex).substring(temp.get(rightChildIndex).indexOf(",") + 1)) ?
                            leftChildIndex : rightChildIndex);
                }


                if (Double.parseDouble(temp.get(index).substring(temp.get(index).indexOf(",") + 1)) <
                        Double.parseDouble(temp.get(childToSwapIndex).substring(temp.get(childToSwapIndex).indexOf(",") + 1))) {

                    var temp1 = temp.get(index);
                    temp.set(index, temp.get(childToSwapIndex));
                    temp.set(childToSwapIndex, temp1);

                } else {
                    break;
                }

                index = childToSwapIndex;
            } else {
                break;
            }

        }
    }

    @Override
    public void heapSort() {

        var lastHeapIndex = size - 1;

        for (var i = 0; i < lastHeapIndex; i++) {
            var temp1 = temp.get(0);
            temp.set(0, temp.get(lastHeapIndex - i));
            temp.set(lastHeapIndex - i, temp1);

            fixHeapBelow(0, lastHeapIndex - i - 1);
        }
    }

    public List<String> returnList(){
        return temp;
    }
}

