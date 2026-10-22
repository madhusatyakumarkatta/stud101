package com.example.stud101.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Subject> __insertionAdapterOfSubject;

  private final EntityInsertionAdapter<Assignment> __insertionAdapterOfAssignment;

  private final EntityInsertionAdapter<TestRecord> __insertionAdapterOfTestRecord;

  private final EntityInsertionAdapter<ODRecord> __insertionAdapterOfODRecord;

  private final EntityDeletionOrUpdateAdapter<Subject> __deletionAdapterOfSubject;

  private final EntityDeletionOrUpdateAdapter<Assignment> __deletionAdapterOfAssignment;

  private final EntityDeletionOrUpdateAdapter<TestRecord> __deletionAdapterOfTestRecord;

  private final EntityDeletionOrUpdateAdapter<ODRecord> __deletionAdapterOfODRecord;

  private final EntityDeletionOrUpdateAdapter<Subject> __updateAdapterOfSubject;

  private final EntityDeletionOrUpdateAdapter<Assignment> __updateAdapterOfAssignment;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSubject = new EntityInsertionAdapter<Subject>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `subjects` (`id`,`name`,`attendedClasses`,`totalClasses`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Subject entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getAttendedClasses());
        statement.bindLong(4, entity.getTotalClasses());
      }
    };
    this.__insertionAdapterOfAssignment = new EntityInsertionAdapter<Assignment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `assignments` (`id`,`subject`,`title`,`dueDate`,`isCompleted`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Assignment entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getSubject());
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getDueDate());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__insertionAdapterOfTestRecord = new EntityInsertionAdapter<TestRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tests` (`id`,`subject`,`date`,`notes`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TestRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getSubject());
        statement.bindString(3, entity.getDate());
        statement.bindString(4, entity.getNotes());
      }
    };
    this.__insertionAdapterOfODRecord = new EntityInsertionAdapter<ODRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `od_records` (`id`,`date`,`reason`,`description`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ODRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDate());
        statement.bindString(3, entity.getReason());
        statement.bindString(4, entity.getDescription());
      }
    };
    this.__deletionAdapterOfSubject = new EntityDeletionOrUpdateAdapter<Subject>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `subjects` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Subject entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfAssignment = new EntityDeletionOrUpdateAdapter<Assignment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `assignments` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Assignment entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfTestRecord = new EntityDeletionOrUpdateAdapter<TestRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tests` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TestRecord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfODRecord = new EntityDeletionOrUpdateAdapter<ODRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `od_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ODRecord entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfSubject = new EntityDeletionOrUpdateAdapter<Subject>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `subjects` SET `id` = ?,`name` = ?,`attendedClasses` = ?,`totalClasses` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Subject entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindLong(3, entity.getAttendedClasses());
        statement.bindLong(4, entity.getTotalClasses());
        statement.bindLong(5, entity.getId());
      }
    };
    this.__updateAdapterOfAssignment = new EntityDeletionOrUpdateAdapter<Assignment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `assignments` SET `id` = ?,`subject` = ?,`title` = ?,`dueDate` = ?,`isCompleted` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Assignment entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getSubject());
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getDueDate());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertSubject(final Subject subject, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSubject.insert(subject);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAssignment(final Assignment assignment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAssignment.insert(assignment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTest(final TestRecord test, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTestRecord.insert(test);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertODRecord(final ODRecord od, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfODRecord.insert(od);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSubject(final Subject subject, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSubject.handle(subject);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAssignment(final Assignment assignment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAssignment.handle(assignment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTest(final TestRecord test, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTestRecord.handle(test);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteODRecord(final ODRecord od, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfODRecord.handle(od);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSubject(final Subject subject, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSubject.handle(subject);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAssignment(final Assignment assignment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAssignment.handle(assignment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Subject>> getAllSubjects() {
    final String _sql = "SELECT * FROM subjects";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"subjects"}, new Callable<List<Subject>>() {
      @Override
      @NonNull
      public List<Subject> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAttendedClasses = CursorUtil.getColumnIndexOrThrow(_cursor, "attendedClasses");
          final int _cursorIndexOfTotalClasses = CursorUtil.getColumnIndexOrThrow(_cursor, "totalClasses");
          final List<Subject> _result = new ArrayList<Subject>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Subject _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpAttendedClasses;
            _tmpAttendedClasses = _cursor.getInt(_cursorIndexOfAttendedClasses);
            final int _tmpTotalClasses;
            _tmpTotalClasses = _cursor.getInt(_cursorIndexOfTotalClasses);
            _item = new Subject(_tmpId,_tmpName,_tmpAttendedClasses,_tmpTotalClasses);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Assignment>> getAllAssignments() {
    final String _sql = "SELECT * FROM assignments ORDER BY dueDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"assignments"}, new Callable<List<Assignment>>() {
      @Override
      @NonNull
      public List<Assignment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Assignment> _result = new ArrayList<Assignment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Assignment _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpSubject;
            _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDueDate;
            _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            _item = new Assignment(_tmpId,_tmpSubject,_tmpTitle,_tmpDueDate,_tmpIsCompleted);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TestRecord>> getAllTests() {
    final String _sql = "SELECT * FROM tests ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tests"}, new Callable<List<TestRecord>>() {
      @Override
      @NonNull
      public List<TestRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "subject");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<TestRecord> _result = new ArrayList<TestRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TestRecord _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpSubject;
            _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new TestRecord(_tmpId,_tmpSubject,_tmpDate,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ODRecord>> getAllODRecords() {
    final String _sql = "SELECT * FROM od_records ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"od_records"}, new Callable<List<ODRecord>>() {
      @Override
      @NonNull
      public List<ODRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<ODRecord> _result = new ArrayList<ODRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ODRecord _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            final String _tmpReason;
            _tmpReason = _cursor.getString(_cursorIndexOfReason);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new ODRecord(_tmpId,_tmpDate,_tmpReason,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
