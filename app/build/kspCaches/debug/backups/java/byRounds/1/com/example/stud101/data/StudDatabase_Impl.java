package com.example.stud101.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StudDatabase_Impl extends StudDatabase {
  private volatile AppDao _appDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `subjects` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `attendedClasses` INTEGER NOT NULL, `totalClasses` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `assignments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject` TEXT NOT NULL, `title` TEXT NOT NULL, `dueDate` TEXT NOT NULL, `isCompleted` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tests` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subject` TEXT NOT NULL, `date` TEXT NOT NULL, `notes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `od_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` TEXT NOT NULL, `reason` TEXT NOT NULL, `description` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '6a4d41a72a2faa3ab057be5ab4afd100')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `subjects`");
        db.execSQL("DROP TABLE IF EXISTS `assignments`");
        db.execSQL("DROP TABLE IF EXISTS `tests`");
        db.execSQL("DROP TABLE IF EXISTS `od_records`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsSubjects = new HashMap<String, TableInfo.Column>(4);
        _columnsSubjects.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubjects.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubjects.put("attendedClasses", new TableInfo.Column("attendedClasses", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSubjects.put("totalClasses", new TableInfo.Column("totalClasses", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSubjects = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSubjects = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSubjects = new TableInfo("subjects", _columnsSubjects, _foreignKeysSubjects, _indicesSubjects);
        final TableInfo _existingSubjects = TableInfo.read(db, "subjects");
        if (!_infoSubjects.equals(_existingSubjects)) {
          return new RoomOpenHelper.ValidationResult(false, "subjects(com.example.stud101.data.Subject).\n"
                  + " Expected:\n" + _infoSubjects + "\n"
                  + " Found:\n" + _existingSubjects);
        }
        final HashMap<String, TableInfo.Column> _columnsAssignments = new HashMap<String, TableInfo.Column>(5);
        _columnsAssignments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssignments.put("subject", new TableInfo.Column("subject", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssignments.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssignments.put("dueDate", new TableInfo.Column("dueDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAssignments.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAssignments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAssignments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAssignments = new TableInfo("assignments", _columnsAssignments, _foreignKeysAssignments, _indicesAssignments);
        final TableInfo _existingAssignments = TableInfo.read(db, "assignments");
        if (!_infoAssignments.equals(_existingAssignments)) {
          return new RoomOpenHelper.ValidationResult(false, "assignments(com.example.stud101.data.Assignment).\n"
                  + " Expected:\n" + _infoAssignments + "\n"
                  + " Found:\n" + _existingAssignments);
        }
        final HashMap<String, TableInfo.Column> _columnsTests = new HashMap<String, TableInfo.Column>(4);
        _columnsTests.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTests.put("subject", new TableInfo.Column("subject", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTests.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTests.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTests = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTests = new TableInfo("tests", _columnsTests, _foreignKeysTests, _indicesTests);
        final TableInfo _existingTests = TableInfo.read(db, "tests");
        if (!_infoTests.equals(_existingTests)) {
          return new RoomOpenHelper.ValidationResult(false, "tests(com.example.stud101.data.TestRecord).\n"
                  + " Expected:\n" + _infoTests + "\n"
                  + " Found:\n" + _existingTests);
        }
        final HashMap<String, TableInfo.Column> _columnsOdRecords = new HashMap<String, TableInfo.Column>(4);
        _columnsOdRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOdRecords.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOdRecords.put("reason", new TableInfo.Column("reason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOdRecords.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOdRecords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOdRecords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOdRecords = new TableInfo("od_records", _columnsOdRecords, _foreignKeysOdRecords, _indicesOdRecords);
        final TableInfo _existingOdRecords = TableInfo.read(db, "od_records");
        if (!_infoOdRecords.equals(_existingOdRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "od_records(com.example.stud101.data.ODRecord).\n"
                  + " Expected:\n" + _infoOdRecords + "\n"
                  + " Found:\n" + _existingOdRecords);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "6a4d41a72a2faa3ab057be5ab4afd100", "1183b29bad64cce4aacd6e670889773e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "subjects","assignments","tests","od_records");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `subjects`");
      _db.execSQL("DELETE FROM `assignments`");
      _db.execSQL("DELETE FROM `tests`");
      _db.execSQL("DELETE FROM `od_records`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppDao appDao() {
    if (_appDao != null) {
      return _appDao;
    } else {
      synchronized(this) {
        if(_appDao == null) {
          _appDao = new AppDao_Impl(this);
        }
        return _appDao;
      }
    }
  }
}
