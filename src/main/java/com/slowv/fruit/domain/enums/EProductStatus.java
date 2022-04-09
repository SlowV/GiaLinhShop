package com.slowv.fruit.domain.enums;

import lombok.Getter;

@Getter
public enum EProductStatus {
    VAN_CON(1, "Vẫn còn"), HET_HANG(0, "Hết hàng"), DA_XOA(-1, "Đã xóa");
    private final int number;
    private final String label;

    EProductStatus(int number, String label){
        this.number = number;
        this.label = label;
    }

    public static EProductStatus getStatusByValue(int value) {
        for (EProductStatus status : EProductStatus.values()) {
            if (status.number == value) return status;
        }
        throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
    }
}
