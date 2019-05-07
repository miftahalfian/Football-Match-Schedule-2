package com.solitelab.footballmatchschedule.data.mvp.model


data class Favorite (
    val id: String? = null,
    val league: String? = null,
    val date: String? = null,
    val time: String? = null,
    val homeTeamID: String? = null,
    val homeTeamName: String? = null,
    val homeScore: Int? = null,
    val homeGoalDetail: String? = null,
    val homeGoalKeeper: String? = null,
    val homeDefence: String? = null,
    val homeMidfield: String? = null,
    val homeForward: String? = null,
    val homeSubstitutes: String? = null,
    val homeRedCards: String? = null,
    val homeYellowCards: String? = null,
    val awayTeamID: String? = null,
    val awayTeamName: String? = null,
    val awayScore: Int? = null,
    val awayGoalDetail: String? = null,
    val awayGoalKeeper: String? = null,
    val awayDefence: String? = null,
    val awayMidfield: String? = null,
    val awayForward: String? = null,
    val awaySubstitutes: String? = null,
    val awayRedCards: String? = null,
    val awayYellowCards: String? = null
) {
    companion object {
        const val ID: String = "ID"
        const val LEAGUE: String = "LEAGUE"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME: String = "TIME"
        const val HOME_TEAM_ID = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME = "HOME_TEAM_NAME"
        const val HOME_SCORE = "HOME_SCORE"
        const val HOME_GOAL_DETAILS = "HOME_GOAL_DETAILS"
        const val HOME_GOAL_KEEPER = "HOME_GOAL_KEEPER"
        const val HOME_DEFENSE = "HOME_DEFENSE"
        const val HOME_MIDFIELD = "HOME_MIDFIELD"
        const val HOME_FORWARD = "HOME_FORWARD"
        const val HOME_SUBSTITUTES = "HOME_SUBSTITUTES"
        const val HOME_RED_CARDS = "HOME_RED_CARDS"
        const val HOME_YELLOW_CARDS = "HOME_YELLOW_CARDS"
        const val AWAY_TEAM_ID = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME = "AWAY_TEAM_NAME"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val AWAY_GOAL_DETAILS = "AWAY_GOAL_DETAILS"
        const val AWAY_GOAL_KEEPER = "AWAY_GOAL_KEEPER"
        const val AWAY_DEFENSE = "AWAY_DEFENSE"
        const val AWAY_MIDFIELD = "AWAY_MIDFIELD"
        const val AWAY_FORWARD = "AWAY_FORWARD"
        const val AWAY_SUBSTITUTES = "AWAY_SUBSTITUTES"
        const val AWAY_RED_CARDS = "AWAY_RED_CARDS"
        const val AWAY_YELLOW_CARDS = "AWAY_YELLOW_CARDS"
    }
}