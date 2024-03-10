package com.marceldev.seoulpublicwifi.Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookmarkGroupModify {
    private String bookmarkName;
    private Integer priority; // lower first
}
