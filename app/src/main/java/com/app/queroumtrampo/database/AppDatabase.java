package com.app.queroumtrampo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.queroumtrampo.database.dao.ServicoDAO;
import com.app.queroumtrampo.database.model.ServicoEntity;

@Database(entities = {ServicoEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ServicoDAO servicoDAO();
}
