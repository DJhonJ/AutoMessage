package com.djhonj.automessage.interfaces

interface IProgrammingPresenter: IShowMessage {
    override fun show(string: String)
    fun save(dateTime: String)
}