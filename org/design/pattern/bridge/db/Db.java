package org.design.pattern.bridge.db;

import org.design.pattern.bridge.profile.Dev;
import org.design.pattern.bridge.profile.Prod;
import org.design.pattern.bridge.profile.Profile;

import java.util.Arrays;

/**
 * @author: lhz
 * @date: 2021/1/28
 **/
public abstract class Db {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2,9,8, 1, 0, 0, 5,2,7, 1, 2, 0};
        //paritionA(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    String username = "null";
    String password = "null";
    Profile dev;

    void setDev(Profile dev) {
        this.dev = dev;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFulllAddr(){
        return dev.getIdAddr()+" "+getUsername()+" "+getPassword();
    }
    public static void quickSort(int[] arr,int i,int j){
        int start = i;
        int end = j;
        int pivot = arr[(start + end) / 2];

        while (start <= end) {
            while (start <= end&& arr[start] <pivot) {
                start++;
            }
            while (start <= end&&arr[end] >pivot) {
                end--;
            }
            if (start <= end) {
                swap(arr,start,end);
                start++;
                end--;
            }
            quickSort(arr, i, end);
            quickSort(arr, start, j);
        }

    }



    public static void paritionA(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int index = 0;
        while (index <= end) {
            if (arr[index] == 1) {
                index++;
            } else if (arr[index] < 1) {
                swap(arr, start, index);
                start++;
                index++;
            }else {
                swap(arr,end,index);
                end--;
            }
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

}
