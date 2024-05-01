package net.skhu.tastyinventory_be.fixture;

import net.skhu.tastyinventory_be.domain.menu.Menu;

public class MenuFixture {

    public static final Menu MENU_1 = new Menu(
            "목살파히타",
            30000L,
            "abc.com"
    );

    public static final Menu MENU_2 = new Menu(
            "베이컨까르보나라",
            20000L,
            "def.com"
    );

    public static final Menu MENU_3 = new Menu(
            "게살볶음밥",
            10000L,
            "ghi.com"
    );

}