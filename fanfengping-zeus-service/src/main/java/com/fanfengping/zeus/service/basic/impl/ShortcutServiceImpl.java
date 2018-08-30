package com.fanfengping.zeus.service.basic.impl;

import com.fanfengping.zeus.entity.basic.Shortcut;
import com.fanfengping.zeus.repository.basic.ShortcutRepository;
import com.fanfengping.zeus.service.basic.ShortcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShortcutServiceImpl implements ShortcutService {
    @Autowired
    ShortcutRepository shortcutRepository;

    public Shortcut add(Shortcut shortcut) {
        if (shortcut != null && shortcutRepository.add(shortcut) > 0) {
            return shortcut;
        }

        return null;
    }

    public Shortcut update(Shortcut shortcut) {
        if (shortcut != null && shortcutRepository.update(shortcut) > 0) {
            return shortcut;
        }

        return null;
    }

    public Shortcut delete(Shortcut shortcut) {
        if (shortcut != null && shortcutRepository.delete(shortcut) > 0) {
            return shortcut;
        }

        return null;
    }

    public List<Shortcut> findAll() {
        return shortcutRepository.findAll();
    }
}
