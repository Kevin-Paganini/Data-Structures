package paganiniK;

import java.util.ArrayList;
import java.util.List;

public class SmallerBiggerSort {







    public static <T extends Comparable<T>> int smallerBigger(List<T> list, int start, int end){
        if(list.size() == 0) {
            return 0;
        }
        T first = list.get(start);
        ArrayList<T> left = new ArrayList();
        ArrayList<T> right = new ArrayList();

        for(int i = start + 1; i <= end; ++i){
            if (list.get(i).compareTo(first) <= 0) {
                left.add(list.get(i));
            } else if (list.get(i).compareTo(first) > 0) {
                right.add(list.get(i));
            }
        }
        if(left.size() == list.size() -1) {
            return 0;
        }
        if(right.size() == list.size() - 1) {
            return left.size();
        }
        if (left.size() > 0){
            for(int i = start; i < start + left.size(); ++i){
                list.set(i, left.get(i - start));
            }
        }
        list.set(start + left.size(), first);
        if (right.size() > 0){
            for(int i = start + left.size() + 1; i <= end; ++i) {
                if(i - start - left.size() -1 < end){
                    list.set(i, right.get(i - start - left.size() - 1));
                }
            }
        }
        int sizeLeft = left.size();


        return start + sizeLeft;
    }
    public static <T extends Comparable<T>> void sort(List<T> list, int start, int end) {

        if (start < end) {

            int sizeLeft = smallerBigger(list, start, end);


            if (end - start > 2 && (sizeLeft - 1) + start > 0) {
                sort(list, start, sizeLeft);
            }
            if (end - start > 2 && sizeLeft + 1 + start > 0) {
                sort(list, sizeLeft + 1, end);
            }

        }
    }

    public static <T extends Comparable<T>> void sort(List<T> list) {

            sort(list, 0, list.size() - 1);
    }




}