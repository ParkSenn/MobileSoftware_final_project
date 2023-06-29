package ddwucom.mobile.finalreport.data

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import ddwucom.mobile.finalreport.R

class MovieDao(val context: Context) {

    val helper = MovieDBHelper(context)

    // 리스트 얻기
    fun getAllMovies() : ArrayList<MovieDto> {
        val db = helper.readableDatabase
        val cursor = db.query(MovieDBHelper.TABLE_NAME, null, null, null, null, null, null)
        val movies = arrayListOf<MovieDto>()

        val idColumnIndex = cursor.getColumnIndex(BaseColumns._ID)
        val titleColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_TITLE)
        val directorColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR)
        val actorColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_ACTOR)
        val rateColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_RATE)
        val storyColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_STORY)


        with(cursor) {
            while (moveToNext()) {
                val id = if (idColumnIndex != -1) getInt(idColumnIndex) else 0
                val title = if (titleColumnIndex != -1) getString(titleColumnIndex) else ""
                val director = if (directorColumnIndex != -1) getString(directorColumnIndex) else ""
                val actor = if (actorColumnIndex != -1) getString(actorColumnIndex) else ""
                val rate = if (rateColumnIndex != -1) getDouble(rateColumnIndex) else 0.0
                val story = if (storyColumnIndex != -1) getString(storyColumnIndex) else ""

                val dto = MovieDto(id, R.mipmap.loveletter, title, director, actor, rate, story)
                movies.add(dto)
            }
        }
        cursor.close()
        helper.close()
        return movies
    }

    // 영화 추가
    fun addMovie(newDto: MovieDto) : Long {
        val db = helper.writableDatabase

        val newValue = ContentValues()
        newValue.put(MovieDBHelper.COL_TITLE, newDto.title)
        newValue.put(MovieDBHelper.COL_DIRECTOR, newDto.director)
        newValue.put(MovieDBHelper.COL_ACTOR, newDto.actor)
        newValue.put(MovieDBHelper.COL_RATE, newDto.rate)
        newValue.put(MovieDBHelper.COL_STORY, newDto.story)

        val result = db.insert(MovieDBHelper.TABLE_NAME, null, newValue)

        helper.close()

        return result
    }

    // 영화 수정
    fun updateMovie(dto: MovieDto) : Int {
        val db = helper.writableDatabase

        val updateValue = ContentValues()
        updateValue.put(MovieDBHelper.COL_TITLE, dto.title)
        updateValue.put(MovieDBHelper.COL_DIRECTOR, dto.director)
        updateValue.put(MovieDBHelper.COL_ACTOR, dto.actor)
        updateValue.put(MovieDBHelper.COL_RATE, dto.rate)
        updateValue.put(MovieDBHelper.COL_STORY, dto.story)

        val where = "${BaseColumns._ID}=?"
        val here = arrayOf(dto.id.toString())
        val result = db.update(MovieDBHelper.TABLE_NAME, updateValue, where, here)
        helper.close()

        return result
    }

    // 영화 삭제
    fun deleteMovie(id: Int) : Int {
        val db = helper.writableDatabase

        val where = "${BaseColumns._ID}=?"
        val here = arrayOf(id.toString())

        val result = db.delete(MovieDBHelper.TABLE_NAME, where, here)

        helper.close()
        return result
    }

    // 영화 검색
    fun searchMovie(kw: String) : String {
        val movies = arrayListOf<MovieDto>()
        val db = helper.readableDatabase
        val columns = null
        val selection = "title=?"
        val selectionKw = arrayOf(kw)

        val cursor = db.query(
            MovieDBHelper.TABLE_NAME, columns, selection, selectionKw,
            null, null, null, null)

        val titleColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_TITLE)
        val directorColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_DIRECTOR)
        val actorColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_ACTOR)
        val rateColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_RATE)
        val storyColumnIndex = cursor.getColumnIndex(MovieDBHelper.COL_STORY)

        var result = ""
        with(cursor) {
            while (moveToNext()) {
                val title = if (titleColumnIndex != -1) getString(titleColumnIndex) else ""
                val director = if (directorColumnIndex != -1) getString(directorColumnIndex) else ""
                val actor = if (actorColumnIndex != -1) getString(actorColumnIndex) else ""
                val rate = if (rateColumnIndex != -1) getDouble(rateColumnIndex) else 0.0
                val story = if (storyColumnIndex != -1) getString(storyColumnIndex) else ""

                result += "\n제목: " + title + "\n" + "감독: " + director + "\n" +"출연: " + actor + "\n" +"평점: " + rate + "\n" +"줄거리: " + story + "\n\n"
            }
        }

        cursor.close()
        helper.close()

        return result
    }

}

