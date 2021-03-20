package it.uniba.magr.misurapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import it.uniba.magr.misurapp.database.bean.Measure;
import it.uniba.magr.misurapp.database.bean.embedded.MeasureAndRuler;

/**
 * The Data access object (Dao) of measures.
 */
@Dao
public interface MeasurementsDao {

    /**
     * @return Gets all measurements that have been kept into the local database.
     */
    @Query("SELECT * FROM Measurements ORDER BY start_date")
    List<Measure> getAll();

    /**
     * @param title The title of the
     * @return A list of measurements filtered by title.
     */
    @Query("SELECT * FROM Measurements WHERE title=:title ORDER BY start_date")
    List<Measure> getMeasurements(String title);

    /**
     * @param title The title of the
     * @return A list of measurements filtered by title.
     */
    @Transaction
    @Query("SELECT * FROM Measurements NATURAL JOIN Rulers WHERE title=:title ORDER BY start_date")
    List<MeasureAndRuler> getRulerMeasure(String title);

    /**
     * @param measure a not null measure instance.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasure(Measure measure);

    /**
     * @param measures a not null array of measurements instances.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeasurements(Measure... measures);

    /**
     * @return The latest measure added into the table.
     */
    @Nullable
    @Query("SELECT * FROM Measurements WHERE id=last_insert_rowid()")
    Measure getLatestMeasure();

}