package com.solitelab.footballmatchschedule.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.solitelab.footballmatchschedule.data.mvp.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createLastMatchTable(db)
        createNextMatchTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("LAST_MATCH", true)
        db.dropTable("NEXT_MATCH", true)

        createLastMatchTable(db)
        createNextMatchTable(db)
    }

    private fun createLastMatchTable(db: SQLiteDatabase) {
        db.createTable("LAST_MATCH", true,
            Favorite.ID to TEXT + PRIMARY_KEY,
            Favorite.LEAGUE to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.TIME to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_SCORE to INTEGER,
            Favorite.HOME_GOAL_DETAILS to TEXT,
            Favorite.HOME_GOAL_KEEPER to TEXT,
            Favorite.HOME_DEFENSE to TEXT,
            Favorite.HOME_MIDFIELD to TEXT,
            Favorite.HOME_FORWARD to TEXT,
            Favorite.HOME_SUBSTITUTES to TEXT,
            Favorite.HOME_RED_CARDS to TEXT,
            Favorite.HOME_YELLOW_CARDS to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_SCORE to INTEGER,
            Favorite.AWAY_GOAL_DETAILS to TEXT,
            Favorite.AWAY_GOAL_KEEPER to TEXT,
            Favorite.AWAY_DEFENSE to TEXT,
            Favorite.AWAY_MIDFIELD to TEXT,
            Favorite.AWAY_FORWARD to TEXT,
            Favorite.AWAY_SUBSTITUTES to TEXT,
            Favorite.AWAY_RED_CARDS to TEXT,
            Favorite.AWAY_YELLOW_CARDS to TEXT
        )
    }

    private fun createNextMatchTable(db: SQLiteDatabase) {
        db.createTable("NEXT_MATCH", true,
            Favorite.ID to TEXT + PRIMARY_KEY,
            Favorite.LEAGUE to TEXT,
            Favorite.DATE_EVENT to TEXT,
            Favorite.TIME to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_SCORE to INTEGER,
            Favorite.HOME_GOAL_DETAILS to TEXT,
            Favorite.HOME_GOAL_KEEPER to TEXT,
            Favorite.HOME_DEFENSE to TEXT,
            Favorite.HOME_MIDFIELD to TEXT,
            Favorite.HOME_FORWARD to TEXT,
            Favorite.HOME_SUBSTITUTES to TEXT,
            Favorite.HOME_RED_CARDS to TEXT,
            Favorite.HOME_YELLOW_CARDS to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_SCORE to INTEGER,
            Favorite.AWAY_GOAL_DETAILS to TEXT,
            Favorite.AWAY_GOAL_KEEPER to TEXT,
            Favorite.AWAY_DEFENSE to TEXT,
            Favorite.AWAY_MIDFIELD to TEXT,
            Favorite.AWAY_FORWARD to TEXT,
            Favorite.AWAY_SUBSTITUTES to TEXT,
            Favorite.AWAY_RED_CARDS to TEXT,
            Favorite.AWAY_YELLOW_CARDS to TEXT
        )
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)