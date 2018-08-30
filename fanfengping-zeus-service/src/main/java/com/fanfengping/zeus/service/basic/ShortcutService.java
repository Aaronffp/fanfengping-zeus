package com.fanfengping.zeus.service.basic;

import com.fanfengping.zeus.entity.basic.Shortcut;

import java.util.List;

public interface ShortcutService {
    Shortcut add(Shortcut shortcut);
    Shortcut update(Shortcut shortcut);
    Shortcut delete(Shortcut shortcut);

    List<Shortcut> findAll();
}
