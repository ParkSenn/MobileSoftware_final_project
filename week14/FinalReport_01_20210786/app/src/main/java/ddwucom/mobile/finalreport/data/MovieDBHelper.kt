package ddwucom.mobile.finalreport.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class MovieDBHelper(context: Context?) :SQLiteOpenHelper(context, DB_NAME, null, 2) {

    companion object {
        const val DB_NAME = "movie_db"
        const val TABLE_NAME = "movie_table"
//        const val COL_POSTER = "poster"
        const val COL_TITLE = "title"
        const val COL_DIRECTOR = "director"
        const val COL_ACTOR = "actor"
        const val COL_RATE = "rate"
        const val COL_STORY = "story"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE ${TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${COL_TITLE} TEXT, ${COL_DIRECTOR} TEXT, " +
                    "${COL_ACTOR} TEXT, ${COL_RATE} DOUBLE, ${COL_STORY} TEXT)"

        db?.execSQL(CREATE_TABLE)

        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '올드보이', '박찬욱', '최민식, 유지태', 4.7, '복수에 집착하는 남자가 15년간의 감금 후 복수를 위해 펼치는 예측 불가능한 이야기')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '러브레터', '이와이 슌지', '나카야마 미호', 4.5, '세대와 시간을 초월한 사랑 이야기를 통해 감동과 위로를 전하는 작품')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '싸이코', '알프레도 히치콕', '안소니 퍼킨스', 4.6, '정신적인 복잡성과 광기에 둘러싸인 심리 스릴러로, 사이코패스와 그를 향한 여성의 숨 막히는 대치를 그린 작품')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '중경삼림', '왕가위', '왕페이, 양조위, 금성무', 4.3, '구룡반도의 충킹맨션 주변을 배경으로 한 1부와 홍콩섬의 센트럴 지역을 배경으로 한 2부, 실연을 겪는 두 남자 경찰을 주인공으로 한 두 개의 에피소드가 옴니버스식으로 구성된 작품')")
        db?.execSQL("INSERT INTO $TABLE_NAME VALUES (NULL, '귀여운 여인', '게리 마샬', '리처드 기어, 줄리아 로버츠', 4.4, '한 남성이 자신의 성장과 사랑을 통해 현실과 이상의 경계를 넘어서는 이야기를 그린 로맨틱 드라마')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }
}