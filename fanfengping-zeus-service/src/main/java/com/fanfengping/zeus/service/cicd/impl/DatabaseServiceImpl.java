package com.fanfengping.zeus.service.cicd.impl;


import com.fanfengping.zeus.entity.cicd.Database;
import com.fanfengping.zeus.repository.cicd.DatabaseRepository;
import com.fanfengping.zeus.service.cicd.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    DatabaseRepository databaseRepository;

    public Database add(Database database) {
        if (database != null && databaseRepository.add(database) > 0) {
            if (database.getBenchmark() == 1) {
                databaseRepository.setBenchmarkFalse(database);
            }

            return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
        }

        return null;
    }

    public Database update(Database database) {
        if (database != null && databaseRepository.update(database) > 0) {
            if (database.getBenchmark() == 1) {
                databaseRepository.setBenchmarkFalse(database);
            }

            return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
        }

        return null;
    }

    public Database delete(Database database) {
        if (database != null && databaseRepository.delete(database) > 0) {
            return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
        }

        return null;
    }

    public Database setBenchmark(Database database) {
        if (database != null && databaseRepository.setBenchmark(database) > 0) {
            databaseRepository.setBenchmarkFalse(database);

            return databaseRepository.findByEnvAndEng(database.getEnv(), database.getEng());
        }

        return null;
    }



    public List<Database> findAllByConditions(String env, String eng, String chs) {
        return databaseRepository.findAllByConditions(env, eng, chs);
    }
}

