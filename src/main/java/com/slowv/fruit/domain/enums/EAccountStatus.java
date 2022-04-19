package com.slowv.fruit.domain.enums;

import com.slowv.fruit.domain.Account;
import lombok.Getter;

@Getter
public enum EAccountStatus {
    HOAT_DONG(1, "Hoạt động"), DANG_KHOA(0, "Đã khoá"), DA_XOA(-1, "Đã xoá");
    private int number;
    private String label;

    EAccountStatus(int number, String label){
        this.number = number;
        this.label = label;
    }

    public static EAccountStatus getStatusByValue(int value) {
        for (EAccountStatus status : EAccountStatus.values()) {
            if (status.number == value) return status;
        }
        throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
    }
}
