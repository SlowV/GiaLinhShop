package com.slowv.fruit.domain.enums;

import lombok.Getter;

@Getter
public enum ECategoryStatus {
    HOAT_DONG(1, "Hoạt động"), NGUNG_HOAT_DONG(0, " Ngưng hoạt động"), DA_XOA(-1, "Đã xóa");
    private final int number;
    private final String label;

    ECategoryStatus(int number, String label) {
        this.number = number;
        this.label = label;
    }


    public static ECategoryStatus getStatusByValue(int value) {
        for (ECategoryStatus status : ECategoryStatus.values()) {
            if (status.number == value) return status;
        }
        throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
    }
}
