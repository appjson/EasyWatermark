package site.appjson.easywatermark.ui

import site.appjson.easywatermark.data.model.entity.Template

sealed class UiState {
    class UseTemplate(val template: Template) : UiState()
    object GoTemplate : UiState()
    object GoEdit : UiState()
    object GoEditDialog : UiState()
    object None : UiState()
    object DatabaseError : UiState()
}
