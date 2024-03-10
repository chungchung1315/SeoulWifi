package com.marceldev.seoulpublicwifi.Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookmarkGroup {
    private int id;
    private String bookmarkName;
    private int priority; // lower first
    private String registerDate;
    private String modifyDate;

    @Override
    public String toString() {
        return String.format("%d, %s, %d, %s, %s", id, bookmarkName, priority, registerDate, modifyDate);
    }
}
