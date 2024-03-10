package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Models.BookmarkGroup;
import com.marceldev.seoulpublicwifi.Models.BookmarkGroupModify;

import java.util.List;

public class BookmarkGroupServiceTest {
    public static void main(String[] args) {
        new BookmarkGroupServiceTest().testAdd();
        new BookmarkGroupServiceTest().testAdd();
        new BookmarkGroupServiceTest().testList();
        new BookmarkGroupServiceTest().testDelete();
        new BookmarkGroupServiceTest().testList();
        new BookmarkGroupServiceTest().testEdit();
        new BookmarkGroupServiceTest().testList();
    }

    public void testAdd() {
        BookmarkGroupService service = new BookmarkGroupService();
        int row = service.add("내집 근처", 3);
        if (row != 1) {
            throw new RuntimeException();
        }
    }

    public void testList() {
        BookmarkGroupService service = new BookmarkGroupService();
        List<BookmarkGroup> list = service.list();
        for (BookmarkGroup bookmarkGroup : list) {
            System.out.println(bookmarkGroup);
        }
    }

    public void testDelete() {
        BookmarkGroupService service = new BookmarkGroupService();

        List<BookmarkGroup> list = service.list();
        for (BookmarkGroup bookmarkGroup : list) {
            service.delete(bookmarkGroup.getId());
            System.out.println(bookmarkGroup + " 삭제");
            break;
        }
    }

    public void testEdit() {
        BookmarkGroupService service = new BookmarkGroupService();
        List<BookmarkGroup> list = service.list();
        for (BookmarkGroup bookmarkGroup : list) {
            BookmarkGroupModify modify = new BookmarkGroupModify();
            modify.setBookmarkName("새로운 이름");
            modify.setPriority(20);
            service.edit(bookmarkGroup.getId(), modify);
            break;
        }
    }
}
