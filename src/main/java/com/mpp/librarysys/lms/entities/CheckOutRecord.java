package com.mpp.librarysys.lms.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class CheckOutRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<CheckOutRecordFine> checkOutRecordFines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CheckOutRecordFine> getCheckOutRecordFines() {
        return checkOutRecordFines;
    }

    public void setCheckOutRecordFines(List<CheckOutRecordFine> checkOutRecordFines) {
        this.checkOutRecordFines = checkOutRecordFines;
    }
}
