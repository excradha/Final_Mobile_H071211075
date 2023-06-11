package com.example.h071211075_finalmobile;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.DatabaseEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.DatabaseEntry.SQL_DROP_TABLE);
        onCreate(db);
    }

    public long insertFavorite(Favorite favorite) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DatabaseEntry.COLUMN_TITLE, favorite.getTitle());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE, favorite.getReleaseDate());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW, favorite.getOverview());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_POSTER_URL, favorite.getPosterPath());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL, favorite.getBackdropUrl());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE, favorite.getVoteAverage());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_GENRE_IDS, favorite.getId());

        return db.insert(DatabaseContract.DatabaseEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllFavorites() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                DatabaseContract.DatabaseEntry._ID,
                DatabaseContract.DatabaseEntry.COLUMN_TITLE,
                DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE,
                DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW,
                DatabaseContract.DatabaseEntry.COLUMN_POSTER_URL,
                DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL,
                DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE,
                DatabaseContract.DatabaseEntry.COLUMN_GENRE_IDS
        };
        return db.query(
                DatabaseContract.DatabaseEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public int updateFavorite(Favorite favorite) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DatabaseEntry.COLUMN_TITLE, favorite.getTitle());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE, favorite.getReleaseDate());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW, favorite.getOverview());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_POSTER_URL, favorite.getPosterPath());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL, favorite.getBackdropUrl());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE, favorite.getVoteAverage());
        values.put(DatabaseContract.DatabaseEntry.COLUMN_GENRE_IDS, favorite.getId());

        String selection = DatabaseContract.DatabaseEntry._ID + "=?";
        String[] selectionArgs = {String.valueOf(favorite.getId())};

        return db.update(DatabaseContract.DatabaseEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public int deleteFavorite(String nama) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = DatabaseContract.DatabaseEntry.COLUMN_TITLE + "=?";
        String[] selectionArgs = {String.valueOf(nama)};

        return db.delete(DatabaseContract.DatabaseEntry.TABLE_NAME, selection, selectionArgs);
    }

    public boolean isFavorites(String favoriteTitle) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            String query = "SELECT * FROM " + DatabaseContract.DatabaseEntry.TABLE_NAME +
                    " WHERE " + DatabaseContract.DatabaseEntry.COLUMN_TITLE + " = ?";
            String[] selectionArgs = {String.valueOf(favoriteTitle)};
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return false;
    }


}
