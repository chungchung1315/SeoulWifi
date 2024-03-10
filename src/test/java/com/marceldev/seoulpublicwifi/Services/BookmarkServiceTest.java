package com.marceldev.seoulpublicwifi.Services;

import com.marceldev.seoulpublicwifi.Models.Bookmark;

import java.util.List;

public class BookmarkServiceTest {
    public static void main(String[] args) {
        // Stateful
        //new BookmarkServiceTest().testAdd();
        //new BookmarkServiceTest().testDelete();

        // Stateless
        new BookmarkServiceTest().testList();
    }

    public void testAdd() {
        BookmarkService service = new BookmarkService();
        int row = service.add(1, "BS100024");
        if (row != 1) {
            throw new RuntimeException();
        }
    }

    public void testDelete() {
        BookmarkService service = new BookmarkService();
        int row = service.delete(2);
        System.out.println(row + " row deleted");
    }

    public void testList() {
        BookmarkService service = new BookmarkService();
        List<Bookmark> list = service.list();
        list.stream().forEach(System.out::println);
    }
}
