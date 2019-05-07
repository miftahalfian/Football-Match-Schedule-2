package com.solitelab.footballmatchschedule.data.mvp.matchdetail

import android.database.sqlite.SQLiteConstraintException
import com.google.gson.Gson
import com.solitelab.footballmatchschedule.data.api.ApiRepository
import com.solitelab.footballmatchschedule.data.api.TheSportDBApi
import com.solitelab.footballmatchschedule.data.db.database
import com.solitelab.footballmatchschedule.data.mvp.model.Favorite
import com.solitelab.footballmatchschedule.data.mvp.model.Match
import com.solitelab.footballmatchschedule.data.mvp.model.TeamResult
import com.solitelab.footballmatchschedule.ui.MatchDetailActivity
import com.solitelab.footballmatchschedule.utils.toDate
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class MatchDetailPresenter(val view : MatchDetailView) {

    fun loadTeamBadge(id : String?, tag : String) {
        doAsync {
            val data = Gson().fromJson(
                ApiRepository().doRequest(TheSportDBApi.getTeamDetail(id)),
                TeamResult::class.java
            )

            uiThread {
                view.onBadgeLoaded(data.teams[0].badge, tag)
            }
        }
    }

    fun addToFavorite(match : Match?) {
        val date = match?.date!!.toDate("yyyy-MM-dd")
        val currentDate = getCurrentDateTime()

        val tableName = if (date.compareTo(currentDate) > 0) "NEXT_MATCH" else "LAST_MATCH"
        val context = view as MatchDetailActivity

        try {
            context.database.use {
                insert(tableName,
                    Favorite.ID to match.id,
                    Favorite.LEAGUE to match.league,
                    Favorite.DATE_EVENT to match.date,
                    Favorite.TIME to match.time,
                    Favorite.HOME_TEAM_ID to match.homeTeamID,
                    Favorite.HOME_TEAM_NAME to match.homeTeamName,
                    Favorite.HOME_SCORE to match.homeScore,
                    Favorite.HOME_GOAL_DETAILS to match.homeGoalDetail,
                    Favorite.HOME_GOAL_KEEPER to match.homeGoalKeeper,
                    Favorite.HOME_DEFENSE to match.homeDefence,
                    Favorite.HOME_MIDFIELD to match.homeMidfield,
                    Favorite.HOME_FORWARD to match.homeForward,
                    Favorite.HOME_SUBSTITUTES to match.homeSubstitutes,
                    Favorite.HOME_RED_CARDS to match.homeRedCards,
                    Favorite.HOME_YELLOW_CARDS to match.homeYellowCards,
                    Favorite.AWAY_TEAM_ID to match.awayTeamID,
                    Favorite.AWAY_TEAM_NAME to match.awayTeamName,
                    Favorite.AWAY_SCORE to match.awayScore,
                    Favorite.AWAY_GOAL_DETAILS to match.awayGoalDetail,
                    Favorite.AWAY_GOAL_KEEPER to match.awayGoalKeeper,
                    Favorite.AWAY_DEFENSE to match.awayDefence,
                    Favorite.AWAY_MIDFIELD to match.awayMidfield,
                    Favorite.AWAY_FORWARD to match.awayForward,
                    Favorite.AWAY_SUBSTITUTES to match.awaySubstitutes,
                    Favorite.AWAY_RED_CARDS to match.awayRedCards,
                    Favorite.AWAY_YELLOW_CARDS to match.awayYellowCards
                )
            }
            context.getRootView().snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            context.getRootView().snackbar(e.localizedMessage).show()
        }
    }

    fun removeFromFavorite(match: Match?) {
        val date = match?.date!!.toDate("yyyy-MM-dd")
        val currentDate = getCurrentDateTime()

        val tableName = if (date.compareTo(currentDate) > 0) "NEXT_MATCH" else "LAST_MATCH"
        val context = view as MatchDetailActivity

        try {
            context.database.use {
                delete(tableName, "(ID = {id})", "id" to match.id!!)
            }
            context.getRootView().snackbar("Removed from favorite").show()

        } catch (e: SQLiteConstraintException){
            context.getRootView().snackbar(e.localizedMessage).show()
        }
    }

    fun isFavorite(match: Match?) : Boolean {
        val date = match?.date!!.toDate("yyyy-MM-dd")
        val currentDate = getCurrentDateTime()

        val tableName = if (date.compareTo(currentDate) > 0) "NEXT_MATCH" else "LAST_MATCH"
        val context = view as MatchDetailActivity

        var isFavorite = false

        context.database.use {
            val result = select(tableName)
                .whereArgs("(ID = {id})",
                    "id" to match.id!!)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }

        return isFavorite
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
}