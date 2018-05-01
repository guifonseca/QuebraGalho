package com.app.myapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.app.myapp.database.model.ServicoEntity;

import java.util.List;

@Dao
public interface ServicoDAO {

    @Query("SELECT * FROM servico")
    List<ServicoEntity> getAll();

    @Query("SELECT * FROM servico WHERE uid = :id ")
    ServicoEntity findById(int id);

    @Insert
    void insert(ServicoEntity servico);

    @Delete
    void delete(ServicoEntity servico);
}
