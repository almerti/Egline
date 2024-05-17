package almerti.egline.data.implementation

import almerti.egline.data.model.Book
import almerti.egline.data.model.Comment
import almerti.egline.data.model.User
import almerti.egline.data.repository.CommentRepository
import almerti.egline.data.source.network.NetworkApi
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<User>,
) : CommentRepository {
    override suspend fun getAll() : List<Comment> {
        val response = remoteApi.getComments()
        return if (response.isSuccessful) {
            response.body()!!.map {
                networkToEntity(it)
            }
        } else
            listOf()
    }

    override suspend fun getAll(book : Book) : List<Comment> {
        return getAll(book.id)
    }

    override suspend fun getAll(bookId : Int) : List<Comment> {
        val response = remoteApi.getComments()
        return if (response.isSuccessful) {
            response.body()!!.filter {it.bookId == bookId}.map {networkToEntity(it)}
        } else
            listOf()
    }

    override suspend fun add(comment : Comment) {
        remoteApi.createComment(entityToNetwork(comment))
    }

    override suspend fun delete(comment : Comment) {
        if (comment.id != userDataStore.data.first().id)
            return
        remoteApi.deleteComment(comment.id)
    }

    override suspend fun update(comment : Comment) {
        remoteApi.updateComment(comment.id, entityToNetwork(comment))
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
