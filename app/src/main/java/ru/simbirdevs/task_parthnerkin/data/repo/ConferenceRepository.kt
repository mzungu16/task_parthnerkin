package ru.simbirdevs.task_parthnerkin.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.simbirdevs.task_parthnerkin.data.retrofit.Result
import ru.simbirdevs.task_parthnerkin.data.retrofit.RetrofitInt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConferenceRepository @Inject constructor(private val api: RetrofitInt) {
    fun getRemoteConferences(): Flow<List<Result>> {
        val response = api.getConferences().execute().body()?.data?.result ?: emptyList()
        return flow {
            emit(response)
        }
    }
}