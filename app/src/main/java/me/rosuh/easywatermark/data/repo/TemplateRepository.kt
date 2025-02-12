package site.appjson.easywatermark.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import site.appjson.easywatermark.data.db.dao.TemplateDao
import site.appjson.easywatermark.data.model.entity.Template
import javax.inject.Inject

class TemplateRepository @Inject constructor(
    private val templateDao: TemplateDao?
) {

    fun checkIfIsDaoNull(): Boolean {
        return templateDao == null
    }

    fun getAllTemplate(): Flow<List<Template>> {
        return templateDao?.getAllTemplate() ?: flow {
            emit(listOf())
        }
    }

    suspend fun insertTemplate(template: Template) = withContext(Dispatchers.IO) {
        templateDao?.insertTemplate(template)
    }

    suspend fun deleteTemplate(template: Template) = withContext(Dispatchers.IO) {
        templateDao?.deleteTemplate(template)
    }

    suspend fun updateTemplate(template: Template) = withContext(Dispatchers.IO) {
        templateDao?.updateTemplate(template)
    }
}