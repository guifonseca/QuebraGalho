package com.app.myapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.myapp.database.dao.ServicoDAO;
import com.app.myapp.database.model.ServicoEntity;

@Database(entities = {ServicoEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ServicoDAO servicoDAO();
}
