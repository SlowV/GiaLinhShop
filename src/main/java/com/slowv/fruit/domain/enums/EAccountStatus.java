package com.slowv.fruit.domain.enums;

import com.slowv.fruit.domain.Account;

public enum EAccountStatus {
    HOAT_DONG(1), DANG_KHOA(0), DA_XOA(-1);
    private int number;

    EAccountStatus(int number){
        this.number = number;
    }

    public int getInt(){
        return number;
    }

    public static EAccountStatus getStatusByValue(int value) {
        for (EAccountStatus status : EAccountStatus.values()) {
            if (status.number == value) return status;
        }
        throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
    }
}
