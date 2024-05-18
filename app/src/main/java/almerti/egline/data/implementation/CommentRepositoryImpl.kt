package almerti.egline.data.implementation

import almerti.egline.data.model.Book
import almerti.egline.data.model.Comment
import almerti.egline.data.model.User
import almerti.egline.data.repository.CommentRepository
import almerti.egline.data.source.network.NetworkApi
import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<User>,
) : CommentRepository {
    override suspend fun getAll() : List<Comment> {
        try {
            val response = remoteApi.getComments()
            if (response.isSuccessful) {
                return response.body()!!.map {
                    networkToEntity(it)
                }
            }
        } catch (e : Exception) {
            Log.e("CommentRepositoryImpl", e.toString())
        }
        return listOf()
    }

    override suspend fun getAll(book : Book) : List<Comment> {
        return getAll(book.id)
    }

    override suspend fun getAll(bookId : Int) : List<Comment> {
        try {
            val response = remoteApi.getComments()
            if (response.isSuccessful) {
                return response.body()!!.filter {it.bookId == bookId}.map {networkToEntity(it)}
            }
        } catch (e : Exception) {
            Log.e("CommentRepositoryImpl", e.toString())
        }
        return listOf()
    }

    override suspend fun add(comment : Comment) {
        try {
            remoteApi.createComment(entityToNetwork(comment))
        } catch (e : Exception) {
            Log.e("CommentRepositoryImpl", e.toString())
        }
    }

    override suspend fun delete(comment : Comment) {
        try {
            if (comment.id != userDataStore.data.first().id)
                return
            remoteApi.deleteComment(comment.id)
        } catch (e : Exception) {
            Log.e("CommentRepositoryImpl", e.toString())
        }
    }

    override suspend fun update(comment : Comment) {
        try {
            remoteApi.updateComment(comment.id, entityToNetwork(comment))
        } catch (e : Exception) {
            Log.e("CommentRepositoryImpl", e.toString())
        }
    }

    private fun networkToEntity(network : almerti.egline.data.source.network.model.Comment) : Comment {
        return Comment(
            id = network.id,
            userId = network.userId,
            bookId = network.bookId,
            rating = network.upvotes - network.downvotes,
            chapterId = if (network.chapterId <= 0) null else network.chapterId,
            text = network.text,
        )
    }

    private suspend fun entityToNetwork(entity : Comment) : almerti.egline.data.source.network.model.Comment {
        return almerti.egline.data.source.network.model.Comment(
            id = entity.id,
            userId = userDataStore.data.first().id,
            bookId = entity.bookId,
            upvotes = entity.rating,
            downvotes = entity.rating,
            chapterId = entity.chapterId ?: 0,
            text = entity.text,
        )
    }
}
